package com.example.sdaprojectsocialmediaapp.models.engagements;

public class Reply {
    private int postID;
    private String text;

    public Reply(int postID, String text) {
        this.postID = postID;
        this.text = text;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
