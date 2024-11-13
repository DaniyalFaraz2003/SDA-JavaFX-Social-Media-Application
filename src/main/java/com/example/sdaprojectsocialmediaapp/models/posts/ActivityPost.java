package com.example.sdaprojectsocialmediaapp.models.posts;

import com.example.sdaprojectsocialmediaapp.models.engagements.Reply;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ActivityPost extends Post {
    private ArrayList<Reply> replies;
    private int size;

    public ActivityPost(int id, String title, String description, String author, int authorId, Timestamp date, ArrayList<Reply> replies) {
        super(id, title, description, author, authorId, date);
        this.replies = replies;
        this.size = replies.size();
    }

    public ArrayList<Reply> getReplies() {
        return replies;
    }

    public void setReplies(ArrayList<Reply> replies) {
        this.replies = replies;
    }

    public int getSize() {
        return size;
    }

    public void setSize() {
        this.size = replies.size();
    }

    public void addReply(Reply reply) {
        replies.add(reply);
        size++;
    }
}
