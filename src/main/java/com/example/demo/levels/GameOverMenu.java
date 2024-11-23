package com.example.demo.levels;

import com.example.demo.controller.Controller;
import com.example.demo.controller.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

public class GameOverMenu {

    private final Stage stage;

    public GameOverMenu(Stage stage, Controller controller) {
        this.stage = stage;
    }

    public void show() {
        playGameOverSound();

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: black;");

        VBox layout = new VBox(40);
        layout.setAlignment(Pos.CENTER);

        Text gameOverText = new Text("GAME OVER");
        gameOverText.setFill(Color.WHITE);
        gameOverText.setFont(Font.font("Courier New", 70)); // Retro-style font
        gameOverText.setEffect(new DropShadow(20, Color.GREY));

        // Blinking effect to emphasize game over
        Timeline blinkEffect = new Timeline(
                new KeyFrame(Duration.seconds(0.7), e -> gameOverText.setOpacity(0)),
                new KeyFrame(Duration.seconds(1.4), e -> gameOverText.setOpacity(1))
        );
        blinkEffect.setCycleCount(Timeline.INDEFINITE);
        blinkEffect.play();

        // "Press ESC to continue" Text with styling
        Text instructionsText = new Text("PRESS ESC TO CONTINUE");
        instructionsText.setFill(Color.WHITE);
        instructionsText.setFont(Font.font("Courier New", 25)); // Smaller retro font
        instructionsText.setStyle("-fx-opacity: 0.8;"); // Slightly faded

        // Add both texts to layout
        layout.getChildren().addAll(gameOverText, instructionsText);
        root.getChildren().add(layout);

        // Create Scene
        Scene gameOverScene = new Scene(root, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        // Handle "ESC" key press to navigate back to the main menu
        gameOverScene.setOnKeyPressed(e -> {
            if (Objects.requireNonNull(e.getCode()) == KeyCode.ESCAPE) {
                goToMainMenu();
            }
        });

        stage.setScene(gameOverScene);
        stage.show();
    }

    private void goToMainMenu() {
        MainMenu mainMenu = new MainMenu(stage);
        mainMenu.show();
    }

    private void playGameOverSound() {
        try {
            String gameOver = getClass().getResource("/com/example/demo/images/gameOver.mp3").toExternalForm();
            Media gameOverSound = new Media(gameOver);
            MediaPlayer mediaPlayer = new MediaPlayer(gameOverSound);
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
