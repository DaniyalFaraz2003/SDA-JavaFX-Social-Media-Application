package com.example.sdaprojectsocialmediaapp.controllers;

import com.example.sdaprojectsocialmediaapp.Router;
import com.example.sdaprojectsocialmediaapp.models.notification.Notification;
import com.example.sdaprojectsocialmediaapp.repository.NotificationRepository;
import com.example.sdaprojectsocialmediaapp.services.Session;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {

    public MainController() {}

    @FXML
    void openActivityPosts(MouseEvent event) throws IOException {
        Router.navigateTo("Activity Post Page");
    }

    @FXML
    void openChatPage(MouseEvent event) throws IOException {
        Router.navigateTo("Chat Page");
    }

    @FXML
    void openDashboard(MouseEvent event) throws IOException {
        Router.navigateTo("Homepage");
    }

    @FXML
    void openFriendsPage(MouseEvent event) throws IOException {
        Router.navigateTo("Friends Page");
    }

    @FXML
    void openProfilePage(MouseEvent event) throws IOException {
        Router.navigateTo("Profile Page");
    }

    @FXML
    void openQuestions(MouseEvent event) throws IOException {
        Router.navigateTo("Question Page");
    }

    @FXML
    void openRequests(MouseEvent event) throws IOException {
        Router.navigateTo("Friend Request Page");
    }

    @FXML
    void openSimplePosts(MouseEvent event) throws IOException {
        Router.navigateTo("Simple Post Page");
    }

    @FXML
    void handleLogout(MouseEvent event) throws IOException {
        NotificationRepository notificationRepository = new NotificationRepository();
        ArrayList<Notification> notifications = notificationRepository.getNotifications(Session.getSessionVariable().getId());
        for (Notification notification : notifications) {
            notificationRepository.notificationReceived(notification.getId());
        }
        Session.maintainSession(null);
        Router.navigateTo("Login");
    }
}
