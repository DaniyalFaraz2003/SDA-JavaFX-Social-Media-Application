package com.example.sdaprojectsocialmediaapp.models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class RegisterApp extends Application {
    private final String styles;
    private final Image logo;

    public RegisterApp() {
        this.styles = Objects.requireNonNull(this.getClass().getResource("/css/register.css")).toExternalForm();
        logo = new Image(Objects.requireNonNull(this.getClass().getResource("/images/logo.png")).toExternalForm());
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = new FXMLLoader(getClass().getResource("/fxml/register.fxml")).load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(styles);
        stage.setTitle("FASTBook Social Media Application");
        stage.getIcons().add(logo);
        stage.setScene(scene);
        stage.show();
    }
}
