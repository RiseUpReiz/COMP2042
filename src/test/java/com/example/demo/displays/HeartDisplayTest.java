package com.example.demo.displays;

import com.example.demo.utils.JavaFXTestUtils;
import javafx.scene.layout.HBox;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeartDisplayTest {

    private HeartDisplay heartDisplay;

    @BeforeAll
    static void setupJavaFX() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {});
    }

    @BeforeEach
    void setUp() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> heartDisplay = new HeartDisplay(100, 100, 3));
    }

    @Test
    void initializesWithCorrectNumberOfHearts() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            HBox container = heartDisplay.getContainer();
            assertEquals(3, container.getChildren().size());
        });
    }

    @Test
    void removesHeartSuccessfully() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            heartDisplay.removeHeart();
            HBox container = heartDisplay.getContainer();
            assertEquals(2, container.getChildren().size());
        });
    }

    @Test
    void doesNotRemoveHeartWhenEmpty() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            heartDisplay.removeHeart();
            heartDisplay.removeHeart();
            heartDisplay.removeHeart();
            heartDisplay.removeHeart(); // Attempt to remove more hearts than available
            HBox container = heartDisplay.getContainer();
            assertEquals(0, container.getChildren().size());
        });
    }

    @Test
    void containerPositionIsSetCorrectly() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            HBox container = heartDisplay.getContainer();
            assertEquals(100, container.getLayoutX());
            assertEquals(100, container.getLayoutY());
        });
    }

    @Test
    void initializesWithZeroHearts() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            heartDisplay = new HeartDisplay(100, 100, 0);
            HBox container = heartDisplay.getContainer();
            assertEquals(0, container.getChildren().size());
        });
    }

    @Test
    void initializesWithNegativeHearts() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            heartDisplay = new HeartDisplay(100, 100, -1);
            HBox container = heartDisplay.getContainer();
            assertEquals(0, container.getChildren().size());
        });
    }

    @Test
    void removesHeartFromSingleHeartDisplay() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            heartDisplay = new HeartDisplay(100, 100, 1);
            heartDisplay.removeHeart();
            HBox container = heartDisplay.getContainer();
            assertEquals(0, container.getChildren().size());
        });
    }
}
