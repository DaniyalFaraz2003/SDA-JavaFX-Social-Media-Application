package com.example.sdaprojectsocialmediaapp.models.posts;

import com.example.sdaprojectsocialmediaapp.models.engagements.Reply;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Post {
    private int id;
    private String title;
    private String description;
    private int authorId;
    private Timestamp date;

    public Post(int id, String title, String description, int authorId, Timestamp date) {
        this.id = id;
        this.title = title;
        this.description = description;
//        this.author = author;
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

//    public String getAuthor() {
//        return author;
//    }

//    public void setAuthor(String author) {
//        this.author = author;
//    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getDate() {
        LocalDateTime localDateTime = date.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}
