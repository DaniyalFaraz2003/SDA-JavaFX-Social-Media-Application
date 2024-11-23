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
    private void sendMessage(MouseEvent event) {
        String message = messageBox.getText();
        messageBox.setText("");
        System.out.println("To ID is: " + StaticToID.getId());
        Message newMessage = new Message(StaticToID.getId(), Session.getSessionVariable().getId(), message, new Timestamp(System.currentTimeMillis()));
        FXMLLoader messageLoader = new FXMLLoader(getClass().getResource("/fxml/chat/message.fxml"));
        try {
            AnchorPane messagePane = messageLoader.load();
            MessageCont messageCont = messageLoader.getController();
            messageCont.initializeMessage(newMessage);
            messagesContainer.getChildren().add(messagePane);
            messageRepo.saveChat(newMessage.getFromID(), newMessage.getToID(), message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
            ContactCont controller = loader.getController();
            if (messages.size() > 0)
                controller.initializeContacts(friend, messages.get(messages.size() - 1), messagesContainer);
            else
                controller.initializeContacts(friend, new Message(friend.getId(), Session.getSessionVariable().getId(), "end to end encrypted", null), messagesContainer);
            this.contacts.getChildren().add(pane);
        }
    }

}
