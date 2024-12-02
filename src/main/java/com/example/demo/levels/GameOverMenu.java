package com.example.demo.levels;

import com.example.demo.controller.HighScoreManager;
import com.example.demo.controller.Main;
import com.example.demo.controller.MusicManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

public class GameOverMenu {

    private final Stage stage;
    private final HighScoreManager highScoreManager;
    private static final String GAME_OVER_SOUND = "/com/example/demo/audios/gameOver.mp3";
    private static final String BACKGROUND_IMAGE_PATH = "/com/example/demo/images/gameover.jpeg";

    public GameOverMenu(Stage stage, HighScoreManager highScoreManager) {
        this.stage = stage;
        this.highScoreManager = highScoreManager;
    }

    public void show() {
        MusicManager.getInstance().playSoundEffect(GAME_OVER_SOUND);

        StackPane root = new StackPane();

        // Add background image
        ImageView backgroundImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(BACKGROUND_IMAGE_PATH))));
        backgroundImage.setFitWidth(Main.SCREEN_WIDTH);
        backgroundImage.setFitHeight(Main.SCREEN_HEIGHT);
        backgroundImage.setPreserveRatio(false);

        VBox layout = new VBox(40);
        layout.setAlignment(Pos.CENTER);

        Text gameOverText = new Text("GAME OVER");
        gameOverText.setFill(Color.WHITE);
        gameOverText.setFont(Font.font("Courier New", 70));
        gameOverText.setEffect(new DropShadow(20, Color.GREY));

        // Blinking effect
        Timeline blinkEffect = new Timeline(
                new KeyFrame(Duration.seconds(0.7), e -> gameOverText.setOpacity(0)),
                new KeyFrame(Duration.seconds(1.4), e -> gameOverText.setOpacity(1))
        );
        blinkEffect.setCycleCount(Timeline.INDEFINITE);
        blinkEffect.play();

        Text instructionsText = new Text("PRESS ESC TO CONTINUE");
        instructionsText.setFill(Color.WHITE);
        instructionsText.setFont(Font.font("Courier New", 25));
        instructionsText.setStyle("-fx-opacity: 0.8;");

        layout.getChildren().addAll(gameOverText, instructionsText);
        root.getChildren().addAll(backgroundImage, layout);

        Scene gameOverScene = new Scene(root, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        gameOverScene.setOnKeyPressed(e -> {
            if (Objects.requireNonNull(e.getCode()) == KeyCode.ESCAPE) {
                goToMainMenu();
            }
        });

        stage.setScene(gameOverScene);
        stage.show();
    }

    public void endlessShow(int currentScore) {
        MusicManager.getInstance().playSoundEffect(GAME_OVER_SOUND);

        StackPane root = new StackPane();

        // Add background image
        ImageView backgroundImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(BACKGROUND_IMAGE_PATH))));
        backgroundImage.setFitWidth(Main.SCREEN_WIDTH);
        backgroundImage.setFitHeight(Main.SCREEN_HEIGHT);
        backgroundImage.setPreserveRatio(false);

        VBox layout = new VBox(40);
        layout.setAlignment(Pos.CENTER);

        Label currentScoreLabel = new Label("Your time: " + currentScore + " seconds");
        currentScoreLabel.setTextFill(Color.WHITE);
        currentScoreLabel.setFont(Font.font("Courier New", 30));

        Label highScoreLabel = new Label("Best time: " + highScoreManager.getHighScore() + " seconds");
        highScoreLabel.setTextFill(Color.WHITE);
        highScoreLabel.setFont(Font.font("Courier New", 30));

        Text continueText = new Text("PRESS ESC TO CONTINUE");
        continueText.setFill(Color.WHITE);
        continueText.setFont(Font.font("Courier New", 25));
        continueText.setStyle("-fx-opacity: 0.8;");

        layout.getChildren().addAll(currentScoreLabel, highScoreLabel, continueText);
        root.getChildren().addAll(backgroundImage, layout);

        Scene gameOverScene = new Scene(root, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        gameOverScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                goToMainMenu();
            }
        });

        stage.setScene(gameOverScene);
        stage.show();
    }

    private void goToMainMenu() {
        MusicManager musicManager = MusicManager.getInstance();
        musicManager.stopBackgroundMusic();
        MusicManager.getInstance().playBackgroundMusic("/com/example/demo/audios/bgm.mp3");
        MainMenu mainMenu = new MainMenu(stage);
        mainMenu.show();
    }
}
