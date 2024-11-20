package com.example.sdaprojectsocialmediaapp.controllers.friend_request;

import com.example.sdaprojectsocialmediaapp.Router;
import com.example.sdaprojectsocialmediaapp.controllers.ViewProfileCont;
import com.example.sdaprojectsocialmediaapp.controllers.friends.FriendPageCont;
import com.example.sdaprojectsocialmediaapp.models.FriendRequestComp;
import com.example.sdaprojectsocialmediaapp.models.Student;
import com.example.sdaprojectsocialmediaapp.repository.FriendRequestRepository;
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

public class FriendRequestCompCont {
    StudentRepository studentRepository = new StudentRepository();
    FriendRequestRepository friendRequestRepository = new FriendRequestRepository();

    @FXML
    private Button acceptBtn;

    @FXML
    private Pane pane;

    @FXML
    private Button rejectBtn;

    @FXML
    private Label requestDate;

    @FXML
    private Label senderName;

    @FXML
    private Button viewProfileBtn;

    @FXML
    void acceptRequest(MouseEvent event) {
        // remove request
        // add friend in database
    }

    @FXML
    void rejectRequest(MouseEvent event) {
        // remove request
    }

    @FXML
    public void initializePost(FriendRequestComp friendRequest, Stage stage) {
        Student s = studentRepository.getStudentByID(friendRequest.getFromId());
        senderName.setText(s.getName());
        requestDate.setText("Requested On: " + friendRequest.getRequestDate());
        viewProfileBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/view_profile.fxml"));
            try {
                Parent root = loader.load();
                ViewProfileCont controller = loader.getController();
                controller.initializePage(s.getUserName());
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        acceptBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            friendRequestRepository.acceptFriendRequest(friendRequest.getToId(), friendRequest.getFromId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/friend_request/friend_req_page.fxml"));
            try {
                Parent root = loader.load();
                FriendRequestCont controller = loader.getController();
                controller.initializePage(stage);
                stage.getScene().setRoot(root); // Set the updated scene
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        rejectBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            friendRequestRepository.removeFriendRequest(friendRequest.getToId(), friendRequest.getFromId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/friend_request/friend_req_page.fxml"));
            try {
                Parent root = loader.load();
                FriendRequestCont controller = loader.getController();
                controller.initializePage(stage);
                stage.getScene().setRoot(root); // Set the updated scene
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
