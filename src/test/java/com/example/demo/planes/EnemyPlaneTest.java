package com.example.demo.planes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.demo.controller.ActiveActorDestructible;
import com.example.demo.projectiles.EnemyProjectile;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EnemyPlaneTest {

    @BeforeAll
    static void initToolkit() {
        Platform.startup(() -> {
            // Toolkit initialized
        });
    }

    @Test
    void initialHealthIsSetCorrectly() {
        EnemyPlane enemyPlane = new EnemyPlane(100.0, 200.0);
        assertEquals(1, enemyPlane.getHealth());
    }

    @Test
    void updatePositionMovesPlaneHorizontally() {
        EnemyPlane enemyPlane = new EnemyPlane(100.0, 200.0);
        enemyPlane.updatePosition();
        assertEquals(-6, enemyPlane.getTranslateX());
    }

    @Test
    void fireProjectileReturnsProjectileWhenConditionMet() {
        EnemyPlane enemyPlane = new EnemyPlane(100.0, 200.0);
        EnemyPlane.FIRE_RATE = 1.0; // Ensure projectile is fired
        ActiveActorDestructible projectile = enemyPlane.fireProjectile();
        assertNotNull(projectile);
        assertEquals(EnemyProjectile.class, projectile.getClass());
    }

    @Test
    void fireProjectileReturnsNullWhenConditionNotMet() {
        EnemyPlane enemyPlane = new EnemyPlane(100.0, 200.0);
        EnemyPlane.FIRE_RATE = 0.0; // Ensure projectile is not fired
        ActiveActorDestructible projectile = enemyPlane.fireProjectile();
        assertNull(projectile);
    }

    @Test
    void updateActorUpdatesPosition() {
        EnemyPlane enemyPlane = new EnemyPlane(100.0, 200.0);
        enemyPlane.updateActor();
        assertEquals(-6, enemyPlane.getTranslateX());
    }
}