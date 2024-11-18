package com.example.sdaprojectsocialmediaapp.controllers.posts;

import com.example.sdaprojectsocialmediaapp.Router;
import com.example.sdaprojectsocialmediaapp.controllers.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ActivityPostCont extends MainController {
    @FXML
    private Pane pane;

    @FXML
    private Label postType;

    @FXML
    private TextArea postContent;

    @FXML
    private TextField postTitle;

    @FXML
    public String getPostType() {
        return postType.getText();
    }

    @FXML
    void handleReply(MouseEvent event) {
        // Will display a text area to get reply
    }

    @FXML
    void createNewPost(MouseEvent event) throws IOException {
        // Routing to the post creation page
        Router.navigateTo("Activity Post Form");
    }

    @FXML
    void submit(MouseEvent event) throws IOException {
        // Create an activity post object

        // Insert post data to database

        // Return back to Activity posts page
        Router.navigateTo("Activity Post Page");
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
