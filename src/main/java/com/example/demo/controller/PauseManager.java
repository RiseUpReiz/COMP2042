package com.example.demo.controller;

import com.example.demo.levels.LevelView;
import javafx.animation.Timeline;

public class PauseManager {
    private boolean isPause;
    private final Timeline timeline;
    private final LevelView levelView;

    public PauseManager(Timeline timeline, LevelView levelView) {
        this.timeline = timeline;
        this.levelView = levelView;
        this.isPause = false;
    }

    public void togglePause() {
        if (!isPause) {
            isPause = true;
            timeline.pause();
            MusicManager.getInstance().setPauseState(true); // Pause background music
            levelView.showPauseMenu(); // Show the pause menu
        } else {
            isPause = false;
            timeline.play();
            MusicManager.getInstance().setPauseState(false); // Resume background music
            levelView.hidePauseMenu(); // Hide the pause menu
        }
    }

    public boolean isPause() {
        return isPause;
    }
}