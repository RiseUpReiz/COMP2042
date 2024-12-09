package com.example.demo.menu;

import com.example.demo.manager.MusicManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The WinMenu class represents the victory screen displayed when the player wins the game.
 */
public class MenuWin {

    private final Stage stage;
    private static final String BGM_FILE_PATH = "/com/example/demo/audios/bgm.mp3";
    private static final String VICTORY_MUSIC = "/com/example/demo/audios/Victory.mp3";

    /**
     * Constructs a WinMenu with the specified stage.
     *
     * @param stage the stage to display the win menu on
     */
    public MenuWin(Stage stage) {
        this.stage = stage;
    }

    /**
     * Displays the win menu, including the victory message, background image, and buttons.
     */
    public void show() {
        // Stop current music and play victory music
        MusicManager musicManager = MusicManager.getInstance();
        musicManager.stopBackgroundMusic();
        musicManager.playBackgroundMusic(VICTORY_MUSIC);

        // Create the victory message
        Text victoryMessage = new Text("CONGRATULATIONS. YOU WON.");
        victoryMessage.setFont(Font.font("Arial", FontWeight.BOLD, 48)); // Larger font for emphasis
        victoryMessage.setStyle("-fx-fill: white;");
        victoryMessage.setStroke(Color.BLACK);
        victoryMessage.setStrokeWidth(2);

        // Back to Main Menu button
        Button backButton = new Button("Back to Main Menu");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backButton.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black;");
        backButton.setOnAction(e -> goToMainMenu());

        // Vertical layout for the text and button
        VBox content = new VBox(20); // Space between text and button
        content.setAlignment(Pos.CENTER); // Center align the text and button
        content.getChildren().addAll(victoryMessage, backButton);

        // Background image
        ImageView backgroundImage = new ImageView(
                new Image(getClass().getResource("/com/example/demo/images/winBackground.jpeg").toExternalForm())
        );
        backgroundImage.setFitHeight(stage.getHeight());
        backgroundImage.setFitWidth(stage.getWidth());

        // Layout the components in a StackPane
        StackPane layout = new StackPane();
        layout.getChildren().addAll(backgroundImage, content);
        StackPane.setAlignment(content, Pos.CENTER);

        // Create and set the scene
        Scene winScene = new Scene(layout, stage.getWidth(), stage.getHeight());

        stage.setScene(winScene);
        stage.show();
    }

    /**
     * Navigates back to the main menu and resumes background music.
     */
    private void goToMainMenu() {
        MusicManager musicManager = MusicManager.getInstance();
        musicManager.stopBackgroundMusic();
        MusicManager.getInstance().playBackgroundMusic(BGM_FILE_PATH);
        MainMenu mainMenu = new MainMenu(stage);
        mainMenu.show();
    }
}