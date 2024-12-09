package com.example.demo.manager;

import com.example.demo.levels.LevelView;
import javafx.animation.Timeline;

/**
 * Manages the pause state of the game.
 */
public class PauseManager {
    private boolean isPause;
    private final Timeline timeline;
    private final LevelView levelView;

    /**
     * Constructs a PauseManager instance.
     *
     * @param timeline the timeline controlling the game animations
     * @param levelView the view of the current level
     */
    public PauseManager(Timeline timeline, LevelView levelView) {
        this.timeline = timeline;
        this.levelView = levelView;
        this.isPause = false;
    }

    /**
     * Toggles the pause state of the game.
     * Pauses or resumes the game timeline, background music, and shows or hides the pause menu.
     */
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

    /**
     * Returns the current pause state.
     *
     * @return true if the game is paused, false otherwise
     */
    public boolean isPause() {
        return isPause;
    }
}