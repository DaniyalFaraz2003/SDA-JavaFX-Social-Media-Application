package com.example.sdaprojectsocialmediaapp.controllers;

import com.example.sdaprojectsocialmediaapp.Router;
import com.example.sdaprojectsocialmediaapp.models.Student;
import com.example.sdaprojectsocialmediaapp.models.notification.Friend;
import com.example.sdaprojectsocialmediaapp.repository.FriendRequestRepository;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import com.example.sdaprojectsocialmediaapp.services.Session;
import com.example.sdaprojectsocialmediaapp.utils.Validate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class  LoginCont {

    StudentRepository studentRepository = new StudentRepository();
    FriendRequestRepository friendRequestRepository = new FriendRequestRepository();

    @FXML
    private PasswordField i_password;

    @FXML
    private TextField i_username;

    @FXML
    private Label err;

    @FXML
    private Stage stage;

    @FXML
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void goToRegisterPage(MouseEvent event) throws IOException {
        Router.navigateTo("Register");
    }

    @FXML
    void login(MouseEvent event) throws IOException {
        String username = i_username.getText().trim();
        String password = i_password.getText().trim();
        if(Validate.isValidUsername(username)) {
            if(studentRepository.checkStudentExists(username, password)) {
                // Further session begins
                Student student = studentRepository.getStudentbyUsername(username);
                ArrayList<Integer> friends = friendRequestRepository.getAllFriends(student.getId());
                for (int i = 0; i < friends.size(); i++) {
                    Friend friend = new Friend(friends.get(i));
                    student.addObserver(friend);
                }
                Session.maintainSession(student);
                Router.navigateTo("Homepage");
            }
            else{
                err.setText("Invalid Username or Password");
            }
        }
        else{
            err.setText("Invalid Username or Password");
        }

    }

}
