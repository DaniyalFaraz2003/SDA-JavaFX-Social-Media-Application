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
    }
}
