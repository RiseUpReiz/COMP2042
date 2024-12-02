package com.example.demo.levels;

import com.example.demo.displays.HeartDisplay;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LevelView {

	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final double KILL_COUNTER_X_OFFSET = 300;
	private static final double KILL_COUNTER_Y_POSITION = 35;

	private final Group root;
	private final HeartDisplay heartDisplay;
	private final PauseMenu pauseMenu;
	private final Label killCounter;

	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.pauseMenu = new PauseMenu();

		// Initialize the kill counter
		this.killCounter = new Label();
		this.killCounter.setLayoutX(HEART_DISPLAY_X_POSITION + KILL_COUNTER_X_OFFSET);
		this.killCounter.setLayoutY(KILL_COUNTER_Y_POSITION);
		this.killCounter.setTextFill(Color.BLACK);
		this.killCounter.setFont(new Font("Arial", 25));
	}

	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	public void updateKillCounter(int currentKills, int targetKills) {
		killCounter.setText("Kills: " + currentKills + " / " + targetKills);
		if (!root.getChildren().contains(killCounter)) {
			root.getChildren().add(killCounter);
		}
	}

	public void removeKillCounter() {
		root.getChildren().remove(killCounter);
	}

	public void showPauseMenu() {
		if (!root.getChildren().contains(pauseMenu)) {
			root.getChildren().add(pauseMenu);
		} else {
			root.getChildren().remove(pauseMenu);
			root.getChildren().add(pauseMenu);
		}
		pauseMenu.showPauseMenu();
	}

	public void hidePauseMenu() {
		pauseMenu.hidePauseMenu();
	}

	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}
}
