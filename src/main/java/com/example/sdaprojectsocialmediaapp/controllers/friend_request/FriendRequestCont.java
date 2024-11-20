package com.example.sdaprojectsocialmediaapp.controllers.friend_request;

import com.example.sdaprojectsocialmediaapp.controllers.MainController;
import com.example.sdaprojectsocialmediaapp.models.FriendRequestComp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.Timestamp;

public class FriendRequestCont extends MainController {

    private ArrayList<FriendRequestComp> friendRequests;

    @FXML
    Pane pane;

    @FXML
    private VBox container;

    @FXML
    private Label requestCount;

    private void populateFriendRequests() {
        friendRequests = new ArrayList<>();
        // Mock friend request data
        friendRequests.add(new FriendRequestComp(1, 1, 3, new Timestamp(System.currentTimeMillis() - 3600 * 1000), false));
        friendRequests.add(new FriendRequestComp(2, 3, 2, new Timestamp(System.currentTimeMillis() - 3600 * 1000), false)); // 1 hour ago
        friendRequests.add(new FriendRequestComp(3, 4, 2, new Timestamp(System.currentTimeMillis() - 3600 * 1000), false)); // 2 hours ago
    }

    @FXML
    public void initializePage(Stage stage) throws IOException {
        // get array list of friend request component
        populateFriendRequests();
        int size = friendRequests.size();
        container.getChildren().clear();
        // setting the request count
        requestCount.setText(Integer.toString(size) + " requests");
        // populating the page
        for (int i = 0; i < size; i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/friend_request/friend_req_component.fxml"));
            pane = loader.load();
            FriendRequestCompCont controller = loader.getController();
            controller.initializePost(friendRequests.get(i), stage);
            container.getChildren().add(pane);
        }
    }

}
