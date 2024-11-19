package com.example.sdaprojectsocialmediaapp.models.engagements;

public class StudentActivityReply {
    private int stdID;
    private int replyID;

    public StudentActivityReply(int stdID, int replyID) {
        this.stdID = stdID;
        this.replyID = replyID;
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

    public void setReplyID(int replyID) {
        this.replyID = replyID;
    }
}
