package com.example.sdaprojectsocialmediaapp.models.posts;

import com.example.sdaprojectsocialmediaapp.models.engagements.Comment;

import java.sql.Timestamp;
import java.util.ArrayList;

public class SimplePost extends Post {
    private String postImageUrl;
    private int numberOfLikes;
    private int numberOfComments;
    private ArrayList<Comment> comments;

    public SimplePost(int id, String title, String description, String author, int authorId, Timestamp date, String postImageUrl, int numberOfLikes, ArrayList<Comment> comments) {
        super(id, title, description, author, authorId, date);
        this.postImageUrl = postImageUrl;
        this.numberOfLikes = numberOfLikes;
        this.numberOfComments = comments.size();
        this.comments = comments;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments() {
        this.numberOfComments = comments.size();
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        numberOfComments++;
    }

}
