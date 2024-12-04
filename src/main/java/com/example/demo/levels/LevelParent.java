package com.example.demo.levels;

import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.ActiveActorDestructible;
import com.example.demo.menu.MenuGameOver;
import com.example.demo.menu.MenuWin;
import com.example.demo.planes.FighterPlane;
import com.example.demo.planes.UserPlane;
import com.example.demo.controller.Controller;
import com.example.demo.controller.HighScoreManager;
import com.example.demo.controller.MusicManager;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Abstract class representing a level in the game.
 * This class provides common functionality for all levels, including initialization, updating actors, and handling game over conditions.
 */
public abstract class LevelParent extends Observable {

    private static final int MILLISECOND_DELAY = 50;
    private final double screenHeight;
    private final double screenWidth;

    private final Group root;
    public final Timeline timeline;
    private final UserPlane user;
    private final Scene scene;
    private final ImageView background;

    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;

    private int currentNumberOfEnemies;
    public final LevelView levelView;
    public boolean isPause = false;

    public final Stage stage;
    private HighScoreManager highScoreManager;

    /**
     * Constructs a LevelParent instance.
     *
     * @param backgroundImageName the name of the background image
     * @param screenHeight the height of the screen
     * @param screenWidth the width of the screen
     * @param playerInitialHealth the initial health of the player
     * @param controller the game controller
     * @param stage the stage for the game
     */
    public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth, Controller controller, Stage stage) {
        this.root = new Group();
        this.scene = new Scene(root, screenWidth, screenHeight);
        this.timeline = new Timeline();
        this.user = new UserPlane(playerInitialHealth);
        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();

        this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.levelView = instantiateLevelView();
        this.currentNumberOfEnemies = 0;
        this.stage = stage;

        initializeTimeline();
        friendlyUnits.add(user);
    }

    /**
     * Initializes the friendly units in the game.
     */
    protected abstract void initializeFriendlyUnits();

    /**
     * Checks if the game is over.
     */
    protected abstract void checkIfGameOver();

    /**
     * Spawns enemy units in the game.
     */
    protected abstract void spawnEnemyUnits();

    /**
     * Instantiates the level view.
     *
     * @return the level view
     */
    protected abstract LevelView instantiateLevelView();

    /**
     * Initializes the scene for the level.
     *
     * @return the scene
     */
    public Scene initializeScene() {
        initializeBackground();
        initializeFriendlyUnits();
        levelView.showHeartDisplay();
        return scene;
    }

    /**
     * Starts the game.
     */
    public void startGame() {
        background.requestFocus();
        timeline.play();
    }

    /**
     * Advances to the next level.
     *
     * @param levelName the name of the next level
     */
    public void goToNextLevel(String levelName) {
        timeline.stop();
        setChanged();
        notifyObservers(levelName);
    }

    /**
     * Updates the scene, including spawning enemy units and handling collisions.
     */
    public void updateScene() {
        spawnEnemyUnits();
        updateActors();
        generateEnemyFire();
        updateNumberOfEnemies();
        handleEnemyPenetration();
        handleUserProjectileCollisions();
        handleEnemyProjectileCollisions();
        handlePlaneCollisions();
        removeAllDestroyedActors();
        updateKillCount();
        updateLevelView();
        checkIfGameOver();
    }

    /**
     * Initializes the timeline for the game loop.
     */
    private void initializeTimeline() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
        timeline.getKeyFrames().add(gameLoop);
    }

    /**
     * Initializes the background for the level.
     */
    private void initializeBackground() {
        background.setFocusTraversable(true);
        background.setFitHeight(screenHeight);
        background.setFitWidth(screenWidth);
        background.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.UP) user.moveUp();
                if (kc == KeyCode.DOWN) user.moveDown();
                if (kc == KeyCode.SPACE) fireProjectile();
                if (kc == KeyCode.ESCAPE) pauseLevel();
            }
        });
        background.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
            }
        });
        root.getChildren().add(background);
    }

    /**
     * Checks if firing is allowed.
     *
     * @return true if firing is allowed, false otherwise
     */
    private boolean allowFiring() {
        return !isPause;
    }

    /**
     * Fires a projectile from the user plane.
     */
    private void fireProjectile() {
        if (!allowFiring()) return;
        ActiveActorDestructible projectile = user.fireProjectile();
        root.getChildren().add(projectile);
        userProjectiles.add(projectile);
    }

    /**
     * Generates enemy fire.
     */
    private void generateEnemyFire() {
        enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
    }

    /**
     * Spawns an enemy projectile.
     *
     * @param projectile the enemy projectile
     */
    private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
        if (projectile != null) {
            root.getChildren().add(projectile);
            enemyProjectiles.add(projectile);
        }
    }

    /**
     * Updates the actors in the game.
     */
    private void updateActors() {
        friendlyUnits.forEach(plane -> plane.updateActor());
        enemyUnits.forEach(enemy -> enemy.updateActor());
        userProjectiles.forEach(projectile -> projectile.updateActor());
        enemyProjectiles.forEach(projectile -> projectile.updateActor());
    }

    /**
     * Removes all destroyed actors from the game.
     */
    private void removeAllDestroyedActors() {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
    }

    /**
     * Removes destroyed actors from a list.
     *
     * @param actors the list of actors
     */
    private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
                .collect(Collectors.toList());
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    /**
     * Handles collisions between planes.
     */
    private void handlePlaneCollisions() {
        handleCollisions(friendlyUnits, enemyUnits);
    }

    /**
     * Handles collisions between user projectiles and enemy units.
     */
    private void handleUserProjectileCollisions() {
        handleCollisions(userProjectiles, enemyUnits);
    }

    /**
     * Handles collisions between enemy projectiles and friendly units.
     */
    private void handleEnemyProjectileCollisions() {
        handleCollisions(enemyProjectiles, friendlyUnits);
    }

    /**
     * Handles collisions between two lists of actors.
     *
     * @param actors1 the first list of actors
     * @param actors2 the second list of actors
     */
    private void handleCollisions(List<ActiveActorDestructible> actors1, List<ActiveActorDestructible> actors2) {
        for (ActiveActorDestructible actor : actors2) {
            for (ActiveActorDestructible otherActor : actors1) {
                if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
                    actor.takeDamage();
                    otherActor.takeDamage();
                }
            }
        }
    }

    /**
     * Handles enemy penetration of defenses.
     */
    private void handleEnemyPenetration() {
        for (ActiveActorDestructible enemy : enemyUnits) {
            if (enemyHasPenetratedDefenses(enemy)) {
                user.takeDamage();
                enemy.destroy();
            }
        }
    }

    /**
     * Updates the level view.
     */
    private void updateLevelView() {
        levelView.removeHearts(user.getHealth());
    }

    /**
     * Updates the kill count.
     */
    private void updateKillCount() {
        for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
            user.incrementKillCount();
        }
    }

    /**
     * Checks if an enemy has penetrated defenses.
     *
     * @param enemy the enemy
     * @return true if the enemy has penetrated defenses, false otherwise
     */
    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }

    /**
     * Handles the win game scenario.
     */
    public void winGame() {
        if (!allowFiring()) return;
        timeline.stop();
        MusicManager.getInstance().stopBackgroundMusic();
        MenuWin winMenu = new MenuWin(stage);
        winMenu.show();
    }

    /**
     * Handles the lose game scenario.
     */
    protected void loseGame() {
        if (!allowFiring()) return;
        timeline.stop();
        MusicManager.getInstance().stopBackgroundMusic();
        MenuGameOver gameOverMenu = new MenuGameOver(stage, highScoreManager);
        gameOverMenu.show();
    }

    /**
     * Pauses or resumes the level.
     */
    private void pauseLevel() {
        if (!isPause) {
            isPause = true;
            timeline.pause();
            MusicManager.getInstance().setPauseState(true); // Pause background music
            levelView.showPauseMenu(); // Show the pause menu
        } else {
            isPause = false;
            timeline.play();
            MusicManager.getInstance().setPauseState(false); // Resume background music
            levelView.hidePauseMenu(); // Hide the pause menu
        }
    }

    /**
     * Gets the user plane.
     *
     * @return the user plane
     */
    protected UserPlane getUser() {
        return user;
    }

    /**
     * Gets the root group.
     *
     * @return the root group
     */
    protected Group getRoot() {
        return root;
    }

    /**
     * Gets the current number of enemies.
     *
     * @return the current number of enemies
     */
    protected int getCurrentNumberOfEnemies() {
        return enemyUnits.size();
    }

    /**
     * Adds an enemy unit to the game.
     *
     * @param enemy the enemy unit
     */
    protected void addEnemyUnit(ActiveActorDestructible enemy) {
        enemyUnits.add(enemy);
        root.getChildren().add(enemy);
    }

    /**
     * Checks if the user is destroyed.
     *
     * @return true if the user is destroyed, false otherwise
     */
    public boolean userIsDestroyed() {
        return user.isDestroyed();
    }

    /**
     * Updates the number of enemies.
     */
    private void updateNumberOfEnemies() {
        currentNumberOfEnemies = enemyUnits.size();
    }
}