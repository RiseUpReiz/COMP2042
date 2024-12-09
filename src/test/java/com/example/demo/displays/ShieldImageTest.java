package com.example.demo.displays;

import com.example.demo.utils.JavaFXTestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShieldImageTest {

    private ShieldImage shieldImage;

    @BeforeAll
    static void setupJavaFX() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {});
    }

    @BeforeEach
    void setUp() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> shieldImage = new ShieldImage(100, 100));
    }

    @Test
    void initializesWithCorrectPosition() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            assertEquals(100, shieldImage.getLayoutX());
            assertEquals(100, shieldImage.getLayoutY());
        });
    }

    @Test
    void initializesWithCorrectSize() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            assertEquals(65, shieldImage.getFitHeight());
            assertEquals(65, shieldImage.getFitWidth());
        });
    }

    @Test
    void initializesAsInvisible() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> assertFalse(shieldImage.isVisible()));
    }

    @Test
    void showsShieldSuccessfully() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            shieldImage.showShield();
            assertTrue(shieldImage.isVisible());
        });
    }

    @Test
    void hidesShieldSuccessfully() throws InterruptedException {
        JavaFXTestUtils.runOnJavaFXThread(() -> {
            shieldImage.showShield();
            shieldImage.hideShield();
            assertFalse(shieldImage.isVisible());
        });
    }
}
