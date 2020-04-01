package com.github.git_leon.jetpack_survivor_maven.actors.userinterface.gamewindow;

import com.github.git_leon.jfoot.sprite.SpriteCreatorRemover;
import com.github.git_leon.jfoot.system.controls.JFootKey;
import com.github.git_leon.jfoot.resources.JFootTextImageBuilder;
import greenfoot.Color;
import greenfoot.Greenfoot;

public class PauseWindow extends TextWindow {
    private int numberOfTimesReleased;
    private boolean isKeyDown;

    public PauseWindow() {
        super(new JFootTextImageBuilder()
                .setBackground(Color.BLACK)
                .setForeground(Color.WHITE)
                .setOutline(Color.YELLOW)
                .setSize(30)
                .setString("PAUSE"));
    }

    public void act() {
        while (true) {
            Greenfoot.delay(1);
            if (!JFootKey.P.isKeyDown()) {
                numberOfTimesReleased++;
            }
            if (JFootKey.P.isKeyDown()) {
                this.isKeyDown = true;
            }
            if (isKeyDown && numberOfTimesReleased > 1) {
                break;
            }
        }
        new SpriteCreatorRemover(this).destroy();
    }
}
