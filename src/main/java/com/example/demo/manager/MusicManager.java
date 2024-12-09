package com.example.demo.manager;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Singleton class that manages background music and sound effects for the game.
 */
public class MusicManager {
    private static MusicManager instance;
    private MediaPlayer backgroundMusicPlayer;
    private MediaPlayer soundEffectPlayer; // Instance variable for sound effects

    /**
     * Private constructor to prevent instantiation.
     */
    private MusicManager() {
    }

    /**
     * Returns the singleton instance of the MusicManager.
     *
     * @return the singleton instance of the MusicManager
     */
    public static MusicManager getInstance() {
        if (instance == null) {
            instance = new MusicManager();
        }
        return instance;
    }

    /**
     * Plays background music from the specified file path.
     *
     * @param musicFilePath the file path of the background music
     */
    public void playBackgroundMusic(String musicFilePath) {
        Media media = new Media(getClass().getResource(musicFilePath).toExternalForm());
        backgroundMusicPlayer = new MediaPlayer(media);
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop music
        backgroundMusicPlayer.setRate(0.9); // Set playback speed
        backgroundMusicPlayer.play();
    }

    /**
     * Stops the background music if it is playing.
     */
    public void stopBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
        }
    }

    /**
     * Sets the pause state of the background music.
     *
     * @param isPause true to pause the music, false to play it
     */
    public void setPauseState(boolean isPause) {
        if (backgroundMusicPlayer != null) {
            if (isPause) {
                backgroundMusicPlayer.pause();
            } else {
                backgroundMusicPlayer.play();
            }
        }
    }

    /**
     * Plays a sound effect from the specified file path.
     *
     * @param soundFilePath the file path of the sound effect
     */
    public void playSoundEffect(String soundFilePath) {
        if (soundEffectPlayer != null) {
            soundEffectPlayer.stop(); // Stop any currently playing sound effect
        }
        Media media = new Media(getClass().getResource(soundFilePath).toExternalForm());
        soundEffectPlayer = new MediaPlayer(media);
        soundEffectPlayer.play();
    }
}