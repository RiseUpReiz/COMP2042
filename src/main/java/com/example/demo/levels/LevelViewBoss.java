package com.example.demo.levels;

import com.example.demo.displays.ShieldImage;
import javafx.scene.Group;

/**
 * Represents the view for the boss level in the game.
 * This class handles the display and management of the shield image specific to the boss level.
 */
public class LevelViewBoss extends LevelView {

    private static final int SHIELD_X_POSITION = 1150;
    private static final int SHIELD_Y_POSITION = 500;
    private final Group root;
    private final ShieldImage shieldImage;

    /**
     * Constructs a LevelViewBoss instance.
     *
     * @param root the root group for the scene
     * @param heartsToDisplay the number of hearts to display
     */
    public LevelViewBoss(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
        this.root = root;
        this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
    }

    /**
     * Displays the shield image on the screen.
     */
    public void showShield() {
        if (!root.getChildren().contains(shieldImage)) {
            root.getChildren().add(shieldImage);
        }
        shieldImage.showShield();
    }

    /**
     * Hides the shield image from the screen.
     */
    public void hideShield() {
        shieldImage.hideShield();
    }

    /**
     * Updates the position of the shield image.
     *
     * @param xPosition the new x position of the shield
     * @param yPosition the new y position of the shield
     */
    public void updateShieldPosition(double xPosition, double yPosition) {
        shieldImage.setLayoutX(xPosition);
        shieldImage.setLayoutY(yPosition);
    }
}