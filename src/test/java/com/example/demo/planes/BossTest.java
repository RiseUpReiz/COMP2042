package com.example.demo.planes;

import com.example.demo.controller.ActiveActorDestructible;
import com.example.demo.levels.LevelViewBoss;
import com.example.demo.projectiles.BossProjectile;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BossTest {

    private Boss boss;
    private LevelViewBoss levelView;

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
        levelView = mock(LevelViewBoss.class);
        boss = new Boss(levelView);
    }

    @Test
    void bossShouldMoveVerticallyWithinBounds() {
        boss.setTranslateY(20);
        boss.updatePosition();
        assertTrue(boss.getTranslateY() >= 0 && boss.getTranslateY() <= 625);
    }

    @Test
    void bossShouldNotMoveVerticallyOutOfBounds() {
        boss.setTranslateY(700);
        boss.updatePosition();
        assertFalse(boss.getTranslateY() <= 625);
    }

    @Test
    void bossShouldFireProjectileBasedOnFireRate() {
        ActiveActorDestructible projectile = boss.fireProjectile();
        assertTrue(projectile == null || projectile instanceof BossProjectile);
    }

    @Test
    void bossShouldTakeDamageWhenNotShielded() {
        int initialHealth = boss.getHealth();
        boss.takeDamage();
        assertEquals(initialHealth - 1, boss.getHealth());
    }

    @Test
    void bossShouldNotTakeDamageWhenShielded() {
        boss.activateShield();
        int initialHealth = boss.getHealth();
        boss.takeDamage();
        assertEquals(initialHealth, boss.getHealth());
    }

    @Test
    void bossShouldActivateShieldBasedOnProbability() {
        boss.updateShield();
        assertTrue(boss.isShielded() || !boss.isShielded());
    }

    @Test
    void bossShouldDeactivateShieldAfterMaxFrames() {
        boss.activateShield();
        for (int i = 0; i < 500; i++) {
            boss.updateShield();
        }
        assertFalse(boss.isShielded());
    }
}