package com.example.sdaprojectsocialmediaapp.controllers.friend_request;

import com.example.sdaprojectsocialmediaapp.controllers.MainController;
import com.example.sdaprojectsocialmediaapp.models.FriendRequestComp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;

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
        friendRequests.add(new FriendRequestComp("John Doe", new Timestamp(System.currentTimeMillis())));
        friendRequests.add(new FriendRequestComp("Jane Smith", new Timestamp(System.currentTimeMillis() - 3600 * 1000))); // 1 hour ago
        friendRequests.add(new FriendRequestComp("Alice Johnson", new Timestamp(System.currentTimeMillis() - 7200 * 1000))); // 2 hours ago
        friendRequests.add(new FriendRequestComp("Bob Brown", new Timestamp(System.currentTimeMillis() - 24 * 3600 * 1000))); // 1 day ago
        friendRequests.add(new FriendRequestComp("Charlie Wilson", new Timestamp(System.currentTimeMillis() - 2 * 24 * 3600 * 1000))); // 2 days ago
        friendRequests.add(new FriendRequestComp("Diana Green", new Timestamp(System.currentTimeMillis() - 7 * 24 * 3600 * 1000))); // 1 week ago
    }

    @FXML
    public void initializePage() throws IOException {
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
            controller.initializePost(friendRequests.get(i));
            container.getChildren().add(pane);
        }
    }

}
