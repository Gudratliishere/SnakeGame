package com.gudratli.snakegame;

import com.gudratli.snakegame.component.Apple;
import com.gudratli.snakegame.component.Snake;
import com.gudratli.snakegame.config.Sound;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Game
{
    private boolean running;
    private boolean paused = false;

    private Apple apple;
    private Snake snake;

    public Game ()
    {
        apple = Apple.generateApple();
        snake = new Snake();
        running = true;
    }

    public boolean isRunning ()
    {
        return running;
    }

    public boolean isPaused ()
    {
        return paused;
    }

    public void togglePaused ()
    {
        paused = !paused;
    }

    public void performActions ()
    {
        snake.checkApple(apple);
        snake.move();
        checkCollisions();
    }

    private void checkCollisions ()
    {
        running = !snake.intersects();
        if (!running)
            Sound.playGameOver();
    }

    public int getScore ()
    {
        return snake.getAppleEaten();
    }

    public void drawGame (Graphics g)
    {
        apple.draw(g);
        snake.draw(g);
    }

    public void changeDirection (KeyEvent e)
    {
        if (!paused)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_LEFT:
                    snake.changeDirectionToLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    snake.changeDirectionToRight();
                    break;
                case KeyEvent.VK_UP:
                    snake.changeDirectionToTop();
                    break;
                case KeyEvent.VK_DOWN:
                    snake.changeDirectionToBottom();
                    break;
            }
        }
    }
}
