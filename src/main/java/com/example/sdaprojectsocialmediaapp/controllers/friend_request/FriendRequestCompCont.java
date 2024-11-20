package com.example.sdaprojectsocialmediaapp.controllers.friend_request;

import com.example.sdaprojectsocialmediaapp.models.FriendRequestComp;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class FriendRequestCompCont {
    StudentRepository studentRepository = new StudentRepository();

    @FXML
    private Pane pane;

    @FXML
    private Label requestDate;

    @FXML
    private Label senderName;

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
    void viewProfile(MouseEvent event) {
        // open sender profile
    }

    @FXML
    public void initializePost(FriendRequestComp friendRequest) {
        senderName.setText(studentRepository.getStudentByID(friendRequest.getFromId()).getName());
        requestDate.setText("Requested On: " + friendRequest.getRequestDate());
    }
}
