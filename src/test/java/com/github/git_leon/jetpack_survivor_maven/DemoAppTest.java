package com.github.git_leon.jetpack_survivor_maven;

import com.github.git_leon.greeps.Earth;
import com.github.git_leon.jfoot.system.JFootApplication;
import com.github.git_leon.jetpack_survivor_maven.worlds.MySpriteWorld;
import greenfoot.Greenfoot;
import greenfoot.core.WorldHandler;
import org.junit.Test;

public class DemoAppTest {
    @Test
    public void test() {
        try {
            new JFootApplication().run();
            Greenfoot.setWorld(new Earth());
            WorldHandler.getInstance().getWorld();
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
