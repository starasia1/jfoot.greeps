package com.github.git_leon.jetpack_survivor_maven.actors.userinterface.gamewindow;

import com.github.git_leon.jfoot.resources.JFootTextImageBuilder;
import com.github.git_leon.jfoot.system.controls.JFootKey;
import com.github.git_leon.jfoot.world.EmptyWorld;
import greenfoot.Color;
import greenfoot.Greenfoot;

public class GameOverWindow extends TextWindow {
    public GameOverWindow() {
        super(new JFootTextImageBuilder()
                .setBackground(Color.BLACK)
                .setForeground(Color.WHITE)
                .setOutline(Color.GREEN)
                .setSize(30)
                .setString("Game Over!\nPress [ Space ] To Continue"));
    }

    @Override
    public void act() {
        JFootKey.SPACE.onKeyPress(Greenfoot::setWorld, new EmptyWorld());
    }
}
