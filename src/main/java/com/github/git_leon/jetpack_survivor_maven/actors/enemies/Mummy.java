package com.github.git_leon.jetpack_survivor_maven.actors.enemies;

import com.github.git_leon.jfoot.sprite.npc.enemy.WeightedEnemy;

public class Mummy extends MyWeightedEnemy {
    public Mummy(){
        super(    "npc/mummy/", ".png", 17);
        flipImagesHorizontally();
    }

}
