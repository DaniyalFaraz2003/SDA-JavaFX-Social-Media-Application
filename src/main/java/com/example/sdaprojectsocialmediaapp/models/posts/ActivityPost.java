package com.example.sdaprojectsocialmediaapp.models.posts;

import com.example.sdaprojectsocialmediaapp.models.Student;
import com.example.sdaprojectsocialmediaapp.models.engagements.Reply;
import com.example.sdaprojectsocialmediaapp.models.engagements.StudentActivityReply;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ActivityPost extends Post {
    private ArrayList<Reply> replies;
    private ArrayList<StudentActivityReply> studentReplies;

    public ActivityPost(int id, String title, String description, int authorId, Timestamp date, ArrayList<Reply> replies, ArrayList<StudentActivityReply> studentReplies) {
        super(id, title, description, authorId, date);
        this.replies = replies;
        this.studentReplies = studentReplies;
    }

    public ArrayList<Reply> getReplies() {
        return replies;
    }

    public void setReplies(ArrayList<Reply> replies) {
        this.replies = replies;
    }

    public void addReply(Reply reply) {
        replies.add(reply);
    }

    public ArrayList<StudentActivityReply> getStudentReplies() {
        return studentReplies;
    }

    public void setStudentReplies(ArrayList<StudentActivityReply> studentReplies) {
        this.studentReplies = studentReplies;
    }
}
