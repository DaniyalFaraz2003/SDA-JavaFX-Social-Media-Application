package com.example.sdaprojectsocialmediaapp.models.engagements;


import java.security.Timestamp;

public class Message {
    private int id;
    private int toID;
    private int fromID;
    private String text;
    private Timestamp date;

    public Message(int id, int toID, int fromID, String text, Timestamp date) {
        this.id = id;
        this.toID = toID;
        this.fromID = fromID;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getToID() {
        return toID;
    }

    public void setToID(int toID) {
        this.toID = toID;
    }

    public int getFromID() {
        return fromID;
    }

    public void setFromID(int fromID) {
        this.fromID = fromID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
