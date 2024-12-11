package com.example.demo.levels;

import com.example.demo.controller.Controller;
import com.example.demo.manager.HighScoreManager;
import com.example.demo.controller.Main;
import com.example.demo.manager.MusicManager;
import com.example.demo.menu.MenuGameOver;
import com.example.demo.planes.AdvancedPlane;
import com.example.demo.planes.Boss;
import com.example.demo.planes.EnemyPlane;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

/**
 * Represents the endless mode level in the game.
 * This class handles the initialization, spawning of enemy units, and game over conditions for the endless mode.
 */
public class LevelEndless extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background4.jfif";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private static final double Y_UPPER_BOUND = 70;
    private static final double Y_LOWER_BOUND = 650.0;
    private static final int INITIAL_TOTAL_ENEMIES = 6;
    private static final int MAX_TOTAL_ENEMIES = 15;
    private final HighScoreManager highScoreManager;
    private final LevelViewEndless levelViewEndless;
    private int elapsedTime;
    private int increasingTotalEnemies;
    private long startTime;
    private int secondsElapsed;

    /**
     * Constructs an EndlessMode instance.
     *
     * @param screenHeight the height of the screen
     * @param screenWidth the width of the screen
     * @param controller the game controller
     * @param stage the stage for the game
     */
    public LevelEndless(double screenHeight, double screenWidth, Controller controller, Stage stage) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, controller, stage);
        this.highScoreManager = new HighScoreManager();
        this.elapsedTime = 0;
        this.increasingTotalEnemies = INITIAL_TOTAL_ENEMIES;
        this.levelViewEndless = (LevelViewEndless) instantiateLevelView();
        this.startTime = System.currentTimeMillis();
        this.secondsElapsed = 0;
        startTimer();
    }

    /**
     * Starts the timer to track real-time seconds elapsed.
     */
    private void startTimer() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                secondsElapsed = (int) ((System.currentTimeMillis() - startTime) / 1000);
                levelViewEndless.updateTimer(secondsElapsed);
            }
        };
        timer.start();
    }

    /**
     * Initializes the friendly units in the game.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Checks if the game is over and updates the high score if the user is destroyed.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            highScoreManager.updateHighScore(secondsElapsed);
            loseGame();
        }
    }

    /**
     * Spawns enemy units based on elapsed time and spawn probability.
     */
    @Override
    protected void spawnEnemyUnits() {
        elapsedTime++;
        updateTotalEnemies();

        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        if (currentNumberOfEnemies < increasingTotalEnemies && elapsedTime % 20 == 0) {
            for (int i = 0; i < increasingTotalEnemies - currentNumberOfEnemies; i++) {
                double newEnemyInitialYPosition = Y_UPPER_BOUND + Math.random() * (Y_LOWER_BOUND - Y_UPPER_BOUND);
                addEnemyUnit(new EnemyPlane(Main.SCREEN_WIDTH, newEnemyInitialYPosition));
            }
        }

        if (currentNumberOfEnemies < increasingTotalEnemies && elapsedTime % 60 == 0) {
            for (int i = 0; i < increasingTotalEnemies - currentNumberOfEnemies; i++) {
                double newEnemyInitialYPosition = Y_UPPER_BOUND + Math.random() * (Y_LOWER_BOUND - Y_UPPER_BOUND);
                addEnemyUnit(new AdvancedPlane(Main.SCREEN_WIDTH, newEnemyInitialYPosition));
            }
        }

        if (elapsedTime % 200 == 0) {
            Boss boss = new Boss(new LevelViewBoss(getRoot(), PLAYER_INITIAL_HEALTH));
            addEnemyUnit(boss);
        }
    }

    /**
     * Updates the total number of enemies that can be spawned.
     */
    private void updateTotalEnemies() {
        int increaseInterval = 200;
        if (elapsedTime % increaseInterval == 0 && increasingTotalEnemies < MAX_TOTAL_ENEMIES) {
            increasingTotalEnemies++;
        }
    }

    /**
     * Instantiates the level view for endless mode.
     *
     * @return the level view for endless mode
     */
    @Override
    protected LevelView instantiateLevelView() {
        return new LevelViewEndless(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Handles the game over scenario by stopping the game and showing the game over menu.
     */
    @Override
    protected void loseGame() {
        timeline.stop();
        MusicManager.getInstance().stopBackgroundMusic();

        MenuGameOver gameOverMenu = new MenuGameOver(stage, highScoreManager);
        gameOverMenu.endlessShow(secondsElapsed);
    }
}