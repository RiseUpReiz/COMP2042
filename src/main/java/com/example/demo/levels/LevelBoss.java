package com.example.demo.levels;

import com.example.demo.planes.Boss;
import com.example.demo.controller.Controller;
import javafx.stage.Stage;

/**
 * Represents the boss level in the game.
 * This class handles the initialization, spawning of the boss unit, and game over conditions for the boss level.
 */
public class LevelBoss extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background4.jfif";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    public final Boss boss;
    private LevelViewBoss levelView;

    /**
     * Constructs a LevelBoss instance.
     *
     * @param screenHeight the height of the screen
     * @param screenWidth the width of the screen
     * @param controller the game controller
     * @param stage the stage for the game
     */
    public LevelBoss(double screenHeight, double screenWidth, Controller controller, Stage stage) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, controller, stage);
        boss = new Boss(this.levelView);
    }

    /**
     * Initializes the friendly units in the game.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Checks if the game is over.
     * The game is over if the user is destroyed or the boss is destroyed.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (boss.isDestroyed()) {
            winGame();
        }
    }

    /**
     * Spawns enemy units in the game.
     * In this level, only the boss is spawned.
     */
    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(boss);
        }
    }

    /**
     * Instantiates the level view for the boss level.
     *
     * @return the level view for the boss level
     */
    @Override
    protected LevelView instantiateLevelView() {
        this.levelView = new LevelViewBoss(getRoot(), PLAYER_INITIAL_HEALTH);
        return this.levelView;
    }
}