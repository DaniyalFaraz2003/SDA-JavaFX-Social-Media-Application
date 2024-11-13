package com.example.sdaprojectsocialmediaapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ToggleButton;

import java.io.IOException;

public class HomepageCont {
    @FXML
    private Stage stage;

    @FXML
    private ToggleButton activityPostFilterBtn;

    @FXML
    private ToggleButton simplePostFilterBtn;

    @FXML
    private ToggleButton allFilterBtn;

    @FXML
    private VBox container;

    @FXML
    private ToggleButton questionFilterBtn;

    private void displayPosts() throws IOException {
        container.getChildren().clear();
        // Loading Activity Posts
        for (int i = 0; i < 3; i++) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/activity_post.fxml"));
            Pane pane = loader.load();
            ActivityPostCont controller = loader.getController();
            controller.set();
            container.getChildren().add(pane);
        }
        // Loading Simple Posts
        for (int i = 0; i < 3; i++) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/simple_post.fxml"));
            Pane pane = loader.load();
            SimplePostCont controller = loader.getController();
            controller.set("/images/postImage.jpeg");
            container.getChildren().add(pane);
        }
        // Loading Questions
        for (int i = 0; i < 3; i++) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/question.fxml"));
            Pane pane = loader.load();
            QuestionCont controller = loader.getController();
            controller.set();
            container.getChildren().add(pane);
        }
    }

    private void filterPostsBy(String type) {
        container.getChildren().forEach((child) -> {
            Pane postPane = (Pane) child;
            Label postTypeLabel = (Label) postPane.lookup("#postType"); // Find the label by fx:id
            // Check if the postType label exists and its text matches type
            // Hide the post otherwise
            boolean matchesType = postTypeLabel != null && type.equals(postTypeLabel.getText());
            // Show the post if it matches the type
            postPane.setVisible(matchesType);
            postPane.setManaged(matchesType);
        });
    }

    @FXML
    void displayAllPosts(MouseEvent event) throws IOException {
        displayPosts();
    }

    @FXML
    void filterActivityPosts(MouseEvent event) throws IOException {
        if (activityPostFilterBtn.isSelected()) {
            this.simplePostFilterBtn.setSelected(false);
            this.questionFilterBtn.setSelected(false);
            filterPostsBy("Activity Post");
        } else {
            displayPosts(); // Display all posts if the filter is not active
        }
    }

    @FXML
    void filterQuestions(MouseEvent event) throws IOException {
        if (questionFilterBtn.isSelected()) {
            this.simplePostFilterBtn.setSelected(false);
            this.activityPostFilterBtn.setSelected(false);
            filterPostsBy("Question");
        } else {
            displayPosts(); // Display all posts if the filter is not active
        }
    }

    @FXML
    void filterSimplePosts(MouseEvent event) throws IOException {
        if (simplePostFilterBtn.isSelected()) {
            this.questionFilterBtn.setSelected(false);
            this.activityPostFilterBtn.setSelected(false);
            filterPostsBy("Simple Post");
        } else {
            displayPosts(); // Display all posts if the filter is not active
        }
    }

    @FXML
    public void initialize(Stage stage) throws IOException {
        displayPosts();
        this.stage = stage;
    }
}
