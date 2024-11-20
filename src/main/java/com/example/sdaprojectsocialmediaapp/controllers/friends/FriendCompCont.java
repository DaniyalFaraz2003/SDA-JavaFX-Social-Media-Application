package com.example.sdaprojectsocialmediaapp.controllers.friends;

import com.example.sdaprojectsocialmediaapp.controllers.ViewProfileCont;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class FriendCompCont {

    @FXML
    private Pane pane;

    @FXML
    private Label senderName;

    @FXML
    private Button viewProfileBtn;

    @FXML
    void removeFriend(MouseEvent event) {
        // removes friend from database
    }

    @FXML
    void viewProfile(MouseEvent event) {
        // opens profile page of friend
    }

    @FXML
    public void initializeComp(String username, Stage stage) {
        senderName.setText(username);
        viewProfileBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/view_profile.fxml"));
            try {
                Parent root = loader.load();
                ViewProfileCont controller = loader.getController();
                controller.initializePage(username);
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
