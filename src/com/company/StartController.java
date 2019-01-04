package com.company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    private @FXML TextField usernameField;
    private @FXML Button startButton;
    private @FXML Button helpButton;
    private String username;

    @FXML
    private void handleButtons(ActionEvent event) throws IOException {
        if (event.getSource() == startButton) {
            if (usernameField.getText().length() == 0) {
                Alert alert =  new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Game Alert");
                alert.setHeaderText("USERNAME REQUIRED");
                alert.setContentText("Please provide a valid username...");
                alert.show();
            } else {
                username = usernameField.getText();
                Parent newRoot = FXMLLoader.load(getClass().getResource("GameUI.fxml"));
                Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                currentStage.setScene(new Scene(newRoot, 500, 500));
            }
        } else if (event.getSource() == helpButton) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Alert");
            alert.setHeaderText("How to play Simon Says");
            alert.setContentText("Click the start button in order to start the game." +
                    " Certain pieces will light up, you must click in the sequence that they lit up." +
                    " This will continue until a mistake is made. All high scores can be viewed afterwards.");
            alert.show();
        }
    }

    public String getUsername() { return this.username; }
}
