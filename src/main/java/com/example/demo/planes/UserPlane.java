package com.example.demo.planes;

import com.example.demo.controller.ActiveActorDestructible;
import com.example.demo.projectiles.UserProjectile;

/**
 * Represents a user-controlled plane with specific attributes and behaviors.
 */
public class UserPlane extends FighterPlane {

    private static final String IMAGE_NAME = "userplane.png";
    private static final double Y_UPPER_BOUND = 70;
    private static final double Y_LOWER_BOUND = 675.0;
    private static final double INITIAL_X_POSITION = 5.0;
    private static final double INITIAL_Y_POSITION = 300.0;
    private static final int IMAGE_HEIGHT = 30;
    private static final int VERTICAL_VELOCITY = 10; // initial rate 8
    private static final int PROJECTILE_X_POSITION = 110;
    private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
    private int velocityMultiplier;
    private int numberOfKills;

    /**
     * Constructs a UserPlane with the specified initial health.
     *
     * @param initialHealth the initial health of the plane
     */
    public UserPlane(int initialHealth) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
        velocityMultiplier = 0;
    }

    /**
     * Updates the position of the plane by moving it vertically.
     */
    @Override
    public void updatePosition() {
        if (isMoving()) {
            double initialTranslateY = getTranslateY();
            this.moveVertically(VERTICAL_VELOCITY * velocityMultiplier);
            double newPosition = getLayoutY() + getTranslateY();
            if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
                this.setTranslateY(initialTranslateY);
            }
        }
    }

    /**
     * Updates the actor by updating its position.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }

    /**
     * Fires a projectile from the plane.
     *
     * @return a new UserProjectile representing the fired projectile
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
    }

    /**
     * Checks if the plane is currently moving.
     *
     * @return true if the plane is moving, false otherwise
     */
    private boolean isMoving() {
        return velocityMultiplier != 0;
    }

    /**
     * Sets the plane to move upwards.
     */
    public void moveUp() {
        velocityMultiplier = -1;
    }

    /**
     * Sets the plane to move downwards.
     */
    public void moveDown() {
        velocityMultiplier = 1;
    }

    /**
     * Stops the plane's movement.
     */
    public void stop() {
        velocityMultiplier = 0;
    }

    /**
     * Gets the number of kills made by the plane.
     *
     * @return the number of kills
     */
    public int getNumberOfKills() {
        return numberOfKills;
    }

    /**
     * Increments the kill count of the plane.
     */
    public void incrementKillCount() {
        numberOfKills++;
    }
}