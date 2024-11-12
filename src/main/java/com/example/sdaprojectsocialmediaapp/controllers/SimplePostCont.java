package com.example.sdaprojectsocialmediaapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class SimplePostCont {
    @FXML
    private Pane pane;

    @FXML
    private ToggleButton reactBtn;

    @FXML
    private Label reactionCount;

    @FXML
    private Label postType;

    @FXML
    public String getPostType() {
        return postType.getText();
    }

    @FXML
    void handleReaction(MouseEvent event) {
        int reactions = Integer.parseInt(this.reactionCount.getText());
        if (this.reactBtn.isSelected())
            this.reactionCount.setText(Integer.toString(reactions + 1));
        else
            this.reactionCount.setText(Integer.toString(reactions - 1));
    }

    @FXML
    public void set() {
//        pane.setMinHeight(150);
//        pane.setMinWidth(731);
    }

}
