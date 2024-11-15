package com.example.sdaprojectsocialmediaapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class FriendRequestCont extends MainController {

    @FXML
    private VBox container;

    @FXML
    private Label requestCount;

    @FXML
    public void initializePage() {
        // get friend requests from database and populate the page
    }
}
