package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import com.example.demo.menu.MenuEndlessInstruction;
import com.example.demo.levels.LevelEndless;
import com.example.demo.menu.MainMenu;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.levels.LevelParent;

/**
 * Controller class that manages the game levels and transitions.
 */
public class Controller implements Observer {

    public static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.levels.LevelOne";
    private final Stage stage;

    /**
     * Constructs a Controller with the specified stage.
     *
     * @param stage the primary stage for this application
     */
    public Controller(Stage stage) {
        this.stage = stage;
    }

    /**
     * Launches the game by showing the stage and going to the first level.
     *
     * @throws ClassNotFoundException if the class cannot be located
     * @throws NoSuchMethodException if a matching method is not found
     * @throws SecurityException if a security violation occurs
     * @throws InstantiationException if the class cannot be instantiated
     * @throws IllegalAccessException if the class or its nullary constructor is not accessible
     * @throws IllegalArgumentException if the method is invoked with incorrect arguments
     * @throws InvocationTargetException if the underlying constructor throws an exception
     */
    public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        stage.show();
        goToLevel(LEVEL_ONE_CLASS_NAME);
    }

    /**
     * Transitions to the specified level.
     *
     * @param className the fully qualified name of the level class
     * @throws ClassNotFoundException if the class cannot be located
     * @throws NoSuchMethodException if a matching method is not found
     * @throws SecurityException if a security violation occurs
     * @throws InstantiationException if the class cannot be instantiated
     * @throws IllegalAccessException if the class or its nullary constructor is not accessible
     * @throws IllegalArgumentException if the method is invoked with incorrect arguments
     * @throws InvocationTargetException if the underlying constructor throws an exception
     */
    public void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> myClass = Class.forName(className);
        Constructor<?> constructor = myClass.getConstructor(double.class, double.class, Controller.class, Stage.class);
        LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth(), this, stage);
        myLevel.addObserver(this);
        Scene scene = myLevel.initializeScene();
        stage.setScene(scene);
        myLevel.startGame();
    }

    /**
     * Starts the endless mode of the game.
     */
    public void startEndlessMode() {
        LevelEndless endlessMode = new LevelEndless(stage.getHeight(), stage.getWidth(), this, stage);
        stage.setScene(endlessMode.initializeScene());
        endlessMode.startGame();
    }

    /**
     * Shows the instructions for the endless mode.
     */
    public void showEndlessInstructions() {
        MenuEndlessInstruction endlessInstruction = new MenuEndlessInstruction(stage, this);
        endlessInstruction.show();
    }

    /**
     * Returns to the main menu.
     */
    public void backToMainMenu() {
        MainMenu mainMenu = new MainMenu(stage);
        mainMenu.show(); // Go back to the main menu
    }

    /**
     * Updates the controller based on the observable's state.
     *
     * @param arg0 the observable object
     * @param arg1 an argument passed to the notifyObservers method
     */
    @Override
    public void update(Observable arg0, Object arg1) {
        try {
            goToLevel((String) arg1);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getClass().toString());
            alert.show();
        }
    }
}