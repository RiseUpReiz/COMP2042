package com.example.demo.levels;

import com.example.demo.controller.Controller;
import com.example.demo.controller.Main;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class Instructions extends LevelParent {

    private final Stage stage;
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/start-background.jfif";
    double screenWidth = Main.getScreenWidth();
    double screenHeight = Main.getScreenHeight();

    public Instructions(double screenHeight, double screenWidth, Controller controller, Stage stage) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, 0, controller, stage);
        this.stage = stage;
    }

    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), 0);
    }

    public void show() {
        Scene instructionsScene = initializeScene();
        stage.setScene(instructionsScene);

        // Instruction texts
        Text text1 = new Text("Objective: Defeat enemies and avoid projectiles");
        Text text2 = new Text("Use arrow key '>' to move right\nUse arrow key '<' to move left" +
                                 "\nSpacebar to shoot\nPress 'Esc' to pause game");

        text1.setStyle("-fx-font-size: 20px; -fx-fill: black; -fx-font-weight: bold;");
        text2.setStyle("-fx-font-size: 18px; -fx-fill: black; -fx-font-weight: bold;");

        // Back button
        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> goToMainMenu());

        // VBox for text and button
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(text1, text2, backButton);

        // Translucent background box
        Region backgroundBox = new Region();
        backgroundBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8);");

        // Center the translucent box and content together using StackPane
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(screenWidth, screenHeight);
        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().addAll(backgroundBox, vbox);

        // Add StackPane to layout
        Pane layout = new Pane(stackPane);
        getRoot().getChildren().add(layout);

        // Disable esc
        instructionsScene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().toString().equals("ESCAPE")) {
                event.consume(); // Ignore esc presses
            }
        });

        stage.show();
    }

    private void goToMainMenu() {
        MainMenu mainMenu = new MainMenu(stage);
        mainMenu.show(); // Go back to the main menu
    }
    @Override
    protected void checkIfGameOver() {
    }

    @Override
    protected void initializeFriendlyUnits() {
    }

    @Override
    protected void spawnEnemyUnits() {
    }
}