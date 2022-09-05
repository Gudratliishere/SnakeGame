package com.gudratli.snakegame;

import com.gudratli.snakegame.panel.GamePanel;

import javax.swing.*;

public class GameFrame extends JFrame
{
    public GameFrame ()
    {
        add(new GamePanel());
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
