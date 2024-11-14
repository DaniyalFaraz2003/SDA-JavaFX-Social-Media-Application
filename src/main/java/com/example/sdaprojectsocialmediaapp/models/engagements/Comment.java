package com.example.sdaprojectsocialmediaapp.models.engagements;

import java.sql.Timestamp;

public class Comment extends Response {
    private int likes;

    public Comment(int postId, int authorId, Timestamp date, String content, int likes) {
        super(postId, authorId, date, content);
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
