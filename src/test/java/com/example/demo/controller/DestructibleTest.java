package com.example.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class DestructibleTest {

    private Destructible destructible;

    @BeforeEach
    void setUp() {
        // Mock the Destructible interface
        destructible = Mockito.mock(Destructible.class);
    }

    @Test
    void testTakeDamage() {
        // Call the takeDamage method
        destructible.takeDamage();

        // Verify that takeDamage() was called on the destructible object
        verify(destructible).takeDamage();
    }

    @Test
    void testDestroy() {
        // Call the destroy method
        destructible.destroy();

        // Verify that destroy() was called on the destructible object
        verify(destructible).destroy();
    }
}
