package com.github.git_leon.jetpack_survivor_maven;

import com.github.git_leon.jfoot.system.JFootApplication;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class DemoApp   {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        new JFootApplication().run();
    }
}
