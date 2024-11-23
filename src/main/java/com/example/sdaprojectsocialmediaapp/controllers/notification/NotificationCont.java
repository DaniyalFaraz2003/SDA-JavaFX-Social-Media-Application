package com.example.sdaprojectsocialmediaapp.controllers.notification;

import com.example.sdaprojectsocialmediaapp.models.notification.Notification;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class NotificationCont {
    StudentRepository studentRepo = new StudentRepository();

    @FXML
    private Label name;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label postType;

    @FXML
    public void initializeNotification(Notification notification) {
        this.name.setText(studentRepo.getStudentByID(notification.getSenderId()).getName());
        this.postType.setText(notification.getMessage());
    }

}

