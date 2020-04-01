package com.github.git_leon.jetpack_survivor_maven;

import com.github.git_leon.greeps.Earth;
import com.github.git_leon.jfoot.system.JFootApplication;
import greenfoot.Greenfoot;
import greenfoot.core.WorldHandler;
import org.junit.Test;

public class DemoAppTest {
    @Test
    public void test() {
        try {
            new JFootApplication().run();
            Greenfoot.setWorld(new Earth());
            Greenfoot.setSpeed(75);
            WorldHandler.getInstance().getWorld();
            Thread.sleep(99999);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
