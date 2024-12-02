package com.example.demo.levels;

import com.example.demo.controller.Main;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LevelViewEndless extends LevelView {

    private static final double TIMER_LABEL_X_POSITION = (Main.SCREEN_WIDTH / 2.0) - 50;
    private static final double TIMER_LABEL_Y_POSITION = 25;

    private final Label timerLabel;

    public LevelViewEndless(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);

        // Timer Label
        this.timerLabel = new Label("Time: 0s");
        this.timerLabel.setFont(new Font("Arial", 25));
        this.timerLabel.setTextFill(Color.YELLOW);
        this.timerLabel.setLayoutX(TIMER_LABEL_X_POSITION);
        this.timerLabel.setLayoutY(TIMER_LABEL_Y_POSITION);
        root.getChildren().add(timerLabel);
    }

    public void updateTimer(int elapsedTime) {
        timerLabel.setText("Time: " + elapsedTime + "s");
    }
}
