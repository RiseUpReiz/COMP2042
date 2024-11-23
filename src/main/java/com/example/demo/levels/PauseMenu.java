package com.example.demo.levels;

import com.example.demo.controller.Main;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class PauseMenu extends StackPane {

    public PauseMenu() {
        ImageView pauseImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/com/example/demo/images/pauseGame.png")).toExternalForm()));
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

    public void showPauseMenu() {
        this.setVisible(true); // Show pause menu
    }

    public void hidePauseMenu() {
        this.setVisible(false); // Hide pause menu
    }
}
