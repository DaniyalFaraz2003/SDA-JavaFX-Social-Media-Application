package com.example.sdaprojectsocialmediaapp.controllers.posts;

import com.example.sdaprojectsocialmediaapp.Router;
import com.example.sdaprojectsocialmediaapp.controllers.MainController;
import com.example.sdaprojectsocialmediaapp.controllers.engagements.CommentCont;
import com.example.sdaprojectsocialmediaapp.controllers.engagements.ReplyCont;
import com.example.sdaprojectsocialmediaapp.models.engagements.Reply;
import com.example.sdaprojectsocialmediaapp.models.engagements.StudentActivityReply;
import com.example.sdaprojectsocialmediaapp.models.posts.ActivityPost;
import com.example.sdaprojectsocialmediaapp.models.posts.SimplePost;
import com.example.sdaprojectsocialmediaapp.repository.PostRepository;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import com.example.sdaprojectsocialmediaapp.services.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

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
    private Label warning;

    @FXML
    private VBox replies;

    @FXML
    private VBox container;

    @FXML
    private Button updateBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    public String getPostType() {
        return postType.getText();
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
        String content = replyContent.getText();
        if (content.length() > 10) {
            this.warning.setText("Not more than 10 characters");
        } else {
            this.warning.setText("");
            Button button = new Button(replyContent.getText());
            replyContent.setText("");
            button.getStyleClass().add("postButton");
            replyButtonBox.getChildren().add(button);
        }
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
        for (Reply reply : activityPost.getReplies()) {
            Button button = new Button(reply.getText());
            button.getStyleClass().add("postButton");
            button.setOnMouseClicked(event -> {
                System.out.println(button.getText() + " button of Activity Post: " + this.id + " is clicked.");
                StudentActivityReply sar = new StudentActivityReply(Session.getSessionVariable().getId(), reply.getId(), this.id, new Timestamp(System.currentTimeMillis()));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/activity_post/reply.fxml"));
                try {
                    Pane pane = loader.load();
                    ReplyCont controller = loader.getController();
                    controller.initializeReply(sar);
                    replies.getChildren().add(pane);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            replyBox.getChildren().add(button);
        }
        if (Session.getSessionVariable().getId() != activityPost.getAuthorId()) {
            this.updateBtn.setVisible(false);
            this.deleteBtn.setVisible(false);
        } else {
            this.updateBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                System.out.println("Update Btn clicked");
            });
            this.deleteBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                System.out.println("Delete Btn clicked");
            });
        }
        try {
            for (StudentActivityReply sar: activityPost.getStudentReplies()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/activity_post/reply.fxml"));
                Pane commentPane = loader.load();
                ReplyCont controller = loader.getController();
                controller.initializeReply(sar);
                this.replies.getChildren().add(commentPane);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Fetch data to populate post
        // set replyBox layout-y to 528
    }

    @FXML
    public void initializePage() throws IOException {
        container.getChildren().clear();
        PostRepository postRepository = new PostRepository();
        ArrayList<ActivityPost> activityPosts = postRepository.getActivityPostByStudentID(Session.getSessionVariable().getId());
        for (ActivityPost activityPost : activityPosts) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/activity_post/activity_post.fxml"));
            pane = loader.load();
            ActivityPostCont controller = loader.getController();
            controller.initializePost(activityPost);
            container.getChildren().add(pane);
        }
    }

    @FXML
    public void initializeForm() {
        // Fetch data to populate form
    }
}
