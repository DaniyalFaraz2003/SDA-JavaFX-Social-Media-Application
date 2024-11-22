package com.example.sdaprojectsocialmediaapp.models.engagements;

import java.sql.Timestamp;

public class Answer extends Response {
    private int upVotes;
    private boolean isCorrect;
    private int id;

    public Answer(int id, int postId, int authorId, Timestamp date, String content, int upVotes, boolean isCorrect) {
        super(postId, authorId, date, content);
        this.upVotes = upVotes;
        this.isCorrect = isCorrect;
        this.id = id;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
