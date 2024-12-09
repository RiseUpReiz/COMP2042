package com.example.demo.menu;

import com.example.demo.controller.Controller;
import com.example.demo.controller.Main;
import com.example.demo.levels.LevelParent;
import com.example.demo.levels.LevelView;
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

/**
 * Represents the instructions screen in the game.
 * This class handles the display of game instructions and navigation back to the main menu.
 */
public class MenuInstructions extends LevelParent {

    private final Stage stage;
    private final Controller controller;
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/start-background.jfif";
    double screenWidth = Main.getScreenWidth();
    double screenHeight = Main.getScreenHeight();

    /**
     * Constructs an Instructions instance.
     *
     * @param screenHeight the height of the screen
     * @param screenWidth the width of the screen
     * @param controller the game controller
     * @param stage the stage for the game
     */
    public MenuInstructions(double screenHeight, double screenWidth, Controller controller, Stage stage) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, 0, controller, stage);
        this.stage = stage;
        this.controller = controller;
    }

    /**
     * Instantiates the level view for the instructions screen.
     *
     * @return the level view for the instructions screen
     */
    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), 0);
    }

    /**
     * Displays the instructions screen.
     */
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
        backButton.setOnAction(e -> controller.backToMainMenu());

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

    /**
     * Checks if the game is over.
     * This method is not used in the instructions screen.
     */
    @Override
    protected void checkIfGameOver() {
    }

    /**
     * Initializes the friendly units in the game.
     * This method is not used in the instructions screen.
     */
    @Override
    protected void initializeFriendlyUnits() {
    }

    /**
     * Spawns enemy units in the game.
     * This method is not used in the instructions screen.
     */
    @Override
    protected void spawnEnemyUnits() {
    }

    /**
     * Disables firing in the instructions screen.
     *
     * @return false to indicate that firing is not allowed
     */
    @Override
    public boolean allowFiring() {
        return false;
    }
}