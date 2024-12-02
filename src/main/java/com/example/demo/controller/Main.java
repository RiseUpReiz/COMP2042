package com.example.demo.controller;

import com.example.demo.levels.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static final int SCREEN_WIDTH = 1300;
	public static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "Sky Battle";
	private static final String BGM_FILE_PATH = "/com/example/demo/audios/bgm.mp3";


	public static int getScreenWidth() {
		return SCREEN_WIDTH;
	}

	public static int getScreenHeight() {
		return SCREEN_HEIGHT;
	}

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

	public static void main(String[] args) {
		launch();
	}
}
