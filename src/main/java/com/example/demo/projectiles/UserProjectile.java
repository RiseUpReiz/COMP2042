package com.example.demo.projectiles;

/**
 * The UserProjectile class represents a projectile fired by the user character.
 * It extends the Projectile class and defines specific properties and behaviors
 * for the user's projectile.
 */
public class UserProjectile extends Projectile {

    // The name of the image file representing the projectile.
    private static final String IMAGE_NAME = "userfire.png";

    // The height of the projectile image.
    private static final int IMAGE_HEIGHT = 10;

    // The horizontal velocity of the projectile.
    private static final int HORIZONTAL_VELOCITY = 15;

    /**
     * Constructs a new UserProjectile with the specified initial X and Y positions.
     *
     * @param initialXPos the initial X position of the projectile
     * @param initialYPos the initial Y position of the projectile
     */
    public UserProjectile(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
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