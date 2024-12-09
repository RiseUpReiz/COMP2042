package com.example.demo.controller;

import com.example.demo.utils.JavaFXTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActiveActorDestructibleTest {

    private ActiveActorDestructible activeActorDestructible;

    @BeforeEach
    void setUp() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            activeActorDestructible = new ActiveActorDestructible("userplane.png", 60, 150.0, 300.0) {
                @Override
                public void updatePosition() {
                    // Stub implementation for testing
                }

                @Override
                public void updateActor() {
                    // Stub implementation for testing
                }

                @Override
                public void takeDamage() {
                    // Simulate taking damage by destroying the actor
                    destroy();
                }
            };
        });
    }

    @Test
    void initializesWithCorrectAttributes() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            assertEquals("userplane.png", activeActorDestructible.getImage().getUrl().substring(activeActorDestructible.getImage().getUrl().lastIndexOf("/") + 1));
            assertEquals(60, activeActorDestructible.getFitHeight());
            assertEquals(150.0, activeActorDestructible.getLayoutX());
            assertEquals(300.0, activeActorDestructible.getLayoutY());
            assertFalse(activeActorDestructible.isDestroyed());
        });
    }

    @Test
    void takesDamageAndDestroysActor() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            assertFalse(activeActorDestructible.isDestroyed());
            activeActorDestructible.takeDamage();
            assertTrue(activeActorDestructible.isDestroyed());
        });
    }

    @Test
    void destroysActorManually() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            assertFalse(activeActorDestructible.isDestroyed());
            activeActorDestructible.destroy();
            assertTrue(activeActorDestructible.isDestroyed());
        });
    }

    @Test
    void canSetAndCheckDestroyedState() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            assertFalse(activeActorDestructible.isDestroyed());
            activeActorDestructible.setDestroyed(true);
            assertTrue(activeActorDestructible.isDestroyed());
            activeActorDestructible.setDestroyed(false);
            assertFalse(activeActorDestructible.isDestroyed());
        });
    }
}
