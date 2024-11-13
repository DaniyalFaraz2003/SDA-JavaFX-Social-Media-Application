package com.example.sdaprojectsocialmediaapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.Objects;

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
    public void set(String imageUrl) {
        String resolvedPath = Objects.requireNonNull(getClass().getResource(imageUrl)).toExternalForm();
        Image image = new Image(resolvedPath);
        postImage.setImage(image);
    }

}
