package com.github.git_leon.jetpack_survivor_maven.actors.enemies;

import com.github.git_leon.jfoot.sprite.npc.enemy.WeightedEnemy;

public class OrcRider extends MyWeightedEnemy {
    public OrcRider() {
        super(  "npc/orc_rider/", ".png", 2);
    }

    @Override
    public void animate() {
        animate(5);
    }

}
