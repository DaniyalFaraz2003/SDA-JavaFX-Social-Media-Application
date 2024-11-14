package com.example.sdaprojectsocialmediaapp.controllers;

import com.example.sdaprojectsocialmediaapp.Router;
import com.example.sdaprojectsocialmediaapp.utils.Validate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import com.example.sdaprojectsocialmediaapp.models.Student;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Objects;

public class RegisterCont extends MainController {

    @FXML
    private Label emailErr;

    @FXML
    private TextField i_email;

    @FXML
    private TextField i_name;

    @FXML
    private PasswordField i_password;

    @FXML
    private TextField i_phone;

    @FXML
    private TextField i_username;

    @FXML
    private Label nameErr;

    @FXML
    private Label passErr;

    @FXML
    private Label phoneErr;

    @FXML
    private Label usernameErr;

    @FXML
    private Stage stage;

    @FXML
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void goToLoginPage(MouseEvent event) throws IOException { Router.navigateTo("Login"); }

    @FXML
    void register(MouseEvent event) throws IOException {
        nameErr.setText("");
        emailErr.setText("");
        phoneErr.setText("");
        usernameErr.setText("");
        passErr.setText("");
        Student student = new Student(i_name.getText().trim(), i_username.getText().trim(), i_password.getText().trim(), i_email.getText().trim(), i_phone.getText().trim());
        if (Validate.isValidName(student.getName())) {
            if (Validate.isValidEmail(student.getEmail())) {
                if (Validate.isValidPhone(student.getPhone())) {
                    if (Validate.isValidUsername(student.getUserName())) {
                        if (!student.getPassword().isEmpty()) {
                            Router.navigateTo("Login");
                        }
                        else {
                            passErr.setText("Please enter a valid password");
                        }
                    }
                    else {
                        usernameErr.setText("Please enter a valid roll number");
                    }
                }
                else {
                    phoneErr.setText("Please enter a valid phone number");
                }
            }
            else {
                emailErr.setText("Please enter a valid email");
            }
        }
        else {
            nameErr.setText("Please enter a valid name");
        }

    }

}
