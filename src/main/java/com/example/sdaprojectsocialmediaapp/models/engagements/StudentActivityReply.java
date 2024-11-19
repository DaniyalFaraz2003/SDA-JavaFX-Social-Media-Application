package com.example.sdaprojectsocialmediaapp.models.engagements;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StudentActivityReply {
    private int stdID;
    private int replyID;
    private int postId;
    private Timestamp timestamp;


    public StudentActivityReply(int stdID, int replyID, int postId, Timestamp timestamp) {
        this.stdID = stdID;
        this.replyID = replyID;
        this.postId = postId;
        this.timestamp = timestamp;
    }

    public int getStdID() {
        return stdID;
    }

    public void setStdID(int stdID) {
        this.stdID = stdID;
    }

    public int getReplyID() {
        return replyID;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTimestamp() {
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setReplyID(int replyID) {
        this.replyID = replyID;
    }
}
