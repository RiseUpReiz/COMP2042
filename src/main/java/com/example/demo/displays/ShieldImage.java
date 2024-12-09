package com.example.demo.displays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a shield image in the game, extending the ImageView class.
 */
public class ShieldImage extends ImageView {

	private static final int SHIELD_SIZE = 65;

	/**
	 * Constructs a ShieldImage with the specified position.
	 *
	 * @param xPosition the x-coordinate of the shield image
	 * @param yPosition the y-coordinate of the shield image
	 */
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.setImage(new Image(getClass().getResource("/com/example/demo/images/shield.png").toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
	}

	/**
	 * Shows the shield image by setting its visibility to true.
	 */
	public void showShield() {
		this.setVisible(true);
	}

	/**
	 * Hides the shield image by setting its visibility to false.
	 */
	public void hideShield() {
		this.setVisible(false);
	}
}