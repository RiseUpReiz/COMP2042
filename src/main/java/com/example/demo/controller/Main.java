package com.example.demo.controller;

import com.example.demo.manager.MusicManager;
import com.example.demo.menu.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class that serves as the entry point for the JavaFX application.
 */
public class Main extends Application {

    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 750;
    private static final String TITLE = "Sky Battle";
    private static final String BGM_FILE_PATH = "/com/example/demo/audios/bgm.mp3";

    /**
     * Returns the screen width.
     *
     * @return the screen width
     */
    public static int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    /**
     * Returns the screen height.
     *
     * @return the screen height
     */
    public static int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    /**
     * Starts the JavaFX application by setting up the stage and showing the main menu.
     *
     * @param stage the primary stage for this application
     * @throws SecurityException if a security violation occurs
     * @throws IllegalArgumentException if an illegal argument is passed
     */
    @Override
    public void start(Stage stage) throws SecurityException, IllegalArgumentException {
        stage.setTitle(TITLE);
        stage.setResizable(false);
        stage.setHeight(SCREEN_HEIGHT);
        stage.setWidth(SCREEN_WIDTH);
        MusicManager.getInstance().playBackgroundMusic(BGM_FILE_PATH);
        MainMenu mainMenu = new MainMenu(stage);
        mainMenu.show();
    }

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}