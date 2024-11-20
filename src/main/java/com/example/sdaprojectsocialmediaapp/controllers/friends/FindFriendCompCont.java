package com.example.sdaprojectsocialmediaapp.controllers.friends;

import com.example.sdaprojectsocialmediaapp.controllers.ViewProfileCont;
import com.example.sdaprojectsocialmediaapp.repository.FriendRequestRepository;
import com.example.sdaprojectsocialmediaapp.services.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.beans.EventHandler;
import java.io.IOException;

public class FindFriendCompCont {
    FriendRequestRepository fr = new FriendRequestRepository();

    @FXML
    private Button sendRequestBtn;

    @FXML
    private Label suggestedName;

    @FXML
    private Button viewProfileBtn;

    @FXML
    public void initializeComp(String name, String username, int id, Stage stage) {
        suggestedName.setText(name);
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
        sendRequestBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            fr.sendFriendRequest(Session.getSessionVariable().getId(), id);
            // Rerender the friend list
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/friend/friends_page.fxml"));
            try {
                Parent root = loader.load();
                FriendPageCont controller = loader.getController();
                controller.initializePage(stage);
                stage.getScene().setRoot(root); // Set the updated scene
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
