package com.example.sdaprojectsocialmediaapp.controllers;

import com.example.sdaprojectsocialmediaapp.Router;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ProfileCont extends MainController {

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
        Router.navigateTo("Profile Page");
    }

    @FXML
    public void initializePage() {
        // Initializes labels and post VBox
    }

    @FXML
    public void initializeForm() {
        // Initializes text fields using session object
        i_name.setText("Hammad Ali");
        i_phone.setText("03331234567");
        i_email.setText("i220914@edu.pk");
        i_username.setText("Unsatisfying Prince");
        i_password.setText("pass2");
    }

}
