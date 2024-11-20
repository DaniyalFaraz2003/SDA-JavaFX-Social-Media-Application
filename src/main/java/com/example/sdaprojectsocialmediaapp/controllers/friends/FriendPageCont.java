package com.example.sdaprojectsocialmediaapp.controllers.friends;

import com.example.sdaprojectsocialmediaapp.controllers.MainController;
import com.example.sdaprojectsocialmediaapp.models.Student;
import com.example.sdaprojectsocialmediaapp.repository.FriendRequestRepository;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import com.example.sdaprojectsocialmediaapp.services.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class FriendPageCont extends MainController {
    FriendRequestRepository friendRequestRepository = new FriendRequestRepository();
    StudentRepository studentRepository = new StudentRepository();

    @FXML
    Pane pane;

    @FXML
    private VBox findFriendsPane;

    @FXML
    private VBox container;

    @FXML
    private Label friendCount;

    @FXML
    public void initializePage(Stage stage) throws IOException {
        ArrayList<Integer> friends = friendRequestRepository.getAllFriends(Session.getSessionVariable().getId());
        ArrayList<Integer> suggestions = friendRequestRepository.getSuggestionsForFriendRequest(Session.getSessionVariable().getId());
        container.getChildren().clear();
        findFriendsPane.getChildren().clear();
        // setting the request count
        friendCount.setText(Integer.toString(friends.size()) + " friends");
        // populating the page
        for (int i = 0; i < friends.size(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/friend/friend_component.fxml"));
            pane = loader.load();
            FriendCompCont controller = loader.getController();
            Student s = studentRepository.getStudentByID(friends.get(i));
            controller.initializeComp(s.getName(), s.getUserName(), s.getId(), stage);
            container.getChildren().add(pane);
        }

        for (int id: suggestions) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/friend/find_friend.fxml"));
            pane = loader.load();
            FindFriendCompCont controller = loader.getController();
            Student s = studentRepository.getStudentByID(id);
            controller.initializeComp(s.getName(), s.getUserName(), s.getId(), stage);
            findFriendsPane.getChildren().add(pane);
        }
    }

}
