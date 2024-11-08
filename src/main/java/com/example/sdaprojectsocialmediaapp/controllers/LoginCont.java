package com.example.sdaprojectsocialmediaapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import com.example.sdaprojectsocialmediaapp.models.Student;
import com.example.sdaprojectsocialmediaapp.models.HomePage;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class LoginCont {

    @FXML
    private PasswordField i_password;

    @FXML
    private TextField i_username;

    @FXML
    private Stage stage;

    @FXML
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void goToRegisterPage(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));
        String styles = Objects.requireNonNull(this.getClass().getResource("/css/register.css")).toExternalForm();
        Scene scene = new Scene((Parent) loader.load());
        scene.getStylesheets().add(styles);
        RegisterCont controller = loader.getController();
        controller.setStage(stage);
        stage.setScene(scene);
    }

    @FXML
    void login(MouseEvent event) {
        String username = i_username.getText();
        String password = i_password.getText();
        // Further session begins
    }

}
