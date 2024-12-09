package com.example.demo.controller;

/**
 * The ActiveActorDestructible class is an abstract class that represents an active actor
 * in the game which can be destroyed. It extends the ActiveActor class and implements
 * the Destructible interface.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

    // Indicates whether the actor is destroyed.
    private boolean isDestroyed;

    /**
     * Constructs a new ActiveActorDestructible with the specified image name, image height,
     * initial X position, and initial Y position.
     *
     * @param imageName the name of the image file representing the actor
     * @param imageHeight the height of the actor image
     * @param initialXPos the initial X position of the actor
     * @param initialYPos the initial Y position of the actor
     */
    public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        isDestroyed = false;
    }

    /**
     * Updates the position of the actor. This method must be implemented by subclasses.
     */
    @Override
    public abstract void updatePosition();

    /**
     * Updates the state of the actor. This method must be implemented by subclasses.
     */
    public abstract void updateActor();

    /**
     * Takes damage and destroys the actor. This method must be implemented by subclasses.
     */
    @Override
    public abstract void takeDamage();

    /**
     * Destroys the actor by setting its destroyed state to true.
     */
    @Override
    public void destroy() {
        setDestroyed(true);
    }

    /**
     * Sets the destroyed state of the actor.
     *
     * @param isDestroyed the new destroyed state of the actor
     */
    protected void setDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }

    /**
     * Checks if the actor is destroyed.
     *
     * @return true if the actor is destroyed, false otherwise
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }
}