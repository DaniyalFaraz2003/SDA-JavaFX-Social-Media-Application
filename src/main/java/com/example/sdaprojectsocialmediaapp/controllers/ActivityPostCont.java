package com.example.sdaprojectsocialmediaapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;

public class ActivityPostCont {
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
    public void set() {
//        pane.setMinHeight(150);
//        pane.setMinWidth(731);
    }
}
