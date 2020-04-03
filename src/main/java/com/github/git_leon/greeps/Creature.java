package com.github.git_leon.greeps;

import com.github.git_leon.RandomUtils;
import greenfoot.Actor;
import greenfoot.Greenfoot;

import java.util.List;

/**
 * A Creature is the base class for all alien beings in this scenario. It
 * provides the basic abilities of creatures in this world.
 *
 * @author Michael Kölling
 * @version 2.0
 */
public abstract class Creature extends Actor {
    private static final double WALKING_SPEED = 5.0;
    private static final int TIME_TO_SPIT = 10;
    private boolean carryingTomato = false; // Indicate whether we have a tomato with us
    private Spaceship ship; // The creature's home ship
    private boolean moved = false;
    private int timeToSpit = 0;
    private int memory; // General purpose memory
    private boolean[] states; //General purpose memory

    /**
     * Create a creature at its ship.
     */
    public Creature(Spaceship ship) {
        this.ship = ship;
        this.states = new boolean[2];
        setRotation(RandomUtils.createInteger(0, 360));
    }

    abstract public String getCurrentImage();

    abstract protected void behave();

    @Override
    public final void setImage(String imageName) {
        super.setImage(new StringBuilder()
                .append(System.getProperty("user.dir"))
                .append("/src/main/resources/images/")
                .append(imageName)
                .toString());
    }

    /**
     * Act - must be called as part of subclass act. This ensures single
     * movement in each act round.
     */
    @Override
    public final void act() {
        moved = false;
        behave();
    }


    /**
     * To avoid confusion/cheating, calling the built-in Greenfoot
     * move(int amount) does the same as the Creature move() function.
     */
    @Override
    public final void move(int amount) {
        move();
    }


    /**
     * Turn in the direction of the specified `actor`
     *
     * @param actor - Actor to turn towards
     */
    public void turnTowards(Actor actor) {
        super.turnTowards(actor.getX(), actor.getY());
    }


    /**
     *
     */
    public void turnTowardsHome(float likelihoodOfTurn) {
        if (RandomUtils.createBoolean(likelihoodOfTurn)) {
            turnTowards(ship);
        }
    }

    /**
     * True if we are at our space ship.
     */
    public final boolean isAtShip() {
        return getOneIntersectingObject(Spaceship.class) != null;
    }

    /**
     * Return true if we have found food at our current location.
     */
    public final boolean isAtTomatoes() {
        return getSurroundTomatoPile() != null;
    }


    public TomatoPile getSurroundTomatoPile() {
        return (TomatoPile) getOneIntersectingObject(TomatoPile.class);
    }


    /**
     * Return true if we have just seen water in front of us.
     */
    public final boolean isAtWater() {
        return ((Earth) getWorld()).isWater(getNextXCoordinate(), getNextYCoordinate());
    }

    /**
     * Test if we are close to one of the edges of the world. Return true if we are.
     */
    public boolean isAtWorldEdge() {
        if (getX() < 3 || getX() > getWorld().getWidth() - 3)
            return true;
        if (getY() < 3 || getY() > getWorld().getHeight() - 3)
            return true;
        else
            return false;
    }

