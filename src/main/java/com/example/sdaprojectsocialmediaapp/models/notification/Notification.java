package com.example.sdaprojectsocialmediaapp.models.notification;

import java.sql.Timestamp;

public class Notification {
    private int id;
    private int senderId;
    private int recieverId;
    private String message;
    private Timestamp timestamp;

    public Notification(int id, int senderId, int recieverId, String message, Timestamp timestamp) {
        this.id = id;
        this.senderId = senderId;
        this.recieverId = recieverId;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(int recieverId) {
        this.recieverId = recieverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
