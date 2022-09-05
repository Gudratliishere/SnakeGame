package com.gudratli.snakegame.component;

public class Dot
{
    private int X;
    private int Y;

    public Dot ()
    {
    }

    public Dot (int x, int y)
    {
        X = x;
        Y = y;
    }

    public int getX ()
    {
        return X;
    }

    public int getY ()
    {
        return Y;
    }

    public void setX (int x)
    {
        X = x;
    }

    public void setY (int y)
    {
        Y = y;
    }

    public void increaseX (int size)
    {
        X += size;
    }

    public void increaseY (int size)
    {
        Y += size;
    }

    public void decreaseX (int size)
    {
        X -= size;
    }

    public void decreaseY (int size)
    {
        Y -= size;
    }
}
