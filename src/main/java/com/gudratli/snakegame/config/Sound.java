package com.gudratli.snakegame.config;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound
{
    public static final String appleEatenSoundPath = "/sounds/blob.wav";
    public static final String gameOverSoundPath = "/sounds/game_over.wav";

    public static void playSnakeEatApple ()
    {
        playSound(appleEatenSoundPath);
    }

    public static void playGameOver ()
    {
        playSound(gameOverSoundPath);
    }

    private static void playSound (String filePath)
    {
        URL url = Sound.class.getResource(filePath);
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url)) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            e.printStackTrace();
        }
    }
}
