package com.github.git_leon.jetpack_survivor_maven.actors.enemies;

import com.github.git_leon.jetpack_survivor_maven.actors.Platform;
import com.github.git_leon.jfoot.sprite.npc.enemy.WeightedEnemy;

/**
 * @author leonhunter
 * @created 03/31/2020 - 11:36 PM
 */
public class MyWeightedEnemy extends WeightedEnemy {
    public MyWeightedEnemy(String basename, String suffix, int noOfImages) {
        super(basename, suffix, noOfImages);
    }

    @Override
    public boolean isOnGround() {
        return getOneIntersectingObject(Platform.class) != null;
    }
}
