package com.example.sdaprojectsocialmediaapp.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FriendRequestComp {
    private int id;
    private int fromId;
    private int toId;
    private Timestamp requestDate;
    private boolean status;

    public FriendRequestComp(int id, int fromId, int toId, Timestamp requestDate, boolean status) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.requestDate = requestDate;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getRequestDate() {
        LocalDateTime localDateTime = requestDate.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }
}
