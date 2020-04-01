package com.github.git_leon.jetpack_survivor_maven.actors;

import com.github.git_leon.jfoot.sprite.Sprite;
import com.github.git_leon.jfoot.sprite.SpriteCreatorRemover;
import com.github.git_leon.jfoot.sprite.physics.gravity.Gravity;
import com.github.git_leon.jfoot.sprite.physics.gravity.GravityInfluenceeInterface;
import com.github.git_leon.jfoot.system.controls.JFootKey;

public class Rocket extends Sprite {
    private final GravityInfluenceeInterface sprite;

    public Rocket(GravityInfluenceeInterface sprite) {
        super("rocket.png");
        this.sprite = sprite;
    }

    public void act() {
        if(!JFootKey.SPACE.isKeyDown()) {
            new SpriteCreatorRemover(this).destroy();
        } else {
            setLocation(sprite);
            Gravity.ANTI.apply(sprite);
        }
    }
}
