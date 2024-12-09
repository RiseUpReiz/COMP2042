package com.example.demo.planes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.example.demo.projectiles.UserProjectile;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserPlaneTest {

    private UserPlane userPlane;

    @BeforeAll
    static void initToolkit() {
        Platform.startup(() -> {
            // Initialize JavaFX runtime
        });
    }

    @BeforeEach
    void setUp() {
        userPlane = new UserPlane(100); // Ensure health is correctly passed
    }

    @Test
    void initialPositionIsCorrect() {
        assertEquals(5.0, userPlane.getLayoutX());
        assertEquals(300.0, userPlane.getLayoutY());
    }


    @Test
    void moveUpUpdatesPositionCorrectly() {
        userPlane.moveUp();
        userPlane.updatePosition();
        assertTrue(userPlane.getTranslateY() < 0, "TranslateY should decrease when moving up");
    }

    @Test
    void moveDownUpdatesPositionCorrectly() {
        userPlane.moveDown();
        userPlane.updatePosition();
        assertTrue(userPlane.getTranslateY() > 0, "TranslateY should increase when moving down");
    }

    @Test
    void stopResetsVelocityMultiplier() {
        userPlane.moveUp();
        userPlane.stop();
        assertFalse(userPlane.isMoving(), "Plane should not be moving after stop is called");
    }

    @Test
    void fireProjectileCreatesNewProjectile() {
        assertTrue(userPlane.fireProjectile() instanceof UserProjectile, "FireProjectile should return a UserProjectile");
    }

    @Test
    void incrementKillCountIncreasesKillCount() {
        int initialKills = userPlane.getNumberOfKills();
        userPlane.incrementKillCount();
        assertEquals(initialKills + 1, userPlane.getNumberOfKills(), "Kill count should increment by 1");
    }

    @Test
    void positionDoesNotExceedUpperBound() {
        userPlane.moveUp();
        for (int i = 0; i < 100; i++) {
            userPlane.updatePosition();
        }
        assertFalse(userPlane.getY() + userPlane.getTranslateY() >= 70, "Position does not exceed upper bound");
    }

    @Test
    void positionDoesNotExceedLowerBound() {
        userPlane.moveDown();
        for (int i = 0; i < 100; i++) {
            userPlane.updatePosition();
        }
        assertTrue(userPlane.getY() + userPlane.getTranslateY() <= 675.0, "Position should not exceed lower bound");
    }

    @Test
    void isMovingReturnsCorrectValue() {
        assertFalse(userPlane.isMoving(), "Plane should not be moving initially");

        userPlane.moveUp();
        assertTrue(userPlane.isMoving(), "Plane should be moving after moveUp is called");

        userPlane.stop();
        assertFalse(userPlane.isMoving(), "Plane should not be moving after stop is called");
    }

    @Test
    void projectileYPositionIsCalculatedCorrectly() {
        userPlane.setTranslateY(50); // Simulate plane movement
        UserProjectile projectile = (UserProjectile) userPlane.fireProjectile();
        assertEquals(0, projectile.getY(), "Projectile Y position should match calculation");
    }

    @Test
    void velocityMultiplierChangesWithMovementCommands() {
        userPlane.moveUp();
        assertEquals(-1, userPlane.getVelocityMultiplier(), "Velocity multiplier should be -1 for upward movement");

        userPlane.moveDown();
        assertEquals(1, userPlane.getVelocityMultiplier(), "Velocity multiplier should be 1 for downward movement");

        userPlane.stop();
        assertEquals(0, userPlane.getVelocityMultiplier(), "Velocity multiplier should be 0 when movement stops");
    }
}
