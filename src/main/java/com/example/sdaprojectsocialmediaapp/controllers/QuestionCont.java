package com.example.sdaprojectsocialmediaapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;


public class QuestionCont {
    @FXML
    private Pane pane;

    @FXML
    private Label postType;

    @FXML
    private Label votes;

    @FXML
    private ToggleButton voteButton;

    @FXML
    public String getPostType() {
        return postType.getText();
    }

    @FXML
    void handleUpVote(MouseEvent event) {
        int votes = Integer.parseInt(this.votes.getText());
        if (voteButton.isSelected()) {
            this.votes.setText(Integer.toString(votes + 1));
        }
        else
            this.votes.setText(Integer.toString(votes - 1));
    }

    @FXML
    void handleAnswer(MouseEvent event) {
        // Will show a text area to get answer
    }

    @FXML
    public void set() {
//        pane.setMinHeight(150);
//        pane.setMinWidth(731);
    }
}
