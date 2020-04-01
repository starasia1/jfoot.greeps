package com.github.git_leon.jetpack_survivor_maven.actors.enemies;

import com.github.git_leon.jfoot.sprite.npc.enemy.WeightedEnemy;

public class FiendCrawler extends MyWeightedEnemy {
    public FiendCrawler() {
        super("npc/fiend_crawler/", ".png", 5);
    }
    @Override
    public void animate() {
        animate(2);
    }
}
