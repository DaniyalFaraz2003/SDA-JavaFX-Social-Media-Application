package com.example.sdaprojectsocialmediaapp.models.notification;

import com.example.sdaprojectsocialmediaapp.repository.NotificationRepository;

public class Friend implements Observer{
    private int id;
    private NotificationRepository repo = new NotificationRepository();

    public Friend(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void notify(Notification notification) {
        repo.sendNotification(notification.getSenderId(), this.id, notification.getMessage());
    }
}
