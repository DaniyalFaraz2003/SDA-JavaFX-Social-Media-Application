package com.example.sdaprojectsocialmediaapp;

import com.example.sdaprojectsocialmediaapp.models.chat.Message;
import com.example.sdaprojectsocialmediaapp.repository.FriendRequestRepository;
import com.example.sdaprojectsocialmediaapp.repository.MessageRepository;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import javafx.application.Application;

import java.util.ArrayList;

public class MainApp {
    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentRepository();
        FriendRequestRepository friendRequestRepository = new FriendRequestRepository();
        Application.launch(Router.class, args);

//        MessageRepository messageRepository = new MessageRepository();
//        //messageRepository.saveChat(1,2, "Hi. How are you?");
//        //messageRepository.saveChat(2,1, "Daniyal is a bad boi");
//        ArrayList<Message> messages = new ArrayList<>();
//        messages = messageRepository.getChat(1, 2);
//
//        for (Message m : messages) {
//            System.out.println(m.getFromID());
//            System.out.println(m.getToID());
//            System.out.println(m.getText());
//            System.out.println(m.getDate());
//        }
    }
}