    /**
     * Check whether we can see paint of a given color where we are sitting.
     */
    public boolean isSeeingPaint(String color) {
        List paintDrops = getIntersectingObjects(Paint.class);
        for (Object obj : paintDrops) {
            if (((Paint) obj).getColor().equals(color)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieve the value of a flag. 'flagNo' can be 1 or 2.
     */
    public boolean isFlaggedForState(int state) {
        if (state < 1 || state > 2)
            throw new IllegalArgumentException("flag number must be either 1 or 2");
        else
            return states[state - 1];
    }


    /**
     * Check whether we are carrying a tomato.
     */
    public final boolean isCarryingTomato() {
        return carryingTomato;
    }

    /**
     * Remove the tomato currently carried (and return true). Return
     * false if we were not carrying one.
     */
    public final boolean removeTomato() {
        if (carryingTomato) {
            carryingTomato = false;
            return true;
        } else
            return false;
    }


    /**
     * Receive a tomato and carry it.
     */
    private void carryTomato() {
        carryingTomato = true;
        setImage(getCurrentImage());
    }


    /**
     * Drop the tomato we are carrying. If we are at the ship, it is counted.
     * If not, it's just gone...
     */
    protected final void dropTomato() {
        if (!carryingTomato)
            return;

        if (isAtShip()) {
            ship.storeTomato(this);
        }
        carryingTomato = false;
        setImage(getCurrentImage());
    }


    /**
     * Load a tomato onto *another* creature. This works only if there is another creature
     * and a tomato pile present, otherwise this method does nothing.
     */
    public final void loadTomato() {
        // check whether there's a tomato pile here
        TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
        // check whether there's another creature here
        Creature greep = (Creature) getOneIntersectingObject(Creature.class);

        if (greep != null && tomatoes != null) {
            if (!greep.isCarryingTomato()) {
                tomatoes.takeOne();
                greep.carryTomato();
            }
        }
    }


    /**
     * Spit a drop of paint onto the ground. We can spit in three colors: "red", "orange",
     * and "purple". (All other strings will be mapped to one of these.)
     */
    public void spit(String color) {
        if (timeToSpit == 0) {
            Paint paint = new Paint(color);
            getWorld().addObject(paint, getX(), getY());
            timeToSpit = TIME_TO_SPIT + Greenfoot.getRandomNumber(10);
        }
    }


    /**
     * Store a user defined value. Attention: even though the parameter type is int,
     * only byte size values (0 <= val <= 255) are accepted.
     */
    public void setMemory(int val) {
        if (val < 0 || val > 255)
            throw new IllegalArgumentException("memory value must be in range [0..255]");
        else
            memory = val;
    }


    /**
     * Retrieve a previously stored value.
     */
    public int getMemory() {
        return memory;
    }


    /**
     * Store a user defined boolean value (a "flag"). Two flags are available,
     * i.e. 'flagNo' may be 1 or 2.
     */
    public void setFlag(int flagNo, boolean val) {
        if (flagNo < 1 || flagNo > 2)
            throw new IllegalArgumentException("flag number must be either 1 or 2");
        else
            states[flagNo - 1] = val;
    }


    /**
     * Move forward roughly in the current direction. Sometimes we get a
     * little off course.
     */
    public void move() {
        if (moved)   // can move only once per 'act' round
            return;

        // there's a 3% chance that we randomly turn a little off course
        if (RandomUtils.createBoolean(3)) {
            turn((Greenfoot.getRandomNumber(3) - 1) * 10);
        }

        if (!isAtWater()) {
            setLocation(getNextXCoordinate(), getNextYCoordinate());
        }

        if (timeToSpit > 0)
            timeToSpit--;

        moved = true;
    }

    public Boolean canMove() {
        return getNextXCoordinate() != getX() && getNextYCoordinate() != getY();
    }

    public int getNextXCoordinate() {
        double angle = Math.toRadians(getRotation());
        int literalX = (int) Math.round(getX() + Math.cos(angle) * WALKING_SPEED);
        int x = (int) Math.round(getX() + Math.cos(angle) * WALKING_SPEED+2);

        if (x >= getWorld().getWidth()) {
            literalX = getWorld().getWidth() - 1;
        }
        if (x < 0) {
            literalX = 0;
        }
        return literalX;
    }

    public int getNextYCoordinate() {
        double angle = Math.toRadians(getRotation());
        int literalY = (int) Math.round(getY() + Math.sin(angle) * WALKING_SPEED);
        int y = (int) Math.round(getY() + Math.sin(angle) * WALKING_SPEED+2);

        if (y >= getWorld().getHeight()) {
            literalY = getWorld().getHeight() - 1;
        }
        if (y < 0) {
            literalY = 0;
        }
        return literalY;
    }
}
