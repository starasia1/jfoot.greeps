package com.github.git_leon.jetpack_survivor_maven;

import com.github.git_leon.greeps.Earth;
import com.github.git_leon.jfoot.system.JFootApplication;
import greenfoot.Greenfoot;
import greenfoot.core.WorldHandler;
import org.junit.Test;

public class DemoAppTest {
    @Test
    public void test() {
        test(3, 12000L);
    }

    private void test(Integer numberOfRounds, Long durationOfRoundsInMilliseconds) {
        test(numberOfRounds, durationOfRoundsInMilliseconds, 50);
    }

    private void test(Integer numberOfRounds, Long durationOfRoundsInMilliseconds, Integer simulationSpeed) {
        try {
            Long timeToEvaluateResultsInMilliseconds = 1500L;
            Long totalSimulationTime = (numberOfRounds * durationOfRoundsInMilliseconds) + timeToEvaluateResultsInMilliseconds;
            new JFootApplication().run();
            Greenfoot.setWorld(new Earth());
            Greenfoot.setSpeed(50);
            WorldHandler.getInstance().getWorld();
            Thread.sleep(totalSimulationTime);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
