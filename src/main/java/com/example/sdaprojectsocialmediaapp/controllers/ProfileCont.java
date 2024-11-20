package com.example.sdaprojectsocialmediaapp.controllers;

import com.example.sdaprojectsocialmediaapp.Router;
import com.example.sdaprojectsocialmediaapp.controllers.posts.ActivityPostCont;
import com.example.sdaprojectsocialmediaapp.controllers.posts.QuestionCont;
import com.example.sdaprojectsocialmediaapp.controllers.posts.SimplePostCont;
import com.example.sdaprojectsocialmediaapp.models.Student;
import com.example.sdaprojectsocialmediaapp.models.posts.ActivityPost;
import com.example.sdaprojectsocialmediaapp.models.posts.Question;
import com.example.sdaprojectsocialmediaapp.models.posts.SimplePost;
import com.example.sdaprojectsocialmediaapp.repository.PostRepository;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import com.example.sdaprojectsocialmediaapp.services.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class ProfileCont extends MainController {

    private PostRepository postRepo = new PostRepository();
    private StudentRepository studentRepo = new StudentRepository();

    @FXML
    private VBox container;

    @FXML
    private Label email;

    @FXML
    private Label name;

    @FXML
    private Label password;

    @FXML
    private Label phone;

    @FXML
    private Label username;

    @FXML
    private TextField i_email;

    @FXML
    private TextField i_name;

    @FXML
    private TextField i_password;

    @FXML
    private TextField i_phone;

    @FXML
    private TextField i_username;


    @FXML
    void openPostForm(MouseEvent event) throws IOException {
        Router.navigateTo("Post Update Form");
    }

    @FXML
    void openProfileForm(MouseEvent event) throws IOException {
        Router.navigateTo("Profile Update Form");
    }

    @FXML
    void updateProfile(MouseEvent event) throws IOException {
        // update user's profile in database
        Student newStudent = new Student(i_name.getText(), i_username.getText(), i_password.getText(), i_email.getText(), i_phone.getText());
        newStudent.setId(Session.getSessionVariable().getId());
        Session.maintainSession(newStudent);
        studentRepo.updateStudent(newStudent);
        Router.navigateTo("Profile Page");
    }

    @FXML
    public void initializePage() throws IOException {
        // populating profile data
        Student student = Session.getSessionVariable();
        name.setText(student.getName());
        phone.setText(student.getPhone());
        email.setText(student.getEmail());
        username.setText(student.getUserName());
        password.setText(student.getPassword());

//        // populating the posts
//        container.getChildren().clear();
//        ArrayList<SimplePost> simplePosts = postRepo.getAllSimplePosts();
//        ArrayList<ActivityPost> activityPosts = postRepo.getAllActivityPosts();
//        ArrayList<Question> questions = postRepo.getAllQuestions();
//        // Loading Activity Posts
//        for (ActivityPost activityPost : activityPosts) {
//            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/activity_post/activity_post.fxml"));
//            Pane pane = loader.load();
//            ActivityPostCont controller = loader.getController();
//            controller.initializePost(activityPost);
//            container.getChildren().add(pane);
//        }
//        // Loading Simple Posts
//        for (SimplePost simplePost : simplePosts) {
//            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/simple_post/simple_post.fxml"));
//            Pane pane = loader.load();
//            SimplePostCont controller = loader.getController();
//            controller.initializePost(simplePost);
//            container.getChildren().add(pane);
//        }
//        // Loading Questions
//        for (int i = 0; i < 3; i++) {
//            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/question/question.fxml"));
//            Pane pane = loader.load();
//            QuestionCont controller = loader.getController();
//            controller.initializePost();
//            container.getChildren().add(pane);
//        }
    }

    @FXML
    public void initializeForm() {
        Student student = Session.getSessionVariable();
        i_name.setText(student.getName());
        i_phone.setText(student.getPhone());
        i_email.setText(student.getEmail());
        i_username.setText(student.getUserName());
        i_password.setText(student.getPassword());
    }

}
