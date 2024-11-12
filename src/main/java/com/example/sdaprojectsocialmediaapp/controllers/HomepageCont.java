package com.example.sdaprojectsocialmediaapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomepageCont implements Initializable {
    @FXML
    private Stage stage;

    @FXML
    private VBox container;

    @FXML
    public void initialize(Stage stage) throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        container.getChildren().clear();
        // Loading Activity Posts
        for (int i = 0; i < 3; i++) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/activity_post.fxml"));
            Pane pane = null;
            try {
                pane = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ActivityPostCont controller = loader.getController();
            controller.set();
            container.getChildren().add(pane);
        }
        // Loading Simple Posts
        for (int i = 0; i < 3; i++) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/simple_post.fxml"));
            Pane pane = null;
            try {
                pane = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            SimplePostCont controller = loader.getController();
            controller.set();
            container.getChildren().add(pane);
        }
        // Loading Questions
        for (int i = 0; i < 3; i++) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/question.fxml"));
            Pane pane = null;
            try {
                pane = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            QuestionCont controller = loader.getController();
            controller.set();
            container.getChildren().add(pane);
        }
        this.stage = stage;
    }
}
