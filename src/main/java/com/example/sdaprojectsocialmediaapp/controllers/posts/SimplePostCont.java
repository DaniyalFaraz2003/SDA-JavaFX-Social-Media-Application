package com.example.sdaprojectsocialmediaapp.controllers.posts;

import com.example.sdaprojectsocialmediaapp.Router;
import com.example.sdaprojectsocialmediaapp.controllers.MainController;
import com.example.sdaprojectsocialmediaapp.controllers.engagements.CommentCont;
import com.example.sdaprojectsocialmediaapp.models.engagements.Comment;
import com.example.sdaprojectsocialmediaapp.models.posts.SimplePost;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import com.example.sdaprojectsocialmediaapp.services.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;
import java.nio.file.Files;

public class SimplePostCont extends MainController {
    private StudentRepository studentRepository = new StudentRepository();

    private int id;

    @FXML
    private Pane pane;

    @FXML
    private ToggleButton reactBtn;

    @FXML
    private Label reactionCount;

    @FXML
    private Label postType;

    @FXML
    private TextField postTitle;

    @FXML
    private Label postHeading;

    @FXML
    private TextField commentBox;

    @FXML
    private VBox comments;

    @FXML
    private TextArea postContent;

    @FXML
    private Label postDescription;

    @FXML
    private Label timestamp;

    @FXML
    private Label author;

    @FXML
    private ImageView postImage;

    @FXML
    private Button uploadBtn;

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
    void handleComment(MouseEvent event) throws IOException {
        String commentString = commentBox.getText();
        commentBox.setText("");
        Comment comment = new Comment(0, Session.getSessionVariable().getId(), new Timestamp(System.currentTimeMillis()), commentString, 10);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/simple_post/comment.fxml"));
        Pane commentPane = loader.load();
        CommentCont controller = loader.getController();
        controller.initializeComment(comment);
        comments.getChildren().add(commentPane);
    }

    @FXML
    void handleAnswer(MouseEvent event) {

    }

    @FXML
    void createNewPost(MouseEvent event) throws IOException {
        // Routing to the post creation page
        Router.navigateTo("Simple Post Form");
    }

    @FXML
    void uploadImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an Image");

        // Filter only image file types (e.g., .png, .jpg, .jpeg)
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        // Show open file dialog
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                // Define the target directory (post_images in resources)
                String targetDirPath = "src/main/resources/post_images";
                File targetDir = new File(targetDirPath);
                if (!targetDir.exists()) {
                    targetDir.mkdirs(); // Create the directory if it doesn't exist
                }

                // Find the next available file name (1.jpeg, 2.jpeg, etc.)
                int fileIndex = 1;
                File targetFile;
                do {
                    targetFile = new File(targetDir, fileIndex + ".jpeg");
                    fileIndex++;
                } while (targetFile.exists());

                // Copy the file to the target directory with the new name
                Files.copy(file.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                System.out.println("File saved as: " + targetFile.getAbsolutePath());
                uploadBtn.setDisable(true);
            } catch (IOException e) {
                System.out.println("Error saving the file: " + e.getMessage());
            }
        }
    }

    @FXML
    void submit(MouseEvent event) throws IOException {
        // Create an activity post object

        // Insert post data to database

        // Return back to Activity posts page
        Router.navigateTo("Simple Post Page");
    }

    @FXML
    public void initializePost(SimplePost simplePost) {
        this.id = simplePost.getId();
        this.postHeading.setText(simplePost.getTitle());
        this.postDescription.setText(simplePost.getDescription());
        String resolvedPath = Objects.requireNonNull(getClass().getResource(simplePost.getPostImageUrl())).toExternalForm();
        Image image = new Image(resolvedPath);
        postImage.setImage(image);
        this.timestamp.setText("Posted On: " + simplePost.getDate());
        this.author.setText("By: " + studentRepository.getStudentByID(simplePost.getAuthorId()).getName());
        this.reactionCount.setText(Integer.toString(simplePost.getNumberOfLikes()));


        try {
            for (Comment comment : simplePost.getComments()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/simple_post/comment.fxml"));
                Pane commentPane = loader.load();
                CommentCont controller = loader.getController();
                controller.initializeComment(comment);
                this.comments.getChildren().add(commentPane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
