package com.example.demo.levels;

import com.example.demo.displays.HeartDisplay;
import com.example.demo.menu.MenuPause;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Represents the view for a level in the game.
 * This class handles the display of hearts, kill counter, and pause menu.
 */
public class LevelView {

    private static final double HEART_DISPLAY_X_POSITION = 5;
    private static final double HEART_DISPLAY_Y_POSITION = 25;
    private static final double KILL_COUNTER_X_OFFSET = 300;
    private static final double KILL_COUNTER_Y_POSITION = 35;

    private final Group root;
    private final HeartDisplay heartDisplay;
    private final MenuPause pauseMenu;
    private final Label killCounter;

    /**
     * Constructs a LevelView instance.
     *
     * @param root the root group for the scene
     * @param heartsToDisplay the number of hearts to display
     */
    public LevelView(Group root, int heartsToDisplay) {
        this.root = root;
        this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
        this.pauseMenu = new MenuPause();

        // Initialize the kill counter
        this.killCounter = new Label();
        this.killCounter.setLayoutX(HEART_DISPLAY_X_POSITION + KILL_COUNTER_X_OFFSET);
        this.killCounter.setLayoutY(KILL_COUNTER_Y_POSITION);
        this.killCounter.setTextFill(Color.BLACK);
        this.killCounter.setFont(new Font("Arial", 25));
    }

    /**
     * Displays the heart display on the screen.
     */
    public void showHeartDisplay() {
        root.getChildren().add(heartDisplay.getContainer());
    }

    /**
     * Updates the kill counter with the current and target kills.
     *
     * @param currentKills the current number of kills
     * @param targetKills the target number of kills
     */
    public void updateKillCounter(int currentKills, int targetKills) {
        killCounter.setText("Kills: " + currentKills + " / " + targetKills);
        if (!root.getChildren().contains(killCounter)) {
            root.getChildren().add(killCounter);
        }
    }

    /**
     * Removes the kill counter from the screen.
     */
    public void removeKillCounter() {
        root.getChildren().remove(killCounter);
    }

    /**
     * Displays the pause menu on the screen.
     */
    public void showPauseMenu() {
        if (!root.getChildren().contains(pauseMenu)) {
            root.getChildren().add(pauseMenu);
        }
        pauseMenu.toFront(); // Bring the pause menu to the front
        pauseMenu.showPauseMenu();
    }

    /**
     * Hides the pause menu from the screen.
     */
    public void hidePauseMenu() {
        pauseMenu.hidePauseMenu();
    }

    /**
     * Removes hearts from the heart display based on the remaining hearts.
     *
     * @param heartsRemaining the number of hearts remaining
     */
    public void removeHearts(int heartsRemaining) {
        int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
        for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
            heartDisplay.removeHeart();
        }
    }
}