package com.example.sdaprojectsocialmediaapp.controllers;

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
    private TextArea i_address;

    @FXML
    private TextField i_email;

    @FXML
    private TextField i_firstName;

    @FXML
    private TextField i_lastName;

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

    void openLoginPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        String styles = Objects.requireNonNull(this.getClass().getResource("/css/register.css")).toExternalForm();
        Scene scene = new Scene((Parent) loader.load());
        scene.getStylesheets().add(styles);
        LoginCont controller = loader.getController();
        controller.setStage(stage);
        stage.setScene(scene);
    }

    @FXML
    void goToLoginPage(MouseEvent event) throws IOException { openLoginPage(); }

    @FXML
    void register(MouseEvent event) throws IOException {
        Student student = new Student(i_firstName.getText(), i_lastName.getText(), i_username.getText(), i_password.getText(), i_email.getText(), i_phone.getText());
        System.out.println(student.toString());
        openLoginPage();
    }

}
