package com.example.sdaprojectsocialmediaapp.models.posts;

import java.sql.Timestamp;

public class Post {
    private int id;
    private String title;
    private String description;
    private String author;
    private int authorId;
    private Timestamp date;

    public Post(int id, String title, String description, String author, int authorId, Timestamp date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.authorId = authorId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    
}
