package com.example.sdaprojectsocialmediaapp;

import com.example.sdaprojectsocialmediaapp.controllers.ActivityPostCont;
import com.example.sdaprojectsocialmediaapp.controllers.HomepageCont;
import com.example.sdaprojectsocialmediaapp.controllers.LoginCont;
import com.example.sdaprojectsocialmediaapp.controllers.RegisterCont;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Router extends Application {
    @FXML
    private static Stage stage;

    @FXML
    private static Scene scene;

    @FXML
    private static FXMLLoader fxmlLoader;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Router.stage = stage;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        Router.scene = scene;
    }

    public static FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    public static void setFxmlLoader(FXMLLoader fxmlLoader) {
        Router.fxmlLoader = fxmlLoader;
    }

    @Override
    public void start(Stage stage1) throws IOException {
        setStage(stage1);
        fxmlLoader = new FXMLLoader(Router.class.getResource("/fxml/register.fxml"));
        Parent root = fxmlLoader.load();
        RegisterCont controller = fxmlLoader.getController();
        scene = new Scene(root);
        stage.setTitle("FASTBook Social Media Application");
        stage.getIcons().add(new Image(Objects.requireNonNull(Router.class.getResource("/images/logo.png")).toExternalForm()));
        stage.setScene(scene);
        controller.setStage(stage);
        stage.show();
    }

    public static void navigateTo(String page) throws IOException {
        Parent root = null;
        switch (page) {
            case "Register":
                fxmlLoader = new FXMLLoader(Router.class.getResource("/fxml/register.fxml"));
                root = fxmlLoader.load();
                RegisterCont regCont = fxmlLoader.getController();
                scene = new Scene(root);
                regCont.setStage(stage);
                break;
            case "Login":
                fxmlLoader = new FXMLLoader(Router.class.getResource("/fxml/login.fxml"));
                root = fxmlLoader.load();
                LoginCont loginCont = fxmlLoader.getController();
                scene = new Scene(root);
                loginCont.setStage(stage);
                break;
            case "Homepage":
                fxmlLoader = new FXMLLoader(Router.class.getResource("/fxml/homepage.fxml"));
                root = fxmlLoader.load();
                HomepageCont homeCont = fxmlLoader.getController();
                scene = new Scene(root);
                homeCont.initialize(stage);
                break;
            case "Activity Post Page":
                fxmlLoader = new FXMLLoader(Router.class.getResource("/fxml/activity_post/activity_post_page.fxml"));
                root = fxmlLoader.load();
                ActivityPostCont activityCont = fxmlLoader.getController();
                scene = new Scene(root);
                activityCont.initializePage();
                break;
        }
        stage.setScene(scene);
    }
}
