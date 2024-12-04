package com.example.demo.levels;

import javafx.scene.Group;
import javafx.scene.text.Text;

/**
 * Represents the view for the endless level in the game.
 * This class handles the display and management of the timer specific to the endless level.
 */
public class LevelViewEndless extends LevelView {
    private Text timerText;

    /**
     * Constructs a LevelViewEndless instance.
     *
     * @param root the root group for the scene
     * @param playerInitialHealth the initial health of the player
     */
    public LevelViewEndless(Group root, int playerInitialHealth) {
        super(root, playerInitialHealth);
        initializeTimer(root); // Pass root for proper initialization
    }

    /**
     * Initializes the timer display.
     *
     * @param root the root group for the scene
     */
    private void initializeTimer(Group root) {
        timerText = new Text(10, 20, "Time: 0");
        root.getChildren().add(timerText); // Use provided root group
    }

    /**
     * Updates the timer display with the elapsed time.
     *
     * @param elapsedTime the elapsed time to display
     */
    public void updateTimer(int elapsedTime) {
        timerText.setText("Time: " + elapsedTime);
    }
}