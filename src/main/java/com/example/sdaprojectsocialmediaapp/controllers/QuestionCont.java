package com.example.sdaprojectsocialmediaapp.controllers;

import com.example.sdaprojectsocialmediaapp.Router;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;

import java.io.IOException;


public class QuestionCont extends MainController {
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
    void createNewQuestion(MouseEvent event) throws IOException {
        // Routing to the post creation page
        Router.navigateTo("Question Form");
    }

    @FXML
    void submit(MouseEvent event) throws IOException {
        // Create an activity post object

        // Insert post data to database

        // Return back to Activity posts page
        Router.navigateTo("Question Page");
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
