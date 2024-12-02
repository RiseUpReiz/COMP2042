package com.example.demo.levels;

import com.example.demo.displays.ShieldImage;
import javafx.scene.Group;

public class LevelViewBoss extends LevelView {

	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;
	private final Group root;
	private final ShieldImage shieldImage;
	
	public LevelViewBoss(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
	}

	public void showShield() {
		if(!root.getChildren().contains(shieldImage)){
			root.getChildren().add(shieldImage);
		}
		shieldImage.showShield();
	}

	public void hideShield() {
		shieldImage.hideShield();
	}

	public void updateShieldPosition(double xPosition, double yPosition) {
		shieldImage.setLayoutX(xPosition);
		shieldImage.setLayoutY(yPosition);
	}
}
