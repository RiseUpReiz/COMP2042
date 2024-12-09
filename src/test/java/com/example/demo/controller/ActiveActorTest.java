package com.example.demo.controller;

import com.example.demo.utils.JavaFXTestUtils;
import javafx.application.Platform;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActiveActorTest {

    private ActiveActor activeActor;

    @BeforeEach
    void setUp() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            activeActor = new ActiveActor("userplane.png", 50, 100.0, 200.0) {
                @Override
                public void updatePosition() {
                    // Implementation for testing
                }
            };
        });
    }

    @Test
    void initializesWithCorrectAttributes() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            assertEquals("userplane.png", activeActor.getImage().getUrl().substring(activeActor.getImage().getUrl().lastIndexOf("/") + 1));
            assertEquals(50, activeActor.getFitHeight());
            assertEquals(100.0, activeActor.getLayoutX());
            assertEquals(200.0, activeActor.getLayoutY());
        });
    }

    @Test
    void movesHorizontallyCorrectly() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            activeActor.moveHorizontally(50.0);
            assertEquals(50.0, activeActor.getTranslateX());
        });
    }

    @Test
    void movesVerticallyCorrectly() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            activeActor.moveVertically(50.0);
            assertEquals(50.0, activeActor.getTranslateY());
        });
    }

    @Test
    void doesNotMoveHorizontallyWhenAmountIsZero() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            activeActor.moveHorizontally(0.0);
            assertEquals(0.0, activeActor.getTranslateX());
        });
    }

    @Test
    void doesNotMoveVerticallyWhenAmountIsZero() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            activeActor.moveVertically(0.0);
            assertEquals(0.0, activeActor.getTranslateY());
        });
    }

    @Test
    void movesHorizontallyWithNegativeAmount() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            activeActor.moveHorizontally(-50.0);
            assertEquals(-50.0, activeActor.getTranslateX());
        });
    }

    @Test
    void movesVerticallyWithNegativeAmount() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            activeActor.moveVertically(-50.0);
            assertEquals(-50.0, activeActor.getTranslateY());
        });
    }
}
