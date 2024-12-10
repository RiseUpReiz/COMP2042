package com.example.demo.planes;

import com.example.demo.controller.ActiveActorDestructible;
import com.example.demo.projectiles.BossProjectile;
import com.example.demo.levels.LevelViewBoss;

import java.util.*;

/**
 * Represents a boss plane with specific attributes and behaviors.
 */
public class Boss extends FighterPlane {

 private static final String IMAGE_NAME = "bossplane.png";
 private static final double INITIAL_X_POSITION = 1000.0;
 private static final double INITIAL_Y_POSITION = 400;
 private static final double PROJECTILE_Y_POSITION_OFFSET = 40.0;
 private static final double BOSS_FIRE_RATE = .03;
 private static final double BOSS_SHIELD_PROBABILITY = .002;
 private static final int IMAGE_HEIGHT = 70;
 private static final int VERTICAL_VELOCITY = 8;
 private static final int HEALTH = 10;
 private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
 private static final int ZERO = 0;
 private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
 private static final int Y_POSITION_UPPER_BOUND = 30;
 private static final int Y_POSITION_LOWER_BOUND = 625;
 private static final int SHIELD_X_OFFSET = -35;
 private static final int MAX_FRAMES_WITH_SHIELD = 500;
 private final List<Integer> movePattern;
 private boolean isShielded;
 private int consecutiveMovesInSameDirection;
 private int indexOfCurrentMove;
 private int framesWithShieldActivated;
 private final LevelViewBoss levelView;

 /**
  * Constructs a Boss with the specified level view.
  *
  * @param levelView the level view associated with the boss
  */
 public Boss(LevelViewBoss levelView) {
  super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
  movePattern = new ArrayList<>();
  consecutiveMovesInSameDirection = 0;
  indexOfCurrentMove = 0;
  framesWithShieldActivated = 0;
  isShielded = false;
  this.levelView = levelView;
  initializeMovePattern();
 }

 /**
  * Updates the position of the boss by moving it vertically.
  */
 @Override
 public void updatePosition() {
  double initialTranslateY = getTranslateY();
  moveVertically(getNextMove());
  double currentPosition = getLayoutY() + getTranslateY();
  if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
   setTranslateY(initialTranslateY);
  }
 }

 /**
  * Updates the actor by updating its position and shield status.
  */
 @Override
 public void updateActor() {
  updatePosition();
  updateShield();
 }

 /**
  * Fires a projectile from the boss if the random condition based on BOSS_FIRE_RATE is met.
  *
  * @return a new BossProjectile if fired, otherwise null
  */
 @Override
 public ActiveActorDestructible fireProjectile() {
  return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
 }

 /**
  * Takes damage if the boss is not shielded.
  */
 @Override
 public void takeDamage() {
  if (!isShielded) {
   super.takeDamage();
  }
 }

 /**
  * Initializes the move pattern for the boss.
  */
 private void initializeMovePattern() {
  for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
   movePattern.add(VERTICAL_VELOCITY);
   movePattern.add(-VERTICAL_VELOCITY);
   movePattern.add(ZERO);
  }
  Collections.shuffle(movePattern);
 }

 /**
  * Updates the shield status of the boss.
  */
 public void updateShield() {
  if (isShielded) {
   framesWithShieldActivated++;
   updateShieldPosition();
  } else if (shieldShouldBeActivated()) {
   activateShield();
  }
  if (shieldExhausted()) {
   deactivateShield();
  }
 }

 /**
  * Updates the position of the shield.
  */
 private void updateShieldPosition(){
  levelView.updateShieldPosition(
    getLayoutX() + getTranslateX() + SHIELD_X_OFFSET,
    getLayoutY() + getTranslateY());
 }

 /**
  * Gets the next move for the boss based on the move pattern.
  *
  * @return the next move value
  */
 private int getNextMove() {
  int currentMove = movePattern.get(indexOfCurrentMove);
  consecutiveMovesInSameDirection++;
  if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
   Collections.shuffle(movePattern);
   consecutiveMovesInSameDirection = 0;
   indexOfCurrentMove++;
  }
  if (indexOfCurrentMove == movePattern.size()) {
   indexOfCurrentMove = 0;
  }
  return currentMove;
 }

 /**
  * Determines if the boss should fire a projectile in the current frame.
  *
  * @return true if the boss should fire, false otherwise
  */
 private boolean bossFiresInCurrentFrame() {
  return Math.random() < BOSS_FIRE_RATE;
 }

 /**
  * Gets the initial position for the projectile.
  *
  * @return the initial Y position for the projectile
  */
 private double getProjectileInitialPosition() {
  return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
 }

 /**
  * Determines if the shield should be activated.
  *
  * @return true if the shield should be activated, false otherwise
  */
 private boolean shieldShouldBeActivated() {
  return Math.random() < BOSS_SHIELD_PROBABILITY;
 }

 /**
  * Determines if the shield is exhausted.
  *
  * @return true if the shield is exhausted, false otherwise
  */
 private boolean shieldExhausted() {
  return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
 }

 /**
  * Activates the shield for the boss.
  */
 public void activateShield() {
  isShielded = true;
  levelView.showShield();
  updateShieldPosition();
 }

 /**
  * Deactivates the shield for the boss.
  */
 private void deactivateShield() {
  isShielded = false;
  framesWithShieldActivated = 0;
  levelView.hideShield();
 }

 public boolean isShielded() {
    return isShielded;
 }

}