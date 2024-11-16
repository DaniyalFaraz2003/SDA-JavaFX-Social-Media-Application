package com.example.sdaprojectsocialmediaapp.controllers.friends;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class FriendCompCont {

    @FXML
    private Pane pane;

    @FXML
    private Label senderName;

    @FXML
    void removeFriend(MouseEvent event) {
        // removes friend from database
    }

    @FXML
    void viewProfile(MouseEvent event) {
        // opens profile page of friend
    }

    @FXML
    public void initializeComp(String name) {
        senderName.setText(name);
    }

}
