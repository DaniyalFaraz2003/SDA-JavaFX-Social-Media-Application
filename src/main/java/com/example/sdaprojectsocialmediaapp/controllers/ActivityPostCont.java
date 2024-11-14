package com.example.sdaprojectsocialmediaapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;

public class ActivityPostCont extends MainController {
    @FXML
    private Pane pane;

    @FXML
    private Label postType;

    @FXML
    public String getPostType() {
        return postType.getText();
    }

    @FXML
    void handleReply(MouseEvent event) {
        // Will display a text area to get reply
    }

    @FXML
    public void initializePost() {
        // Fetch data to populate post
    }

    @FXML
    public void initializePage() {
        // Fetch data to populate page
    }

    @FXML
    public void initializeForm() {
        // Fetch data to populate form
    }
}
