package com.gudratli.snakegame.component;

import com.gudratli.snakegame.config.Config;

import java.awt.*;
import java.util.Random;

public class Apple
{
    private static Random random = new Random();
    private Dot dot;

    private Apple (int X, int Y)
    {
        dot = new Dot(X, Y);
    }

    public static Apple generateApple ()
    {
        return new Apple(getRandomX(), getRandomY());
    }

    private static int getRandomX ()
    {
        return random.nextInt(Config.getScreenWidth() / Config.getUnitSize()) * Config.getUnitSize();
    }

    private static int getRandomY ()
    {
        return random.nextInt(Config.getScreenHeight() / Config.getUnitSize()) * Config.getUnitSize();
    }

    public int getX ()
    {
        return dot.getX();
    }

    public void setX (int X)
    {
        dot.setX(X);
    }

    public int getY ()
    {
        return dot.getY();
    }

    public void setY (int Y)
    {
        dot.setY(Y);
    }

    public void draw (Graphics g)
    {
        g.setColor(Color.red);
        g.fillOval(getX(), getY(), Config.getUnitSize(), Config.getUnitSize());
    }

    public void resetLocation ()
    {
        setX(getRandomX());
        setY(getRandomY());
    }
}
