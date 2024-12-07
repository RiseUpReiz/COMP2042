package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Manages the high score for the game.
 */
public class HighScoreManager {
    private static final String HIGH_SCORE_FILE = "src/main/resources/com/example/demo/text/highscore.txt";
    private int highScore;

    /**
     * Constructs a HighScoreManager and initializes the high score from the file.
     */
    public HighScoreManager() {
        this.highScore = readHighScoreFromFile();
    }

    /**
     * Returns the current high score.
     *
     * @return the current high score
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * Updates the high score if the current score is higher than the existing high score.
     *
     * @param currentScore the current score to compare with the high score
     */
    public void updateHighScore(int currentScore) {
        if (currentScore > highScore) {
            highScore = currentScore;
            writeHighScoreToFile(highScore);
        }
    }

    /**
     * Reads the high score from the file.
     *
     * @return the high score from the file, or 0 if the file does not exist or an error occurs
     */
    private int readHighScoreFromFile() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(HIGH_SCORE_FILE)))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Writes the high score to the file.
     *
     * @param highScore the high score to write to the file
     */
    private void writeHighScoreToFile(int highScore) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(HIGH_SCORE_FILE)))) {
            writer.write(String.valueOf(highScore));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}