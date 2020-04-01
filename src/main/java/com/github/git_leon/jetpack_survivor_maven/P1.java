package com.github.git_leon.jetpack_survivor_maven;

import com.github.git_leon.jfoot.sprite.AnimatedSprite;
import com.github.git_leon.jfoot.sprite.SpriteCreatorRemover;
import com.github.git_leon.jfoot.sprite.SpriteSensorDecorator;
import com.github.git_leon.jetpack_survivor_maven.actors.Platform;
import com.github.git_leon.jetpack_survivor_maven.actors.Rocket;
import com.github.git_leon.jfoot.sprite.items.weapon.range.HandGun;
import com.github.git_leon.jetpack_survivor_maven.actors.userinterface.gamewindow.PauseWindow;
import com.github.git_leon.jfoot.sprite.npc.ally.AllyInterface;
import com.github.git_leon.jfoot.sprite.physics.gravity.Gravity;
import com.github.git_leon.jfoot.sprite.physics.gravity.GravityInfluenceeInterface;
import com.github.git_leon.jfoot.system.controls.JFootKey;

public class P1 extends AnimatedSprite implements GravityInfluenceeInterface, AllyInterface {
    private final SpriteCreatorRemover spriteCreator;
    private final HandGun gun;
    private Rocket rocket;
    private int runSpeed;
    private float verticalSpeed;
    private boolean flippedHorizontally;

    public P1() {
        super("player/walk/walk", ".png", 6);
        this.runSpeed = 2;
        this.gun = new HandGun(this);
        this.spriteCreator = new SpriteCreatorRemover(this);
        this.rocket = new Rocket(this);
    }

    @Override
    public final void animate() {
        if (JFootKey.isAnyKeyDown(
                JFootKey.LEFT,
                JFootKey.RIGHT,
                JFootKey.UP,
                JFootKey.DOWN)) {
            super.animate(3);
        }
    }

    @Override
    public final void postAnimationBehavior() {
        controls();
        Gravity.applyNormal(this);
    }

    @Override
    public Double getVerticalSpeed() {
        return Double.valueOf(verticalSpeed);
    }

    @Override
    public void setVerticalSpeed(Double aDouble) {

    }

    @Override
    public float getTerminalSpeed() {
        return 3;
    }

    @Override
    public boolean isOnGround() {
        return getOneIntersectingObject(Platform.class) != null;
    }

    @Override
    public void moveRight(int xOffset) {
        super.moveRight(xOffset);
        if (!this.flippedHorizontally) {
            flipImagesHorizontally();
        }
    }

    @Override
    public void moveLeft(int xOffset) {
        super.moveLeft(xOffset);
        if (this.flippedHorizontally) {
            flipImagesHorizontally();
        }
    }

    public void controls() {
        JFootKey.SPACE.onKeyPress(this::createRocket);
        JFootKey.DOWN.onKeyPress(gun::shoot);
        JFootKey.LEFT.onKeyPress(this::moveLeft, runSpeed);
        JFootKey.RIGHT.onKeyPress(this::moveRight, runSpeed);
        JFootKey.P.onKeyPress(this::createPauseWindow);

        if (isOnGround() && JFootKey.UP.isKeyDown()) {
            jump();
        }
    }

    private void createPauseWindow() {
        if (new SpriteSensorDecorator(this).getNearest(PauseWindow.class) == null) {
            spriteCreator.add(new PauseWindow());
        }
    }

    private void pause() {
        if (JFootKey.P.isKeyDown()) {

        }
    }

    private void createRocket() {
        if (!rocket.isAddedToWorld()) {
            spriteCreator.add(rocket);
        }
    }

    private void jump() {
        Gravity.ANTI.apply(this, 15);
    }
}
