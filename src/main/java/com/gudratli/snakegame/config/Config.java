package com.gudratli.snakegame.config;

import org.ini4j.Ini;
import org.ini4j.IniPreferences;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.prefs.Preferences;

public class Config
{
    private static final String configFile = "src\\main\\resources\\config.ini";
    private static int screenWidth;
    private static int screenHeight;
    private static int unitSize;
    private static int delay;
    private static Boolean grid;

    public static int getScreenWidth ()
    {
        if (screenWidth == 0)
            try
            {
                screenWidth = Integer.parseInt(readIni("size", "screenWidth"));
            } catch (IOException e)
            {
                screenWidth = 600;
            }

        return screenWidth;
    }

    public static int getScreenHeight ()
    {
        if (screenHeight == 0)
            try
            {
                screenHeight = Integer.parseInt(readIni("size", "screenHeight"));
            } catch (IOException e)
            {
                screenHeight = 600;
            }

        return screenHeight;
    }

    public static int getUnitSize ()
    {
        if (unitSize == 0)
            try
            {
                unitSize = Integer.parseInt(readIni("size", "unitSize"));
            } catch (IOException e)
            {
                unitSize = 25;
            }

        return unitSize;
    }

    public static int getDelay ()
    {
        if (delay == 0)
            try
            {
                delay = Integer.parseInt(readIni("size", "delay"));
            } catch (IOException e)
            {
                delay = 25;
            }

        return delay;
    }

    public static boolean getGrid ()
    {
        if (grid == null)
            try
            {
                grid = Boolean.valueOf(readIni("layout", "grid"));
            } catch (IOException e)
            {
                grid = true;
            }

        return grid;
    }

    public static String readIni (String node, String key) throws IOException
    {
        Ini ini = new Ini(getConfigFile());
        Preferences preferences = new IniPreferences(ini);
        return preferences.node(node).get(key, null);
    }

    private static File getConfigFile () throws IOException
    {
        File file = new File(configFile);
        if (!file.exists())
            file.createNewFile();

        return file;
    }
}
