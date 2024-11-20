package com.example.sdaprojectsocialmediaapp.controllers.friends;

import com.example.sdaprojectsocialmediaapp.controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class FriendPageCont extends MainController {

    @FXML
    Pane pane;

    @FXML
    private VBox container;

    @FXML
    private Label friendCount;

    @FXML
    public void initializePage(Stage stage) throws IOException {
        int size = 6;
        container.getChildren().clear();
        // setting the request count
        friendCount.setText(Integer.toString(size) + " friends");
        // populating the page
        for (int i = 0; i < size; i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/friend/friend_component.fxml"));
            pane = loader.load();
            FriendCompCont controller = loader.getController();
            controller.initializeComp("i221096", stage);
            container.getChildren().add(pane);
        }
    }

}
