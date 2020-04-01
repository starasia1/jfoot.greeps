package com.github.git_leon.jetpack_survivor_maven.actors.enemies;

import com.github.git_leon.jfoot.sprite.npc.enemy.WeightedEnemy;

public class ZombieCrawler extends MyWeightedEnemy {
    public ZombieCrawler() {
        super("npc/zombie_crawler/", ".png", 12);
        flipImagesHorizontally();
    }


    @Override
    public void animate() {
        animate(3);
    }
}
