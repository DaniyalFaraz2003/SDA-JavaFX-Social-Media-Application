package com.example.sdaprojectsocialmediaapp.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FriendRequestComp {
    private String requesterName;
    private Timestamp requestDate;

    public FriendRequestComp(String requesterName, Timestamp requestDate) {
        this.requesterName = requesterName;
        this.requestDate = requestDate;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
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
