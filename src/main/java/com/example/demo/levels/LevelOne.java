package com.example.demo.levels;

import com.example.demo.ActiveActorDestructible;
import com.example.demo.controller.Main;
import com.example.demo.planes.EnemyPlane;
import com.example.demo.controller.Controller;
import javafx.stage.Stage;

public class LevelOne extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background5.jfif";
	private static final String NEXT_LEVEL = "com.example.demo.levels.LevelBoss";
	private static final double Y_UPPER_BOUND = 70;
	private static final double Y_LOWER_BOUND = 650.0;
	private static final int TOTAL_ENEMIES = 7;
	private static final int KILLS_TO_ADVANCE = 5; // initial 10
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	public LevelOne(double screenHeight, double screenWidth, Controller controller, Stage stage) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, controller, stage);
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (userHasReachedKillTarget()) {
			levelView.removeKillCounter(); // Remove kill counter when entering boss stage
			goToNextLevel(NEXT_LEVEL);
		}
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Y_UPPER_BOUND + Math.random() * (Y_LOWER_BOUND - Y_UPPER_BOUND);
				ActiveActorDestructible newEnemy = new EnemyPlane(Main.SCREEN_WIDTH , newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	@Override
	public void updateScene() {
		super.updateScene();

		// Update kill counter dynamically
		int currentKills = getUser().getNumberOfKills();
		levelView.updateKillCounter(currentKills, KILLS_TO_ADVANCE);
	}

	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}
}
