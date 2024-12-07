package com.example.demo.projectiles;

/**
 * The BossProjectile class represents a projectile fired by a boss character.
 * It extends the Projectile class and defines specific properties and behaviors
 * for the boss's projectile.
 */
public class BossProjectile extends Projectile {

    // The name of the image file representing the projectile.
    private static final String IMAGE_NAME = "fireball.png";

    // The height of the projectile image.
    private static final int IMAGE_HEIGHT = 40;

    // The horizontal velocity of the projectile.
    private static final int HORIZONTAL_VELOCITY = -15;

    // The initial X position of the projectile.
    private static final int INITIAL_X_POSITION = 950;

    /**
     * Constructs a new BossProjectile with the specified initial Y position.
     *
     * @param initialYPos the initial Y position of the projectile
     */
    public BossProjectile(double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
    }

    /**
     * Updates the position of the projectile by moving it horizontally.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Updates the state of the projectile. This includes updating its position
     * and destroying it if it moves out of the screen.
     */
    @Override
    public void updateActor() {
        updatePosition();
        if (outOfScreen()) {
            this.destroy();
        }
    }
}