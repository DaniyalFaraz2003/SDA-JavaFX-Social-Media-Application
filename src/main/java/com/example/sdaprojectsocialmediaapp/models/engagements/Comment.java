package com.example.sdaprojectsocialmediaapp.models.engagements;

import java.sql.Timestamp;

public class Comment extends Response {
    private int likes;
    private int commentID;

    public Comment(int postId, int authorId, Timestamp date, String content, int likes) {
        super(postId, authorId, date, content);
        this.likes = likes;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
