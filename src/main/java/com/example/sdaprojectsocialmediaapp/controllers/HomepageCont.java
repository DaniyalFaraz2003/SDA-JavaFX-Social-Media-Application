package com.example.sdaprojectsocialmediaapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageCont {
    @FXML
    private Stage stage;

    @FXML
    private VBox container;

    @FXML
    public void initialize(Stage stage) throws IOException {
        container.getChildren().clear();
        // Loading Activity Posts
        for (int i = 0; i < 3; i++) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/activity_post.fxml"));
            Pane pane = loader.load();
            ActivityPostCont controller = loader.getController();
            controller.set();
            container.getChildren().add(pane);
        }
        // Loading Simple Posts
        for (int i = 0; i < 3; i++) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/simple_post.fxml"));
            Pane pane = loader.load();
            SimplePostCont controller = loader.getController();
            controller.set();
            container.getChildren().add(pane);
        }
        // Loading Questions
        for (int i = 0; i < 3; i++) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/question.fxml"));
            Pane pane = loader.load();
            QuestionCont controller = loader.getController();
            controller.set();
            container.getChildren().add(pane);
        }
        this.stage = stage;
    }
}
