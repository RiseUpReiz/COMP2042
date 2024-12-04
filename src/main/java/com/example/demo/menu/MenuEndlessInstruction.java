package com.example.demo.menu;

import com.example.demo.controller.Controller;
import com.example.demo.controller.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Class representing the instructions for the endless mode.
 */
public class MenuEndlessInstruction {

    private final Stage stage;
    private final Controller controller;
    private static final String BACKGROUND_IMAGE_PATH = "/com/example/demo/images/background4.jfif";

    /**
     * Constructs an EndlessInstruction with the specified stage and controller.
     *
     * @param stage the primary stage for this application
     * @param controller the controller managing the game
     */
    public MenuEndlessInstruction(Stage stage, Controller controller) {
        this.stage = stage;
        this.controller = controller;
    }

    /**
     * Displays the endless mode instructions.
     */
    public void show() {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        layout.setAlignment(Pos.CENTER);

        Text instructions = new Text(
                """
                        Welcome to Endless Mode!

                        In this mode, the difficulty will increase as time progresses.
                        Your goal is to survive as long as possible and try to beat the high score.

                        Good luck!"""
        );
        instructions.setStyle("-fx-font-size: 18px; -fx-fill: black;");
        instructions.setTextAlignment(TextAlignment.CENTER);

        Button startButton = new Button("Press to begin Endless Mode");
        startButton.setOnAction(e -> controller.startEndlessMode());

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setOnAction(e -> controller.backToMainMenu());

        layout.getChildren().addAll(instructions, startButton, mainMenuButton);

        ImageView backgroundImage = new ImageView(new Image(getClass().getResource(BACKGROUND_IMAGE_PATH).toExternalForm()));
        backgroundImage.setFitWidth(Main.getScreenWidth());
        backgroundImage.setFitHeight(Main.getScreenHeight());
        backgroundImage.setPreserveRatio(false);

        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImage, layout);

        Scene scene = new Scene(root, Main.getScreenWidth(), Main.getScreenHeight());
        stage.setScene(scene);
        stage.show();
    }
}