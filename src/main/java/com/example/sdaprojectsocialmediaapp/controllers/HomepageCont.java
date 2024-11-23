package com.example.sdaprojectsocialmediaapp.controllers;

import com.example.sdaprojectsocialmediaapp.controllers.notification.NotificationCont;
import com.example.sdaprojectsocialmediaapp.controllers.posts.ActivityPostCont;
import com.example.sdaprojectsocialmediaapp.controllers.posts.QuestionCont;
import com.example.sdaprojectsocialmediaapp.controllers.posts.SimplePostCont;
import com.example.sdaprojectsocialmediaapp.models.notification.Notification;
import com.example.sdaprojectsocialmediaapp.models.posts.ActivityPost;
import com.example.sdaprojectsocialmediaapp.models.posts.Question;
import com.example.sdaprojectsocialmediaapp.models.posts.SimplePost;
import com.example.sdaprojectsocialmediaapp.repository.NotificationRepository;
import com.example.sdaprojectsocialmediaapp.repository.PostRepository;
import com.example.sdaprojectsocialmediaapp.services.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ToggleButton;

import java.io.IOException;
import java.util.ArrayList;

public class HomepageCont extends MainController {
    private PostRepository postRepo = new PostRepository();
    private NotificationRepository notificationRepository  = new NotificationRepository();


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
    private VBox notificationBox;

    @FXML
    private ToggleButton questionFilterBtn;

    private void displayNotifications() throws IOException {
        notificationBox.getChildren().clear();
        ArrayList<Notification> notifications = notificationRepository.getNotifications(Session.getSessionVariable().getId());
        if (notifications.isEmpty()) {
            Label label = new Label("No notifications found");
            notificationBox.getChildren().add(label);
        } else {
            for (Notification notification : notifications) {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/notification/notification.fxml"));
                Pane pane = loader.load();
                NotificationCont controller = loader.getController();
                controller.initializeNotification(notification);
                notificationBox.getChildren().add(pane);
            }
        }

    }

    @FXML
    void clearNotification(MouseEvent event) {

    }

    private void displayPosts() throws IOException {
        container.getChildren().clear();
        ArrayList<SimplePost> simplePosts = postRepo.getAllSimplePosts();
        ArrayList<ActivityPost> activityPosts = postRepo.getAllActivityPosts();
        ArrayList<Question> questions = postRepo.getAllQuestions();
        // Loading Activity Posts
        for (ActivityPost activityPost : activityPosts) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/activity_post/activity_post.fxml"));
            Pane pane = loader.load();
            ActivityPostCont controller = loader.getController();
            controller.initializePost(activityPost, true, stage);
            container.getChildren().add(pane);
        }
        // Loading Simple Posts
        for (SimplePost simplePost : simplePosts) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/simple_post/simple_post.fxml"));
            Pane pane = loader.load();
            SimplePostCont controller = loader.getController();
            controller.initializePost(simplePost, true, stage);
            container.getChildren().add(pane);
        }
        // Loading Questions
        for (Question question : questions) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/question/question.fxml"));
            Pane pane = loader.load();
            QuestionCont controller = loader.getController();
            controller.initializePost(question, true, stage);
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
        displayNotifications();
        this.stage = stage;
    }
}
