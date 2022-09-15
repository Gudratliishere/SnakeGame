package com.gudratli.snakegame.panel;

import com.gudratli.snakegame.Game;
import com.gudratli.snakegame.config.Config;
import com.gudratli.snakegame.config.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener
{
    private Game game;

    public GamePanel ()
    {
        setPreferredSize(new Dimension(Config.getScreenWidth(), Config.getScreenHeight()));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
        startGame();
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {
        if (game.isRunning() && !game.isPaused())
            game.performActions();

        repaint();
    }

    public void startGame ()
    {
        game = new Game();
        Timer timer = new Timer(Config.getDelay(), this);
        timer.start();
    }

    @Override
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }

    public void draw (Graphics g)
    {
        if (game.isRunning())
        {
            if (Config.getGrid())
                drawGrid(g);

            game.drawGame(g);
            drawScore(g);
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


    public void gameOver (Graphics g)
    {
        //Game over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics fontMetrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (Config.getScreenWidth() - fontMetrics.stringWidth("Game Over")) / 2,
                Config.getScreenHeight() / 2);

        drawScore(g);
    }

    private void drawScore (Graphics g)
    {
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics fontMetricsScore = getFontMetrics(g.getFont());
        g.drawString("Score: " + game.getScore(),
                (Config.getScreenWidth() - fontMetricsScore.stringWidth("Score: " + game.getScore())) / 2,
                g.getFont().getSize());
    }

    private class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed (KeyEvent e)
        {
            game.changeDirection(e);

            if (e.getKeyCode() == KeyEvent.VK_SPACE)
                if (game.isRunning())
                    game.togglePaused();
                else
                    game = new Game();
        }
    }
}
