package com.example.sdaprojectsocialmediaapp.controllers.posts;

import com.example.sdaprojectsocialmediaapp.Router;
import com.example.sdaprojectsocialmediaapp.controllers.HomepageCont;
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
import com.example.sdaprojectsocialmediaapp.utils.Validate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ActivityPostCont extends MainController {
    StudentRepository studentRepository = new StudentRepository();
    PostRepository postRepository = new PostRepository();

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
    private Button submit;

    @FXML
    private Button addBtn;

    @FXML
    private Label pageWarning;

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

        if (Validate.isValidPostTitle(this.postTitle.getText())) {
            if (Validate.isValidPostDescription(this.postContent.getText())) {
                if (!this.replyButtonBox.getChildren().isEmpty()) {

                    ArrayList<Reply> replies = new ArrayList<>();
                    for (int i = 0; i < this.replyButtonBox.getChildren().size(); i++) {
                        Node node = (Node) this.replyButtonBox.getChildren().get(i);
                        if (node instanceof Button) {
                            Reply reply = new Reply(0, 0, ((Button) node).getText());
                            replies.add(reply);
                        }
                    }

                    postRepository.createActivityPost(Session.getSessionVariable().getId(), this.postTitle.getText().trim(), this.postContent.getText().trim(), replies);

                    Router.navigateTo("Activity Post Page");
                } else {
                    this.warning.setText("Enter at least 1 reply");
                }
            } else {
                this.warning.setText("Invalid Post Description");
            }
        } else {
            this.warning.setText("Invalid Post Title");
        }

    }

    @FXML
    void addReplyButton(MouseEvent event) {
        String content = replyContent.getText();
        if (content.length() > 10) {
            this.warning.setText("Not more than 10 characters");
        }
        else if (content.isEmpty()) {
            this.warning.setText("Enter at least 1 character");
        }
        else {
            this.warning.setText("");
            Button button = new Button(replyContent.getText());
            replyContent.setText("");
            button.getStyleClass().add("postButton");
            replyButtonBox.getChildren().add(button);
        }
        replyContent.setDisable(replyButtonBox.getChildren().size() == 3);
    }

    @FXML
    public void initializePost(ActivityPost activityPost, boolean isHomepage, Stage stage) {
        this.replyBox.getChildren().clear();
        this.id = activityPost.getId();
        this.title.setText(activityPost.getTitle());
        this.description.setText(activityPost.getDescription());
        this.timestamp.setText("Posted On: " + activityPost.getDate());
        this.authorName.setText("By: " + studentRepository.getStudentByID(activityPost.getAuthorId()).getName());
        for (Reply reply : activityPost.getReplies()) {
            Button button = new Button(reply.getText());
            button.getStyleClass().add("postButton");
            if (!postRepository.checkUniqueStudentReply(activityPost.getId(), Session.getSessionVariable().getId())) {
                button.setDisable(true);
            }
            button.setOnMouseClicked(event -> {
                System.out.println(button.getText() + " button of Activity Post: " + this.id + " is clicked.");

                if (postRepository.checkUniqueStudentReply(activityPost.getId(), Session.getSessionVariable().getId())) {
                    postRepository.addStudentActivityReply(reply.getId(), activityPost.getId(), Session.getSessionVariable().getId());
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

                    for (int i = 0; i < replyBox.getChildren().size(); i++) {
                        Node node = (Node) replyBox.getChildren().get(i);
                        if (node instanceof Button) {
                            node.setDisable(true);
                        }
                    }
                }



            });
            replyBox.getChildren().add(button);
        }
        if (Session.getSessionVariable().getId() != activityPost.getAuthorId() || isHomepage) {
            this.updateBtn.setVisible(false);
            this.deleteBtn.setVisible(false);
        } else {
            this.updateBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/activity_post/activity_post_form.fxml"));
                try {
                    Parent root = loader.load();
                    ActivityPostCont controller = loader.getController();
                    controller.initializeForm(activityPost);
                    stage.setScene(new Scene(root));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            this.deleteBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                PostRepository postRepository = new PostRepository();
                postRepository.deleteActivityPost(id);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/activity_post/activity_post_page.fxml"));
                try {
                    Parent root = loader.load();
                    ActivityPostCont controller = loader.getController();
                    controller.initializePage(stage);
                    stage.setScene(new Scene(root));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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
    public void initializePage(Stage stage) throws IOException {
        PostRepository postRepository = new PostRepository();
        ArrayList<ActivityPost> activityPosts = postRepository.getActivityPostByStudentID(Session.getSessionVariable().getId());
        if (activityPosts == null)
            pageWarning.setVisible(true);
        else if (container != null) {
            container.getChildren().clear();
            for (ActivityPost activityPost : activityPosts) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/activity_post/activity_post.fxml"));
                pane = loader.load();
                ActivityPostCont controller = loader.getController();
                controller.initializePost(activityPost, false, stage);
                container.getChildren().add(pane);
            }
        }
    }

    @FXML
    public void initializeForm(ActivityPost activityPost) {
        if (activityPost != null) {
            this.submit.setText("Update");
            this.postTitle.setText(activityPost.getTitle());
            this.postContent.setText(activityPost.getDescription());
            this.replyContent.setDisable(true);
            this.addBtn.setDisable(true);
            this.replyButtonBox.getChildren().clear();
            for (Reply reply : activityPost.getReplies()) {
                Button button = new Button(reply.getText());
                button.getStyleClass().add("postButton");
                button.setDisable(true);
                replyButtonBox.getChildren().add(button);
            }
            submit.removeEventHandler(MouseEvent.MOUSE_CLICKED, submit.getOnMouseClicked());
            submit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                activityPost.setTitle(postTitle.getText());
                activityPost.setDescription(postContent.getText());
                activityPost.setDate(new Timestamp(System.currentTimeMillis()));
                PostRepository postRepository = new PostRepository();
                postRepository.updateActivityPost(activityPost);
                try {
                    Router.navigateTo("Activity Post Page");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
