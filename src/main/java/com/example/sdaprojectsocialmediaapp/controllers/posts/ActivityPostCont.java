package com.example.sdaprojectsocialmediaapp.controllers.posts;

import com.example.sdaprojectsocialmediaapp.Router;
import com.example.sdaprojectsocialmediaapp.controllers.MainController;
import com.example.sdaprojectsocialmediaapp.models.engagements.Reply;
import com.example.sdaprojectsocialmediaapp.models.posts.ActivityPost;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ActivityPostCont extends MainController {
    StudentRepository studentRepository = new StudentRepository();
    private int id;

    @FXML
    private Pane pane;

    @FXML
    private Label postType;

    @FXML
    private Label authorName;

    @FXML
    private Label description;


    @FXML
    private HBox replyBox;

    @FXML
    private Label timestamp;

    @FXML
    private Label title;

    @FXML
    private TextArea postContent;

    @FXML
    private TextField postTitle;

    @FXML
    private HBox replyButtonBox;

    @FXML
    private TextField replyContent;

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
    void addReplyButton(MouseEvent event) {
        String content = postContent.getText();
        if (content.length() > 10) {

        } else {

        }
        Button button = new Button(replyContent.getText());
        replyContent.setText("");
        button.getStyleClass().add("postButton");
        replyButtonBox.getChildren().add(button);
        replyContent.setDisable(replyButtonBox.getChildren().size() == 3);
    }

    @FXML
    public void initializePost(ActivityPost activityPost) {
        this.replyBox.getChildren().clear();
        this.id = activityPost.getId();
        this.title.setText(activityPost.getTitle());
        this.description.setText(activityPost.getDescription());
        this.timestamp.setText("Posted On: " + activityPost.getDate());
        this.authorName.setText("By: " + studentRepository.getStudentByID(activityPost.getAuthorId()).getName());
        for (Reply reply: activityPost.getReplies()) {
            Button button = new Button(reply.getText());
            button.getStyleClass().add("postButton");
            button.setOnMouseClicked(event -> {
                System.out.println(button.getText() + " button of Activity Post: " + this.id + " is clicked.");
            });
            replyBox.getChildren().add(button);
        }

        // Fetch data to populate post
        // set replyBox layout-y to 528
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
