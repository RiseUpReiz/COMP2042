package com.example.demo.levels;

import com.example.demo.HeartDisplay;
import javafx.scene.Group;

public class LevelView {

	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private final Group root;
    private final HeartDisplay heartDisplay;
	private final PauseMenu pauseMenu;

	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
        this.pauseMenu = new PauseMenu();
	}

	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
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
