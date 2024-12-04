package com.example.demo;

/**
 * The Destructible interface defines the methods that must be implemented
 * by any class that represents a destructible object in the game.
 */
public interface Destructible {

    /**
     * Takes damage and processes the destruction of the object.
     */
    void takeDamage();

    /**
     * Destroys the object.
     */
    void destroy();
}