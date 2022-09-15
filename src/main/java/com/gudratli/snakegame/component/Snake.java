package com.gudratli.snakegame.component;

import com.gudratli.snakegame.config.Config;
import com.gudratli.snakegame.config.Sound;
import com.gudratli.snakegame.enums.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Snake
{
    private final java.util.List<Dot> dots = new ArrayList<>(Collections.singletonList(new Dot(0, 0)));
    private Direction direction = Direction.RIGHT;
    private int appleEaten;

    public void draw (Graphics g)
    {
        for (int i = 0; i < dots.size(); i++)
        {
            if (i == 0)
                g.setColor(Color.green);
            else
                g.setColor(Color.white);
            g.fillRect(dots.get(i).getX(), dots.get(i).getY(), Config.getUnitSize(), Config.getUnitSize());
        }
    }

    public void move ()
    {
        shift();

        switch (direction)
        {
            case UP:
                dots.get(0).decreaseY(Config.getUnitSize());
                break;
            case DOWN:
                dots.get(0).increaseY(Config.getUnitSize());
                break;
            case LEFT:
                dots.get(0).decreaseX(Config.getUnitSize());
                break;
            case RIGHT:
                dots.get(0).increaseX(Config.getUnitSize());
                break;
        }
    }

    private void shift ()
    {
        for (int i = dots.size() - 1; i > 0; i--)
        {
            dots.get(i).setX(dots.get(i - 1).getX());
            dots.get(i).setY(dots.get(i - 1).getY());
        }
    }

    public void checkApple (Apple apple)
    {
        if (dots.get(0).getX() == apple.getX() && dots.get(0).getY() == apple.getY())
        {
            dots.add(new Dot());
            appleEaten++;
            apple.resetLocation();
            Sound.playSnakeEatApple();
        }
    }

    public int getAppleEaten ()
    {
        return appleEaten;
    }

    public boolean intersects ()
    {
        return intersectsItself() || intersectsBorder();
    }

    private boolean intersectsItself ()
    {
        if (dots.size() == 1)
            return false;

        //check if head collides with body
        for (int i = dots.size() - 1; i > 0; i--)
            if (dots.get(0).getX() == dots.get(i).getX() && dots.get(0).getY() == dots.get(i).getY())
                return true;

        return false;
    }

    private boolean intersectsBorder ()
    {
        return intersectsLeftBorder() || intersectsRightBorder() || intersectsTopBorder() ||
                intersectsBottomBorder();
    }

    private boolean intersectsLeftBorder ()
    {
        //check if head touch left border
        return dots.get(0).getX() < 0;
    }

    private boolean intersectsRightBorder ()
    {
        //check if head touch right border
        return dots.get(0).getX() > Config.getScreenWidth();
    }

    private boolean intersectsTopBorder ()
    {
        //check if head touch top border
        return dots.get(0).getY() < 0;
    }

    private boolean intersectsBottomBorder ()
    {
        //check if head touch bottom border
        return dots.get(0).getY() > Config.getScreenHeight();
    }

    public void changeDirectionToLeft ()
    {
        direction = (direction == Direction.RIGHT) ? direction : Direction.LEFT;
    }

    public void changeDirectionToRight ()
    {
        direction = (direction == Direction.LEFT) ? direction : Direction.RIGHT;
    }

    public void changeDirectionToTop ()
    {
        direction = (direction == Direction.DOWN) ? direction : Direction.UP;
    }

    public void changeDirectionToBottom ()
    {
        direction = (direction == Direction.UP) ? direction : Direction.DOWN;
    }

}
