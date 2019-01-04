package com.company;

import javafx.animation.AnimationTimer;
import javafx.animation.FillTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.util.Duration;

public class GameController {
    @FXML private Arc greenArc;
    @FXML private Arc redArc;
    @FXML private Arc yellowArc;
    @FXML private Arc blueArc;
    private SimonSay simonSays = new SimonSay();
    private AnimationTimer gameCheck;

    public void initialize() {
        gameCheck = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (simonSays.getCurrentIndex() == 0) {
                    playPattern();
                    this.stop();
                }
            }
        };
        startGameTimer();
    }

    private void startGameTimer() { gameCheck.start(); }

    @FXML
    private void selectGreen() {
        if (!simonSays.checkInput("Green")) {
            endGame();
        } else {
            animateTile(greenArc);
        }
        if (simonSays.getCurrentIndex() == 0) {
            startGameTimer();
        }
    }

    @FXML
    private void selectRed() {
        if (!simonSays.checkInput("Red")) {
            endGame();
        } else {
            animateTile(redArc);
        }
        if (simonSays.getCurrentIndex() == 0) {
            startGameTimer();
        }
    }

    @FXML
    private void selectYellow() {
        if (!simonSays.checkInput("Yellow")) {
            endGame();
        } else {
            animateTile(yellowArc);
        }
        if (simonSays.getCurrentIndex() == 0) {
            startGameTimer();
        }
    }

    @FXML
    private void selectBlue() {
        if (!simonSays.checkInput("Blue")) {
            endGame();
        } else {
            animateTile(blueArc);
        }
        if (simonSays.getCurrentIndex() == 0) {
            startGameTimer();
        }
    }

    private void animateTile(Arc arc) {
        SequentialTransition animation = new SequentialTransition();
        animation.setCycleCount(1);
        animation.setAutoReverse(true);
        FillTransition transition = createFillTransition(arc, 350);
        animation.getChildren().add(transition);
        animation.play();
    }

    private void playPattern() {
        SequentialTransition animation = new SequentialTransition();
        animation.setCycleCount(1);
        animation.setAutoReverse(true);
        for (String color : simonSays.getPattern()) {
            if (color.equals("Green")) {
                FillTransition greenFill = createFillTransition(greenArc, 650);
                animation.getChildren().add(greenFill);
            } else if (color.equals("Red")) {
                FillTransition redFill = createFillTransition(redArc, 650);
                animation.getChildren().add(redFill);
            } else if (color.equals("Yellow")) {
                FillTransition yellowFill = createFillTransition(yellowArc, 650);
                animation.getChildren().add(yellowFill);
            } else if (color.equals("Blue")) {
                FillTransition blueFill = createFillTransition(blueArc, 650);
                animation.getChildren().add(blueFill);
            }
        }
        animation.play();
    }

    private FillTransition createFillTransition(Arc arc, int milli) {
        FillTransition transition = new FillTransition(Duration.millis(milli), arc);
        transition.setAutoReverse(true);
        transition.setFromValue((Color)arc.getFill());
        if (arc == greenArc) {
            transition.setToValue(Color.rgb(0,255,0));
        } else if (arc == redArc) {
            transition.setToValue(Color.rgb(255,0,0));
        } else if (arc == yellowArc) {
            transition.setToValue(Color.rgb(255,255,0));
        } else if (arc == blueArc) {
            transition.setToValue(Color.rgb(0,0,255));
        }
        transition.setCycleCount(2);
        return transition;
    }

    private void disableAllArcActions(boolean toggle) {
        greenArc.setDisable(toggle);
        redArc.setDisable(toggle);
        yellowArc.setDisable(toggle);
        blueArc.setDisable(toggle);
    }

    private void endGame() {
        disableAllArcActions(true);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Game Alert");
        alert.setHeaderText("Game Over");
        alert.setContentText("Oops that wasn't the right choice, the game is now over.");
        alert.show();
    }
}