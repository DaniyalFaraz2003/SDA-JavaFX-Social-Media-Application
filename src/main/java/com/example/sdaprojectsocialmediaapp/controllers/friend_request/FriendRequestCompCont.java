package com.example.sdaprojectsocialmediaapp.controllers.friend_request;

import com.example.sdaprojectsocialmediaapp.Router;
import com.example.sdaprojectsocialmediaapp.controllers.ViewProfileCont;
import com.example.sdaprojectsocialmediaapp.models.FriendRequestComp;
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

    @FXML
    private Pane pane;

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
        senderName.setText(studentRepository.getStudentByID(friendRequest.getFromId()).getName());
        requestDate.setText("Requested On: " + friendRequest.getRequestDate());
        viewProfileBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/view_profile.fxml"));
            try {
                Parent root = loader.load();
                ViewProfileCont controller = loader.getController();
                controller.initializePage(studentRepository.getStudentByID(friendRequest.getFromId()).getUserName());
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
