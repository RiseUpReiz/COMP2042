package com.example.demo.planes;

import com.example.demo.controller.ActiveActorDestructible;
import com.example.demo.projectiles.EnemyProjectile;

/**
 * Represents an enemy plane with specific attributes and behaviors.
 */
public class EnemyPlane extends FighterPlane {

    private static final String IMAGE_NAME = "enemyplane.png";
    private static final int IMAGE_HEIGHT = 60;
    private static final int HORIZONTAL_VELOCITY = -6;
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
    private static final int INITIAL_HEALTH = 1;
    public static double FIRE_RATE = .01;

    /**
     * Constructs an EnemyPlane with the specified initial position.
     *
     * @param initialXPos the initial X position of the plane
     * @param initialYPos the initial Y position of the plane
     */
    public EnemyPlane(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
    }

    /**
     * Updates the position of the plane by moving it horizontally.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Fires a projectile from the plane if the random condition based on FIRE_RATE is met.
     *
     * @return a new EnemyProjectile if fired, otherwise null
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < FIRE_RATE) {
            double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
            double projectileYPostion = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
            return new EnemyProjectile(projectileXPosition, projectileYPostion);
        }
        return null;
    }

    /**
     * Updates the actor by updating its position.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}