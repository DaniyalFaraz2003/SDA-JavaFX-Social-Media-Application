package com.example.sdaprojectsocialmediaapp.controllers.chat;

import com.example.sdaprojectsocialmediaapp.controllers.MainController;
import com.example.sdaprojectsocialmediaapp.models.Student;
import com.example.sdaprojectsocialmediaapp.models.chat.Message;
import com.example.sdaprojectsocialmediaapp.repository.FriendRequestRepository;
import com.example.sdaprojectsocialmediaapp.repository.MessageRepository;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import com.example.sdaprojectsocialmediaapp.services.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ChatCont extends MainController {

    private StudentRepository studentRepo = new StudentRepository();
    private FriendRequestRepository friendRepo = new FriendRequestRepository();
    private MessageRepository messageRepo = new MessageRepository();

    @FXML
    private VBox contacts;

    @FXML
    private TextField messageBox;

    @FXML
    private VBox messagesContainer;

    @FXML
    private ImageView sendBtn;

    @FXML
    private Label contactName;

    @FXML
    private Label metaMessage;

    @FXML
    private Label time;

    @FXML
    private AnchorPane contact;

    @FXML
    public void initializePage() throws IOException {
        ArrayList<Integer> friendIDs = friendRepo.getAllFriends(Session.getSessionVariable().getId());
        Student friend = null;
        ArrayList<Message> messages = null;
        this.contacts.getChildren().clear();
        for (int id : friendIDs) {
            friend = studentRepo.getStudentByID(id);
            messages = messageRepo.getChat(Session.getSessionVariable().getId(), id);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/chat/contact.fxml"));
            AnchorPane pane = loader.load();
            ChatCont controller = loader.getController();
            controller.initializeContacts(friend, messages, messagesContainer, sendBtn, messageBox);
            this.contacts.getChildren().add(pane);
        }
    }

    @FXML
    private void initializeContacts(Student friend, ArrayList<Message> messages, VBox messagesContainer, ImageView sendBtn, TextField messageBox) {
        this.contactName.setText(friend.getUserName());
        this.time.setText(messages.get(messages.size() - 1).getTime());
        this.metaMessage.setText(messages.get(messages.size() - 1).getText());
        this.contact.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            messagesContainer.getChildren().clear();
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
            sendBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, sendEvent -> {
                String message = messageBox.getText();
                messageBox.setText("");
                Message newMessage = new Message(friend.getId(), Session.getSessionVariable().getId(), message, new Timestamp(System.currentTimeMillis()));
                FXMLLoader messageLoader = new FXMLLoader(getClass().getResource("/fxml/chat/message.fxml"));
                AnchorPane messagePane = null;
                try {
                    messagePane = messageLoader.load();
                    MessageCont messageCont = messageLoader.getController();
                    messageCont.initializeMessage(newMessage);
                    messagesContainer.getChildren().add(messagePane);
                    messageRepo.saveChat(newMessage.getFromID(), newMessage.getToID(), message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }

}
