package com.github.git_leon.jetpack_survivor_maven.actors.enemies;

import com.github.git_leon.jfoot.sprite.npc.enemy.WeightedEnemy;

public class ZombieGunner extends MyWeightedEnemy {
    public ZombieGunner() {
        super("npc/zombie_gunner/", ".png", 13);
        flipImagesHorizontally();
    }
}
