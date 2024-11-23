package com.example.sdaprojectsocialmediaapp.controllers.chat;

import com.example.sdaprojectsocialmediaapp.models.Student;
import com.example.sdaprojectsocialmediaapp.models.chat.Message;
import com.example.sdaprojectsocialmediaapp.repository.MessageRepository;
import com.example.sdaprojectsocialmediaapp.services.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class ContactCont {

    private MessageRepository messageRepo = new MessageRepository();

    @FXML
    private Label contactName;

    @FXML
    private Label metaMessage;

    @FXML
    private Label time;

    @FXML
    private AnchorPane contact;

    @FXML
    public void initializeContacts(Student friend, Message lastMessage, VBox messagesContainer) {
        this.contactName.setText(friend.getUserName());
        this.time.setText(lastMessage.getTime());
        this.metaMessage.setText(lastMessage.getText());

        this.contact.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            ArrayList<Message> messages = messageRepo.getChat(Session.getSessionVariable().getId(), friend.getId());
            messagesContainer.getChildren().clear();
            StaticToID.setId(friend.getId());
            for (Message message : messages) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/chat/message.fxml"));
                try {
                    AnchorPane messagePane = loader.load();
                    MessageCont messageCont = loader.getController();
                    messageCont.initializeMessage(message);
                    messagesContainer.getChildren().add(messagePane);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
