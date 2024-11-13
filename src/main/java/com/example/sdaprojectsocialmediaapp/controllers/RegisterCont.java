package com.example.sdaprojectsocialmediaapp.controllers;

import com.example.sdaprojectsocialmediaapp.Router;
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

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class RegisterCont {

    @FXML
    private TextField i_name;

    @FXML
    private TextField i_email;

    @FXML
    private PasswordField i_password;

    @FXML
    private TextField i_phone;

    @FXML
    private TextField i_username;

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
        Student student = new Student(i_name.getText(), i_username.getText(), i_password.getText(), i_email.getText(), i_phone.getText());
        System.out.println(student.toString());
        Router.navigateTo("Login");
    }

}
