package com.github.git_leon.jetpack_survivor_maven;

import com.github.git_leon.RandomUtils;
import com.github.git_leon.jetpack_survivor_maven.actors.enemies.*;
import com.github.git_leon.jfoot.sprite.npc.enemy.Enemy;

import java.util.function.Supplier;

public enum EnemyGenerator {
    BAT(Bat::new),
    MUMMY(Mummy::new),
    FIEND_CRAWLER(FiendCrawler::new),
    ORC_RIDER(OrcRider::new),
    ZOMBIE_CRAWLER(ZombieCrawler::new),
    ZOMBIE_GUNNER(ZombieGunner::new);
    private final Supplier<Enemy> enemySupplier;

    EnemyGenerator(Supplier<Enemy> enemySupplier) {
        this.enemySupplier = enemySupplier;
    }

    public Supplier<Enemy> getEnemySupplier() {
        return enemySupplier;
    }

    public static Enemy getRandom() {
        return RandomUtils.selectElement(values()).enemySupplier.get();
    }
}
