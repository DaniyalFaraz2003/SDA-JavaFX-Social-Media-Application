package com.example.sdaprojectsocialmediaapp.controllers;

import com.example.sdaprojectsocialmediaapp.Router;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ActivityPostCont {
    @FXML
    private Pane pane;

    @FXML
    private Label postType;

    @FXML
    void openActivityPosts(MouseEvent event) throws IOException {
        Router.navigateTo("Activity Post Page");
    }

    @FXML
    void openChatPage(MouseEvent event) throws IOException {
        Router.navigateTo("Chat Page");
    }

    @FXML
    void openDashboard(MouseEvent event) throws IOException {
        Router.navigateTo("Homepage");
    }

    @FXML
    void openFriendsPage(MouseEvent event) throws IOException {
        Router.navigateTo("Friends Page");
    }

    @FXML
    void openProfilePage(MouseEvent event) throws IOException {
        Router.navigateTo("Profile Page");
    }

    @FXML
    void openQuestions(MouseEvent event) throws IOException {
        Router.navigateTo("Question Page");
    }

    @FXML
    void openRequests(MouseEvent event) throws IOException {
        Router.navigateTo("Friend Request Page");
    }

    @FXML
    void openSimplePosts(MouseEvent event) throws IOException {
        Router.navigateTo("Simple Post Page");
    }


    @FXML
    public String getPostType() {
        return postType.getText();
    }

    @FXML
    void handleReply(MouseEvent event) {
        // Will display a text area to get reply
    }

    @FXML
    public void initialize() {
//        pane.setMinHeight(150);
//        pane.setMinWidth(731);
    }
}
