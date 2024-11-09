package com.example.sdaprojectsocialmediaapp.models;

import com.example.sdaprojectsocialmediaapp.controllers.LoginCont;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginApp extends Application {
    private final String styles;
    private final Image logo;
    private final FXMLLoader loader;
    private LoginCont controller;

    public LoginApp() throws IOException {
        this.styles = Objects.requireNonNull(this.getClass().getResource("/css/register.css")).toExternalForm();
        logo = new Image(Objects.requireNonNull(this.getClass().getResource("/images/logo.png")).toExternalForm());
        this.loader = new FXMLLoader(this.getClass().getResource("/fxml/login.fxml"));
        controller = null;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(styles);
        stage.setTitle("FASTBook Social Media Application");
        stage.getIcons().add(logo);
        stage.setScene(scene);
        controller.setStage(stage);
        stage.show();
    }
}
