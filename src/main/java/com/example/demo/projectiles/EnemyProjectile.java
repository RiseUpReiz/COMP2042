package com.example.demo.projectiles;

/**
 * The EnemyProjectile class represents a projectile fired by an enemy character.
 * It extends the Projectile class and defines specific properties and behaviors
 * for the enemy's projectile.
 */
public class EnemyProjectile extends Projectile {

    // Name of enemyprojectile image
    private static final String IMAGE_NAME = "enemyFire.png";

    // The height of the projectile image
    private static final int IMAGE_HEIGHT = 20;

    // Horizontal velocity of the projectile
    private static final int HORIZONTAL_VELOCITY = -10;

    /**
     * Constructs a new EnemyProjectile with the specified initial X and Y positions.
     *
     * @param initialXPos the initial X position of the projectile
     * @param initialYPos the initial Y position of the projectile
     */
    public EnemyProjectile(double initialXPos, double initialYPos) {
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