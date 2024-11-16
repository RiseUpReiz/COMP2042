package com.example.demo.levels;

import com.example.demo.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {

    private final Stage stage;

    public MainMenu(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        // Main Menu Title
        Label title = new Label("Sky Battle");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Start Game Button
        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> startGame());

        // Instructions Button
        Button instructionsButton = new Button("Instructions");
        instructionsButton.setOnAction(e -> showInstructions());

        // Exit Button
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> stage.close());

        // Layout for main menu
        VBox menuLayout = new VBox();
        menuLayout.getChildren().addAll(title, startButton, instructionsButton, exitButton);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        // Main Menu Scene
//        Scene mainMenuScene = new Scene(menuLayout, 1300, 750);
        Scene mainMenuScene = new Scene (menuLayout, stage.getWidth(), stage.getHeight());
        stage.setScene(mainMenuScene);
        stage.show();
    }

    private void startGame() {
        try {
            Controller myController = new Controller(stage);
            myController.launchGame(); // This will switch the scene to the game scene
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showInstructions() {
        Instructions instructions = new Instructions(stage);
        instructions.show();
    }
}
