package com.example.sdaprojectsocialmediaapp.models.engagements;

public class Reply {
    private int id;
    private int postID;
    private String text;

    public Reply(int id, int postID, String text) {
        this.id = id;
        this.postID = postID;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
