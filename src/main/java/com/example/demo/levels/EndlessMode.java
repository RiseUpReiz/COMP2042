package com.example.demo.levels;

import com.example.demo.controller.Controller;
import com.example.demo.controller.HighScoreManager;
import com.example.demo.controller.Main;
import com.example.demo.controller.MusicManager;
import com.example.demo.planes.AdvancedPlane;
import com.example.demo.planes.Boss;
import com.example.demo.planes.EnemyPlane;
import javafx.stage.Stage;

public class EndlessMode extends LevelParent {

    private final LevelViewEndless levelViewEndless;
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background4.jfif";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private static final double Y_UPPER_BOUND = 70;
    private static final double Y_LOWER_BOUND = 650.0;
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    private static final int INITIAL_TOTAL_ENEMIES = 6;
    private static final int MAX_TOTAL_ENEMIES = 15;
    private final HighScoreManager highScoreManager;
    private int elapsedTime;
    private int increasingTotalEnemies;

    public EndlessMode(double screenHeight, double screenWidth, Controller controller, Stage stage) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, controller, stage);
        this.highScoreManager = new HighScoreManager();
        this.elapsedTime = 0;
        this.increasingTotalEnemies = INITIAL_TOTAL_ENEMIES;
        this.levelViewEndless = (LevelViewEndless) instantiateLevelView();
    }

    @Override
    // Add user plane
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    @Override
    // Game over logic
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            highScoreManager.updateHighScore(getUser().getNumberOfKills());
            loseGame();
        }
    }

    @Override
    // Spawn enemy units logic
    protected void spawnEnemyUnits() {
        elapsedTime++;
        levelViewEndless.updateTimer(elapsedTime); // Update timer
        updateTotalEnemies();

        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        if (currentNumberOfEnemies < increasingTotalEnemies && elapsedTime % 20 == 0) {
            for (int i = 0; i < increasingTotalEnemies - currentNumberOfEnemies; i++) {
                if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                    double newEnemyInitialYPosition = Y_UPPER_BOUND + Math.random() * (Y_LOWER_BOUND - Y_UPPER_BOUND);
                    addEnemyUnit(new EnemyPlane(Main.SCREEN_WIDTH, newEnemyInitialYPosition));
                }
            }
        }

        if (currentNumberOfEnemies < increasingTotalEnemies && elapsedTime % 60 == 0) {
            for (int i = 0; i < increasingTotalEnemies - currentNumberOfEnemies; i++) {
                if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                    double newEnemyInitialYPosition = Y_UPPER_BOUND + Math.random() * (Y_LOWER_BOUND - Y_UPPER_BOUND);
                    addEnemyUnit(new AdvancedPlane(Main.SCREEN_WIDTH, newEnemyInitialYPosition));
                }
            }
        }

        // Spawn boss plane (every 10 seconds)
        if (elapsedTime % 200 == 0) {
            Boss boss = new Boss(new LevelViewBoss(getRoot(), PLAYER_INITIAL_HEALTH));
            addEnemyUnit(boss);
        }
    }


    private void updateTotalEnemies() {
        int increaseInterval = 200; // Time interval (ticks) for increasing enemies
        if (elapsedTime % increaseInterval == 0 && increasingTotalEnemies < MAX_TOTAL_ENEMIES) {
            increasingTotalEnemies++;
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        return new LevelViewEndless(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    @Override
    protected void loseGame() {
        timeline.stop();
        MusicManager.getInstance().stopBackgroundMusic();

        GameOverMenu gameOverMenu = new GameOverMenu(stage, highScoreManager);
        gameOverMenu.endlessShow(getUser().getNumberOfKills());
    }
}
