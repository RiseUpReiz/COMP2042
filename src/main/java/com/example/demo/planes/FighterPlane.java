package com.example.demo.planes;

import com.example.demo.controller.ActiveActorDestructible;

/**
 * Represents a fighter plane with specific attributes and behaviors.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

    private int health;

    /**
     * Constructs a FighterPlane with the specified attributes.
     *
     * @param imageName the name of the image representing the plane
     * @param imageHeight the height of the image representing the plane
     * @param initialXPos the initial X position of the plane
     * @param initialYPos the initial Y position of the plane
     * @param health the initial health of the plane
     */
    public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        this.health = health;
    }

    /**
     * Fires a projectile from the plane.
     *
     * @return a new ActiveActorDestructible representing the fired projectile
     */
    public abstract ActiveActorDestructible fireProjectile();

    /**
     * Takes damage and decreases the health of the plane. Destroys the plane if health reaches zero.
     */
    @Override
    public void takeDamage() {
        health--;
        if (healthAtZero()) {
            this.destroy();
        }
    }

    /**
     * Gets the X position for the projectile based on the specified offset.
     *
     * @param xPositionOffset the offset for the X position
     * @return the calculated X position for the projectile
     */
    protected double getProjectileXPosition(double xPositionOffset) {
        return getLayoutX() + getTranslateX() + xPositionOffset;
    }

    /**
     * Gets the Y position for the projectile based on the specified offset.
     *
     * @param yPositionOffset the offset for the Y position
     * @return the calculated Y position for the projectile
     */
    protected double getProjectileYPosition(double yPositionOffset) {
        return getLayoutY() + getTranslateY() + yPositionOffset;
    }

    /**
     * Checks if the health of the plane is zero.
     *
     * @return true if the health is zero, false otherwise
     */
    private boolean healthAtZero() {
        return health == 0;
    }

    /**
     * Gets the current health of the plane.
     *
     * @return the current health of the plane
     */
    public int getHealth() {
        return health;
    }
}