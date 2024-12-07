package com.example.demo.projectiles;

import com.example.demo.ActiveActorDestructible;
import com.example.demo.controller.Main;

/**
 * The Projectile class is an abstract class that represents a projectile in the game.
 * It extends the ActiveActorDestructible class and provides common functionality
 * for all projectiles, such as taking damage and updating the actor's state.
 */
public abstract class Projectile extends ActiveActorDestructible {

    /**
     * Constructs a new Projectile with the specified image name, image height,
     * initial X position, and initial Y position.
     *
     * @param imageName the name of the image file representing the projectile
     * @param imageHeight the height of the projectile image
     * @param initialXPos the initial X position of the projectile
     * @param initialYPos the initial Y position of the projectile
     */
    public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        super(imageName, imageHeight, initialXPos, initialYPos);
    }

    /**
     * Takes damage and destroys the projectile.
     */
    @Override
    public void takeDamage() {
        this.destroy();
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

    /**
     * Checks if the projectile is out of the screen.
     *
     * @return true if the projectile is out of the screen, false otherwise
     */
    public boolean outOfScreen() {
        return getTranslateX() > Main.getScreenWidth();
    }
}