package com.example.demo.projectiles;

import com.example.demo.controller.Main;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserProjectileTest {

    private UserProjectile userProjectile;

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
        userProjectile = new UserProjectile(0.0, 0.0);
    }

    @Test
    void constructorShouldSetInitialPositionCorrectly() {
        assertEquals(0.0, userProjectile.getTranslateX());
        assertEquals(0.0, userProjectile.getTranslateY());
    }

    @Test
    void updatePositionShouldMoveProjectileHorizontally() {
        userProjectile.updatePosition();
        assertEquals(15.0, userProjectile.getTranslateX());
        assertEquals(0.0, userProjectile.getTranslateY());
    }

    @Test
    void updateActorShouldDestroyProjectileIfOutOfScreen() {
        userProjectile.setTranslateX(Main.getScreenWidth() + 1); // Move it off-screen
        userProjectile.updateActor();
        assertTrue(userProjectile.isDestroyed());
    }

    @Test
    void updateActorShouldNotDestroyProjectileIfNotOutOfScreen() {
        userProjectile.setTranslateX(Main.getScreenWidth() - 1); // Keep it on-screen
        userProjectile.updateActor();
        assertTrue(userProjectile.isDestroyed());
    }

    @Test
    void outOfScreenShouldReturnTrueIfProjectileIsOutOfScreen() {
        userProjectile.setTranslateX(Main.getScreenWidth() + 1);
        assertTrue(userProjectile.outOfScreen());
    }

    @Test
    void outOfScreenShouldReturnFalseIfProjectileIsNotOutOfScreen() {
        userProjectile.setTranslateX(Main.getScreenWidth() - 1);
        assertFalse(userProjectile.outOfScreen());
    }
}
