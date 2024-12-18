package com.example.demo.menu;

import com.example.demo.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

/**
 * Represents the main menu of the game.
 * This class handles the display and interaction of the main menu, including starting the game, showing instructions, and exiting the game.
 */
public class MainMenu {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/start-background.png";

    private final Stage stage;
    private final Controller controller;

    /**
     * Constructs a MainMenu instance.
     *
     * @param stage the stage for the main menu
     */
    public MainMenu(Stage stage) {
        this.stage = stage;
        this.controller = new Controller(stage);
    }

    /**
     * Displays the main menu.
     */
    public void show() {
        VBox menuLayout = new VBox(10);
        menuLayout.setAlignment(Pos.CENTER);

        // Create the title
        Label title = new Label("Sky Battle");
        title.setStyle("-fx-font-size: 100px; -fx-font-weight: bold; " +
                "-fx-fill: linear-gradient(from 0% 0% to 0% 100%, #C0C0C0, #A9A9A9, #808080); " +
                "-fx-font-family: 'Impact', 'Stencil', sans-serif; -fx-stroke: #2F4F4F; " +
                "-fx-stroke-width: 3px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 15, 0.5, 0, 4);"
        );

        // Create the buttons
        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> startGame());

        Button startEndlessModeButton = new Button("Endless Mode");
        startEndlessModeButton.setOnAction(e -> controller.showEndlessInstructions());

        Button instructionsButton = new Button("Instructions");
        instructionsButton.setOnAction(e -> showInstructions());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> stage.close());

        // Add all elements to the layout
        menuLayout.getChildren().addAll(title, startButton, startEndlessModeButton, instructionsButton, exitButton);

        // Set the background image using the constant
        menuLayout.setBackground(new Background(new BackgroundImage(
                new Image(getClass().getResource(BACKGROUND_IMAGE_NAME).toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT
        )));

        // Create and set the scene
        Scene mainMenuScene = new Scene(menuLayout, stage.getWidth(), stage.getHeight());
        stage.setScene(mainMenuScene);
        stage.show();
    }

    /**
     * Displays the instructions screen.
     */
    private void showInstructions() {
        MenuInstructions instructions = new MenuInstructions(stage.getHeight(), stage.getWidth(), controller, stage);
        instructions.show();
    }

    /**
     * Starts the game by launching the game controller.
     */
    private void startGame() {
        try {
            Controller myController = new Controller(stage);
            myController.launchGame(); // This will switch the scene to the game scene
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}