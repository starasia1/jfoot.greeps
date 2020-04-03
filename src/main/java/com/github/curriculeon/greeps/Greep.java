package com.github.curriculeon.greeps;

import com.github.git_leon.RandomUtils;
import greenfoot.GreenfootImage;

import java.util.List;

/**
 * A Greep is an alien creature that likes to collect tomatoes.
 *
 * @author (your name here)
 * @version 0.1
 */
public class Greep extends Creature {
    /**
     * Create a creature at its ship.
     *
     * @param ship
     */
    public Greep(Spaceship ship) {
        super(ship);
        setImage(getCurrentImage());
    }

    @Override
    protected void behave() {
        if (isCarryingTomato()) {
            if (isAtShip()) {
                dropTomato();
            } else {
                turnTowardsHome();
                move();
            }
        } else {
            move();
            checkFood();
        }
    }


    public Boolean isWaitingForAssistance() {
        return isAtTomatoes() && !isCarryingTomato();
    }


    public Boolean isWaitingToAssist() {
        if (isAtTomatoes()) {
            for (Greep greep : getSurroundTomatoPile().getIntersectingObjects(Greep.class)) {
                if (!greep.isCarryingTomato()) {
                    return true;
                }
            }
        }
        return false;
    }


    public void waitForTomatoLoadingAssistance() {
        turnTowards(getSurroundTomatoPile());
        move();
        loadTomato();
    }


    public Boolean isReturningToShip() {
        return isCarryingTomato();
    }


    public void returnToShip() {
        turnTowardsHome(3);
        move();
    }


    public Boolean shouldSeekTomatoPile() {
        return !isCarryingTomato();
    }


    public void seekTomatoPile() {
        move();
    }


    public void turnRandomDegrees() {
        turnRandomDegrees(15, 90);
    }


    public void turnRandomDegrees(int minimumTurn, int maximumTurn) {
        turn(RandomUtils.createInteger(minimumTurn, maximumTurn));
    }


    public void turnRandomly() {
        turnRandomly(-15, -90, 0);
    }


    public void turnRandomly(int minimumTurn, int maximumTurn, float likelihoodOfTurn) {
        if (RandomUtils.createBoolean(likelihoodOfTurn)) {
            turnRandomDegrees(minimumTurn, maximumTurn);
        }
    }

    /**
     * Is there any food here where we are? If so, try to load some!
     */

    public void checkFood() {
        // check whether there's a tomato pile here
        if (isAtTomatoes()) {
            loadTomato();
            // Note: this attempts to load a tomato onto *another* Greep. It won't
            // do anything if we are alone here.
        }
    }


    /**
     * This method specifies the image we want displayed at any time. (No need
     * to change this for the competition.)
     */

    public String getCurrentImage() {
        if (isCarryingTomato())
            return "greep-with-food.png";
        else
            return "greep.png";
    }

    /**
     * Create a Greep with its home space ship.
     */

    public static String getAuthorName() {
        return "Anonymous";
    }


    public List<GreenfootImage> getImageList() {
        return null;
    }
}