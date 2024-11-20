package com.example.sdaprojectsocialmediaapp.controllers.friend_request;

import com.example.sdaprojectsocialmediaapp.controllers.MainController;
import com.example.sdaprojectsocialmediaapp.models.FriendRequestComp;
import com.example.sdaprojectsocialmediaapp.repository.FriendRequestRepository;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import com.example.sdaprojectsocialmediaapp.services.Session;
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

    FriendRequestRepository friendRequestRepository = new FriendRequestRepository();
    StudentRepository studentRepository = new StudentRepository();

    @FXML
    Pane pane;

    @FXML
    private VBox container;

    @FXML
    private Label requestCount;


    @FXML
    public void initializePage(Stage stage) throws IOException {
        // get array list of friend request component
        ArrayList<FriendRequestComp> requests = friendRequestRepository.getAllFriendsRequests(Session.getSessionVariable().getId());
        int size = 0;
        if (requests != null) {
            size = requests.size();
        }
        container.getChildren().clear();
        // setting the request count
        requestCount.setText(Integer.toString(size) + " requests");
        // populating the page
        for (int i = 0; i < size; i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/friend_request/friend_req_component.fxml"));
            pane = loader.load();
            FriendRequestCompCont controller = loader.getController();
            controller.initializePost(requests.get(i), stage);
            container.getChildren().add(pane);
        }
    }

}
