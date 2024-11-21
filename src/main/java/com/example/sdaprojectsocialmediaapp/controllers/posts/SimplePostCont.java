package com.example.sdaprojectsocialmediaapp.controllers.posts;

import com.example.sdaprojectsocialmediaapp.Router;
import com.example.sdaprojectsocialmediaapp.controllers.MainController;
import com.example.sdaprojectsocialmediaapp.controllers.engagements.CommentCont;
import com.example.sdaprojectsocialmediaapp.models.engagements.Comment;
import com.example.sdaprojectsocialmediaapp.models.posts.SimplePost;
import com.example.sdaprojectsocialmediaapp.repository.PostRepository;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import com.example.sdaprojectsocialmediaapp.services.Session;
import com.example.sdaprojectsocialmediaapp.utils.Validate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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


import javax.imageio.ImageIO;

public class SimplePostCont extends MainController {
    private StudentRepository studentRepository = new StudentRepository();
    private PostRepository postRepository = new PostRepository();

    File imageFile = null;
    private int id;

    @FXML
    private ImageView imageView;

    @FXML
    private Pane pane;

    @FXML
    private Label errorText;

    @FXML
    private ToggleButton reactBtn;

    @FXML
    private Label reactionCount;

    @FXML
    private Label postType;

    @FXML
    private TextField postTitle;

    @FXML
    private TextArea postContent;

    @FXML
    private Label postHeading;

    @FXML
    private TextField commentBox;

    @FXML
    private VBox comments;

    @FXML
    private VBox container;

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
    private Button updateBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button submit;

    @FXML
    private Label warning;

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
        this.imageFile = file;
        if (file != null) {
            try {
                // Load the image from the file
                Image image = new Image(file.toURI().toString());
                // Set the image in the ImageView
                imageView.setImage(image);

                // Optionally disable the upload button after the image is uploaded
                uploadBtn.setDisable(true);
            } catch (Exception e) {
                errorText.setText("Error loading the image");
            }
        }
    }

    @FXML
    void submit(MouseEvent event) throws IOException {

        if (Validate.isValidPostTitle(this.postTitle.getText())) {
            if (Validate.isValidPostDescription(this.postContent.getText())) {
                if (this.imageFile != null) {
                    try {
                        // Define the target directory (post_images in resources)
                        String targetDirPath = "src/main/resources/post_images";
                        File targetDir = new File(targetDirPath);
                        if (!targetDir.exists()) {
                            targetDir.mkdirs(); // Create the directory if it doesn't exist
                        }

                        //int studentID, String title, String description, String url, int numLikes
                        int newId = postRepository.createSimplePost(Session.getSessionVariable().getId(), this.postTitle.getText(), this.postContent.getText(), "", 0);
                        SimplePost sp = postRepository.getSimplePost(newId);


                        // Find the next available file name (1.jpeg, 2.jpeg, etc.)
                        File targetFile = new File(targetDir, newId + ".jpeg");
                        Files.copy(this.imageFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                        // Verify the file exists

                        if (targetFile.exists()) {
                            // Update the post with the correct image URL
                            sp.setPostImageUrl("/post_images/" + newId + ".jpeg");
                            postRepository.updateSimplePost(sp);

                            // Navigate to the Simple Post Page
                            System.out.println("File saved as: " + targetFile.getAbsolutePath());
                            Router.navigateTo("Simple Post Page");
                        } else {
                            errorText.setText("Image file not saved properly. Please try again.");
                        }

                    } catch (IOException e) {
                        System.out.println("Error saving the file: " + e.getMessage());
                    }
                }
                else {
                    errorText.setText("Please Choose Image File");
                }
            } else {
                errorText.setText("Invalid post description");
            }
        } else {
            errorText.setText("Please enter valid post title");
        }

    }

    @FXML
    public void initializePost(SimplePost simplePost, boolean isHomepage, Stage stage) {
        this.id = simplePost.getId();
        this.postHeading.setText(simplePost.getTitle());
        this.postDescription.setText(simplePost.getDescription());
        String resolvedPath = System.getProperty("user.dir") + "\\src\\main\\resources".replace("\\", "/") + simplePost.getPostImageUrl();

        File file = new File(resolvedPath);

        if (file.exists()) {
            String fileURI = file.toURI().toString(); // Convert absolute path to a URI
            Image image = new Image(fileURI); // Load the image using its URI
            this.postImage.setImage(image); // Set the image in the ImageView
        } else {
            System.out.println("Error: File does not exist at " + resolvedPath);
            return;
        }

        this.timestamp.setText("Posted On: " + simplePost.getDate());
        this.author.setText("By: " + studentRepository.getStudentByID(simplePost.getAuthorId()).getName());
        this.reactionCount.setText(Integer.toString(simplePost.getNumberOfLikes()));
        if (Session.getSessionVariable().getId() != simplePost.getAuthorId() || isHomepage) {
            this.updateBtn.setVisible(false);
            this.deleteBtn.setVisible(false);
        } else {
            this.updateBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/simple_post/simple_post_form.fxml"));
                try {
                    Parent root = loader.load();
                    SimplePostCont controller = loader.getController();
                    controller.initializeForm(simplePost);
                    stage.setScene(new Scene(root));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            this.deleteBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                PostRepository postRepository = new PostRepository();
                postRepository.deleteSimplePost(id);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/simple_post/simple_post_page.fxml"));
                try {
                    Parent root = loader.load();
                    SimplePostCont controller = loader.getController();
                    controller.initializePage(stage);
                    stage.setScene(new Scene(root));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
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
    public void initializePage(Stage stage) throws IOException {
        PostRepository postRepository = new PostRepository();
        ArrayList<SimplePost> simplePosts = postRepository.getSimplePostByStudentID(Session.getSessionVariable().getId());


        if (simplePosts == null)
            warning.setVisible(true);
        else if (this.container != null) {
            container.getChildren().clear();
            for (SimplePost simplePost : simplePosts) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/simple_post/simple_post.fxml"));
                pane = loader.load();
                SimplePostCont controller = loader.getController();
                controller.initializePost(simplePost, false, stage);
                container.getChildren().add(pane);
            }
        }
    }

    @FXML
    public void initializeForm(SimplePost simplePost) {
        if (simplePost != null) {
            this.submit.setText("Update");
            this.postTitle.setText(simplePost.getTitle());
            this.postContent.setText(simplePost.getDescription());
            this.uploadBtn.setDisable(true);
            submit.removeEventHandler(MouseEvent.MOUSE_CLICKED, submit.getOnMouseClicked());
            submit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                simplePost.setTitle(postTitle.getText());
                simplePost.setDescription(postContent.getText());
                simplePost.setDate(new Timestamp(System.currentTimeMillis()));
                PostRepository postRepository = new PostRepository();
                postRepository.updateSimplePost(simplePost);
                try {
                    Router.navigateTo("Simple Post Page");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
