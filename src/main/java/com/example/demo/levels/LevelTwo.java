package com.example.demo.levels;

import com.example.demo.controller.ActiveActorDestructible;
import com.example.demo.controller.Main;
import com.example.demo.planes.AdvancedPlane;
import com.example.demo.controller.Controller;
import javafx.stage.Stage;

/**
 * Represents the second level in the game.
 * This class handles the initialization, spawning of enemy units, and game over conditions for the second level.
 */
public class LevelTwo extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background5.jfif";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelBoss";
    private static final double Y_UPPER_BOUND = 70;
    private static final double Y_LOWER_BOUND = 650.0;
    private static final int TOTAL_ENEMIES = 6;
    private static final int KILLS_TO_ADVANCE = 10;
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /**
     * Constructs a LevelTwo instance.
     *
     * @param screenHeight the height of the screen
     * @param screenWidth the width of the screen
     * @param controller the game controller
     * @param stage the stage for the game
     */
    public LevelTwo(double screenHeight, double screenWidth, Controller controller, Stage stage) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, controller, stage);
    }

    /**
     * Checks if the game is over.
     * The game is over if the user is destroyed or the user has reached the kill target.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (userHasReachedKillTarget()) {
            levelView.removeKillCounter();
            goToNextLevel(NEXT_LEVEL);
        }
    }

    /**
     * Initializes the friendly units in the game.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Spawns enemy units based on the spawn probability.
     */
    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
            if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition = Y_UPPER_BOUND + Math.random() * (Y_LOWER_BOUND - Y_UPPER_BOUND);
                ActiveActorDestructible newEnemy = new AdvancedPlane(Main.SCREEN_WIDTH, newEnemyInitialYPosition);
                addEnemyUnit(newEnemy);
            }
        }
    }

    /**
     * Instantiates the level view for the second level.
     *
     * @return the level view for the second level
     */
    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Updates the scene, including the kill counter.
     */
    @Override
    public void updateScene() {
        super.updateScene();
        int currentKills = getUser().getNumberOfKills();
        levelView.updateKillCounter(currentKills, KILLS_TO_ADVANCE);
    }

    /**
     * Checks if the user has reached the kill target.
     *
     * @return true if the user has reached the kill target, false otherwise
     */
    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }
}