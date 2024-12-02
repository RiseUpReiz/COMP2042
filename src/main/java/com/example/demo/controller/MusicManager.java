package com.example.demo.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicManager {
    private static MusicManager instance;
    private MediaPlayer backgroundMusicPlayer;
    private MediaPlayer soundEffectPlayer; // Instance variable for sound effects

    private MusicManager() {
    }

    public static MusicManager getInstance() {
        if (instance == null) {
            instance = new MusicManager();
        }
        return instance;
    }

    // Play background music
    public void playBackgroundMusic(String musicFilePath) {
        Media media = new Media(getClass().getResource(musicFilePath).toExternalForm());
        backgroundMusicPlayer = new MediaPlayer(media);
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop music
        backgroundMusicPlayer.setRate(0.9); // Set playback speed
        backgroundMusicPlayer.play();
    }

    public void stopBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
        }
    }

    // Manage background music state based on isPause
    public void setPauseState(boolean isPause) {
        if (backgroundMusicPlayer != null) {
            if (isPause) {
                backgroundMusicPlayer.pause();
            } else {
                backgroundMusicPlayer.play();
            }
        }
    }

    // Play a sound effect (non-looping)
    public void playSoundEffect(String soundFilePath) {
        if (soundEffectPlayer != null) {
            soundEffectPlayer.stop(); // Stop any currently playing sound effect
        }
        Media media = new Media(getClass().getResource(soundFilePath).toExternalForm());
        soundEffectPlayer = new MediaPlayer(media);
        soundEffectPlayer.play();
    }
}
