package com.example.sdaprojectsocialmediaapp.controllers;

import com.example.sdaprojectsocialmediaapp.Router;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class SimplePostCont extends MainController {
    @FXML
    private Pane pane;

    @FXML
    private ToggleButton reactBtn;

    @FXML
    private Label reactionCount;

    @FXML
    private Label postType;

    @FXML
    private ImageView postImage;

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
    void handleAnswer(MouseEvent event) {
        // Will Handle Answers
    }

    @FXML
    void createNewPost(MouseEvent event) throws IOException {
        // Routing to the post creation page
        Router.navigateTo("Simple Post Form");
    }

    @FXML
    void submit(MouseEvent event) throws IOException {
        // Create an activity post object

        // Insert post data to database

        // Return back to Activity posts page
        Router.navigateTo("Simple Post Page");
    }

    @FXML
    public void initializePost(String imageUrl) {
        String resolvedPath = Objects.requireNonNull(getClass().getResource(imageUrl)).toExternalForm();
        Image image = new Image(resolvedPath);
        postImage.setImage(image);
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
