package com.example.demo.controller;

import javafx.scene.image.*;

/**
 * The ActiveActor class is an abstract class that represents an active actor in the game.
 * It extends the ImageView class and provides common functionality for all active actors,
 * such as setting the image, initial position, and movement.
 */
public abstract class ActiveActor extends ImageView {

    // The location of the image files.
    private static final String IMAGE_LOCATION = "/com/example/demo/images/";

    /**
     * Constructs a new ActiveActor with the specified image name, image height,
     * initial X position, and initial Y position.
     *
     * @param imageName the name of the image file representing the actor
     * @param imageHeight the height of the actor image
     * @param initialXPos the initial X position of the actor
     * @param initialYPos the initial Y position of the actor
     */
    public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        // Set the image of the actor using the specified image name.
        this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
        // Set the initial X position of the actor.
        this.setLayoutX(initialXPos);
        // Set the initial Y position of the actor.
        this.setLayoutY(initialYPos);
        // Set the height of the actor image.
        this.setFitHeight(imageHeight);
        // Preserve the aspect ratio of the actor image.
        this.setPreserveRatio(true);
    }

    /**
     * Updates the position of the actor. This method must be implemented by subclasses.
     */
    public abstract void updatePosition();

    /**
     * Moves the actor horizontally by the specified amount.
     *
     * @param horizontalMove the amount to move the actor horizontally
     */
    protected void moveHorizontally(double horizontalMove) {
        this.setTranslateX(getTranslateX() + horizontalMove);
    }

    /**
     * Moves the actor vertically by the specified amount.
     *
     * @param verticalMove the amount to move the actor vertically
     */
    protected void moveVertically(double verticalMove) {
        this.setTranslateY(getTranslateY() + verticalMove);
    }
}