package com.example.demo.utils;

import javafx.application.Platform;

import java.util.concurrent.CountDownLatch;

public class JavaFXTestUtils {

    private static boolean toolkitInitialized = false;

    public static void initializeToolkit() throws InterruptedException {
        if (!toolkitInitialized) {
            CountDownLatch latch = new CountDownLatch(1);
            Platform.startup(latch::countDown);
            latch.await();
            toolkitInitialized = true;
        }
    }

    public static void runOnJavaFXThread(Runnable action) throws InterruptedException {
        initializeToolkit(); // Ensure toolkit is initialized
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                action.run();
            } finally {
                latch.countDown();
            }
        });
        latch.await();
    }
}
