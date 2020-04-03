package com.github.curriculeon.greeps;

import com.github.curriculeon.greeps.Earth;
import com.github.git_leon.jfoot.system.JFootApplication;
import greenfoot.Greenfoot;
import greenfoot.core.WorldHandler;
import org.junit.Test;

public class DemoAppTest {
    @Test
    public void test() {
        test(3, 999999L);
    }

    private void test(Integer numberOfRounds, Long durationOfRoundsInMilliseconds) {
        test(numberOfRounds, durationOfRoundsInMilliseconds, 50);
    }

    private void test(Integer numberOfRounds, Long durationOfRoundsInMilliseconds, Integer simulationSpeed) {
        try {
            Long timeToEvaluateResultsInMilliseconds = 5000L;
            Long totalSimulationTime = (numberOfRounds * durationOfRoundsInMilliseconds) + timeToEvaluateResultsInMilliseconds;
            totalSimulationTime *=  50 / simulationSpeed ;
            new JFootApplication().run();
            Greenfoot.setWorld(new Earth());
            Greenfoot.setSpeed(simulationSpeed);
            WorldHandler.getInstance().getWorld();
            Thread.sleep(totalSimulationTime);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
