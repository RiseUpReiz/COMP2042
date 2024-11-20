package com.example.demo.levels;

import com.example.demo.controller.Controller;
import com.example.demo.controller.Main;
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

public class MainMenu {

    private final Stage stage;
    private Controller controller;

    public MainMenu(Stage stage) {
        this.stage = stage;
    }

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

        Button instructionsButton = new Button("Instructions");
        instructionsButton.setOnAction(e -> showInstructions());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> stage.close());

        // Add all elements to the layout
        menuLayout.getChildren().addAll(title, startButton, instructionsButton, exitButton);

        // Set the background image
        menuLayout.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource
                ("/com/example/demo/images/start-background.png").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

        // Create and set the scene
        Scene mainMenuScene = new Scene(menuLayout, stage.getWidth(), stage.getHeight());
        stage.setScene(mainMenuScene);
        stage.show();
    }

    private void showInstructions() {
        Instructions instructions = new Instructions(stage.getHeight(), stage.getWidth(), controller, stage);
        instructions.show();
    }

    private void startGame() {
        try {
            Controller myController = new Controller(stage);
            myController.launchGame(); // This will switch the scene to the game scene
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
