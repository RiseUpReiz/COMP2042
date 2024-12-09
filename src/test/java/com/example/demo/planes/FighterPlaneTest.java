package com.example.demo.planes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.controller.ActiveActorDestructible;
import com.example.demo.projectiles.UserProjectile;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FighterPlaneTest {

    @BeforeAll
    static void initToolkit() {
        Platform.startup(() -> {
            // Toolkit initialized
        });
    }
    @Test
    void initialHealthIsSetCorrectly() {
        FighterPlane fighterPlane = new FighterPlaneImpl("userplane.png", 50, 100.0, 200.0, 5);
        assertEquals(5, fighterPlane.getHealth());
    }

    @Test
    void takeDamageDecreasesHealth() {
        FighterPlane fighterPlane = new FighterPlaneImpl("userplane.png", 50, 100.0, 200.0, 5);
        fighterPlane.takeDamage();
        assertEquals(4, fighterPlane.getHealth());
    }

    @Test
    void takeDamageDestroysPlaneWhenHealthReachesZero() {
        FighterPlane fighterPlane = new FighterPlaneImpl("userplane.png", 50, 100.0, 200.0, 1);
        fighterPlane.takeDamage();
        assertTrue(fighterPlane.isDestroyed());
    }

    @Test
    void getProjectileXPositionCalculatesCorrectly() {
        FighterPlane fighterPlane = new FighterPlaneImpl("userplane.png", 50, 100.0, 200.0, 5);
        assertEquals(110.0, fighterPlane.getProjectileXPosition(10.0));
    }

    @Test
    void getProjectileYPositionCalculatesCorrectly() {
        FighterPlane fighterPlane = new FighterPlaneImpl("userplane.png", 50, 100.0, 200.0, 5);
        assertEquals(210.0, fighterPlane.getProjectileYPosition(10.0));
    }

    // FighterPlaneImpl is a concrete implementation of the abstract FighterPlane class for testing purposes.
    class FighterPlaneImpl extends FighterPlane {
        public FighterPlaneImpl(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
            super(imageName, imageHeight, initialXPos, initialYPos, health);
        }

        @Override
        public ActiveActorDestructible fireProjectile() {
            double projectileX = getProjectileXPosition(110.0);
            double projectileY = getProjectileYPosition(20.0);
            return new UserProjectile(projectileX, projectileY);
        }

        @Override
        public void updatePosition() {
            // Implement the method as required
        }

        @Override
        public void updateActor() {
            // Implement the method as required
        }
    }
}