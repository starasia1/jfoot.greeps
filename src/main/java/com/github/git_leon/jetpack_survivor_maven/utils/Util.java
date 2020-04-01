package com.github.git_leon.jetpack_survivor_maven.utils;

import com.github.git_leon.collectionutils.maps.IntrospectiveMap;
import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;
import greenfoot.GreenfootImage;
import greenfoot.Color;

import java.lang.reflect.Field;

public class Util {

    public static int ran(int maxVal) {
        if (maxVal < 0)
            return -1 * Greenfoot.getRandomNumber(Math.abs(maxVal));
        return Greenfoot.getRandomNumber(maxVal);
    }

    public static boolean chance(int percent) {
        return ran(100) <= percent;
    }



    public static boolean keysDown(String... keys) {
        for (String key : keys)
            if (keyDown(key))
                return true;
        return false;
    }

    public static boolean keyDown(String key) {
        return Greenfoot.isKeyDown(key);
    }

    public static int plusOrMinus() {
        return Util.ran(1) == 0 ? 1 : -1;
    }

    public static boolean compare(String text, String text0) {
        return text.toLowerCase().equals(text0);
    }

    public static String getFieldNamesAndValues(Object o) {
        return new IntrospectiveMap(o).toString();
    }


    public static Field[] getFields(Object obj) {
        return obj.getClass().getFields();
    }

    public static Object[] fieldvals(Object obj) {
        try {
            Field[] fields = getFields(obj);
            Object[] vals = new Object[fields.length];
            for (int i = 0; i < vals.length; i++)
                vals[i] = fields[i].get(obj);
            return vals;
        } catch (IllegalAccessException iae) {
            return null;
        }
    }


    public static class Text {


        public static GreenfootImage getText(String text, int size, Color foreground) {
            return getText(text, size, foreground, Color.BLACK, Color.BLACK);
        }

        public static GreenfootImage getText(String string, int size, Color foreground, Color background, Color outline) {
            return new GreenfootImage(string, size, foreground, background, outline);
        }
    }
}