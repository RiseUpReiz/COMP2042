package com.example.demo.levels;

import com.example.demo.controller.Controller;
import com.example.demo.controller.Main;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;

public class GameOverMenu {

    private final Stage stage;
    private final Controller controller;

    public GameOverMenu(Stage stage, Controller controller) {
        this.stage = stage;
        this.controller = controller;
    }

    public void show() {
        VBox layout = new VBox(20);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20; -fx-background-color: black;");

        // "Restart Game" Button
        Button restartButton = new Button("Restart Game");
        restartButton.setStyle("-fx-font-size: 16; -fx-text-fill: white; -fx-background-color: gray;");
        restartButton.setOnAction(e -> {
            try {
                restartGame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // "Exit to Main Menu" Button
        Button exitButton = new Button("Exit to Main Menu");
        exitButton.setStyle("-fx-font-size: 16; -fx-text-fill: white; -fx-background-color: gray;");
        exitButton.setOnAction(e -> goToMainMenu());

        layout.getChildren().addAll(restartButton, exitButton);

        Scene menuScene = new Scene(layout, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        stage.setScene(menuScene);
        stage.show();
    }

    private void restartGame() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        controller.goToLevel(Controller.LEVEL_ONE_CLASS_NAME);
    }

    private void goToMainMenu() {
        // Navigate to the main menu
        MainMenu mainMenu = new MainMenu(stage);
        mainMenu.show();
    }
}
