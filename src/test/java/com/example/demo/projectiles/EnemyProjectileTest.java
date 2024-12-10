package com.example.demo.projectiles;

import com.example.demo.controller.Main;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyProjectileTest {

    private EnemyProjectile enemyProjectile;

    @BeforeAll
    static void initToolkit() {
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {
                // Toolkit initialized
            });
        }
    }

    @BeforeEach
    void setUp() {
        enemyProjectile = new EnemyProjectile(0, 0);
    }

    @Test
    void enemyProjectileShouldMoveHorizontallyWhenPositionIsUpdated() {
        enemyProjectile.updatePosition();
        assertEquals(-10, enemyProjectile.getTranslateX());
    }

    @Test
    void enemyProjectileShouldBeDestroyedWhenOutOfScreen() {
        enemyProjectile.setTranslateX(Main.getScreenWidth() + 1);
        enemyProjectile.updateActor();
        assertFalse(enemyProjectile.isDestroyed());
    }

    @Test
    void enemyProjectileShouldNotBeDestroyedWhenNotOutOfScreen() {
        enemyProjectile.setTranslateX(Main.getScreenWidth() - 1);
        enemyProjectile.updateActor();
        assertFalse(enemyProjectile.isDestroyed());
    }

    @Test
    void enemyProjectileShouldReturnTrueWhenOutOfScreen() {
        enemyProjectile.setTranslateX(Main.getScreenWidth() + 1);
        assertTrue(enemyProjectile.outOfScreen());
    }

    @Test
    void enemyProjectileShouldReturnFalseWhenNotOutOfScreen() {
        enemyProjectile.setTranslateX(Main.getScreenWidth() - 1);
        assertFalse(enemyProjectile.outOfScreen());
    }
}