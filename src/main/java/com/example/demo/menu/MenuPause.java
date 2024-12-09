package com.example.demo.menu;

import com.example.demo.controller.Main;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

/**
 * Represents the pause menu in the game.
 * This class handles the display and visibility of the pause menu.
 */
public class MenuPause extends StackPane {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/pauseGame.png";

    /**
     * Constructs a PauseMenu instance.
     * Initializes the pause menu with a translucent overlay and a pause image.
     */
    public MenuPause() {
        ImageView pauseImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_NAME)).toExternalForm()));
        pauseImage.setPreserveRatio(true);
        pauseImage.setOpacity(0.7);

        // Center align the pause image
        this.setAlignment(Pos.CENTER);

        // Add the pause image to the StackPane
        this.getChildren().add(pauseImage);

        // Style the background as a translucent overlay
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4);");
        this.setPrefSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // Cover the screen
        this.setVisible(false);
    }

    /**
     * Shows the pause menu by setting its visibility to true.
     */
    public void showPauseMenu() {
        this.setVisible(true); // Show pause menu
    }

    /**
     * Hides the pause menu by setting its visibility to false.
     */
    public void hidePauseMenu() {
        this.setVisible(false); // Hide pause menu
    }
}