package com.example.sdaprojectsocialmediaapp.controllers.chat;

import com.example.sdaprojectsocialmediaapp.models.chat.Message;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import com.example.sdaprojectsocialmediaapp.services.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MessageCont {

    StudentRepository studentRepository = new StudentRepository();

    @FXML
    private AnchorPane messageBlock;

    @FXML
    private Label messageText;

    @FXML
    private Label name;

    @FXML
    private Label timeStamp;

    @FXML
    public void initializeMessage(Message message) {
        messageText.setText(message.getText());
        name.setText(studentRepository.getStudentByID(message.getFromID()).getName());
        timeStamp.setText(message.getDate());
    }

}
