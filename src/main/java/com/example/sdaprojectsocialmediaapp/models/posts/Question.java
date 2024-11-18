package com.example.sdaprojectsocialmediaapp.models.posts;

import com.example.sdaprojectsocialmediaapp.models.engagements.Answer;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Question extends Post {
    private ArrayList<Answer> answers;
    private int size;
    private int upVotes;

    public Question(int id, String title, String description, int authorId, Timestamp date, ArrayList<Answer> answers, int upVotes) {
        super(id, title, description, authorId, date);
        this.answers = answers;
        this.size = answers.size();
        this.upVotes = upVotes;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public int getSize() {
        return size;
    }

    public void setSize() {
        this.size = answers.size();
    }

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
        size++;
    }
}
