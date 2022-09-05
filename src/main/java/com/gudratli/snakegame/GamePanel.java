package com.gudratli.snakegame;

import com.gudratli.snakegame.component.Apple;
import com.gudratli.snakegame.component.Snake;
import com.gudratli.snakegame.config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener
{
    private boolean running = false;
    private Apple apple;
    private Snake snake;

    public GamePanel ()
    {
        apple = Apple.generateApple();
        snake = new Snake();
        setPreferredSize(new Dimension(Config.getScreenWidth(), Config.getScreenHeight()));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
        startGame();
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {
        if (running)
        {
            snake.checkApple(apple);
            snake.move();
            checkCollisions();
        }
        repaint();
    }

    public void startGame ()
    {
        running = true;
        Timer timer = new Timer(Config.getDelay(), this);
        timer.start();
    }

    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }

    public void draw (Graphics g)
    {
        if (running)
        {
            if (Config.getGrid())
                drawGrid(g);

            apple.draw(g);
            snake.draw(g);

            drawText(g);
        } else
            gameOver(g);
    }

    private void drawGrid (Graphics g)
    {
        for (int i = 0; i < Config.getScreenHeight() / Config.getUnitSize(); i++)
        {
            g.drawLine(i * Config.getUnitSize(), 0, i * Config.getUnitSize(), Config.getScreenHeight());
            g.drawLine(0, i * Config.getUnitSize(), Config.getScreenWidth(), i * Config.getUnitSize());
        }
    }

    public void checkCollisions ()
    {
        running = !snake.intersects();
    }

    public void gameOver (Graphics g)
    {
        //Game over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics fontMetrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (Config.getScreenWidth() - fontMetrics.stringWidth("Game Over")) / 2,
                Config.getScreenHeight() / 2);

        drawText(g);
    }

    private void drawText (Graphics g)
    {
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics fontMetricsScore = getFontMetrics(g.getFont());
        g.drawString("Score: " + snake.getAppleEaten(),
                (Config.getScreenWidth() - fontMetricsScore.stringWidth("Score: " + snake.getAppleEaten())) / 2,
                g.getFont().getSize());
    }

    public class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed (KeyEvent e)
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
