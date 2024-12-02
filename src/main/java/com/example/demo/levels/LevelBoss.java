package com.example.demo.levels;

import com.example.demo.planes.Boss;
import com.example.demo.controller.Controller;
import javafx.stage.Stage;

public class LevelBoss extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background4.jfif";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	public final Boss boss;
	private LevelViewBoss levelView;

	public LevelBoss(double screenHeight, double screenWidth, Controller controller, Stage stage) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, controller, stage);
		boss = new Boss(this.levelView);
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (boss.isDestroyed()) {
			winGame();
		}
	}

	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		this.levelView = new LevelViewBoss(getRoot(), PLAYER_INITIAL_HEALTH);
		return this.levelView;
	}

}
