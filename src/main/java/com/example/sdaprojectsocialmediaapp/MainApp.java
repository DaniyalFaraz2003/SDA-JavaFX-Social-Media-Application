package com.example.sdaprojectsocialmediaapp;

import com.example.sdaprojectsocialmediaapp.models.FriendRequestComp;
import com.example.sdaprojectsocialmediaapp.models.engagements.Answer;
import com.example.sdaprojectsocialmediaapp.models.engagements.Comment;
import com.example.sdaprojectsocialmediaapp.models.engagements.Reply;
import com.example.sdaprojectsocialmediaapp.models.engagements.StudentActivityReply;
import com.example.sdaprojectsocialmediaapp.models.posts.ActivityPost;
import com.example.sdaprojectsocialmediaapp.models.posts.Question;
import com.example.sdaprojectsocialmediaapp.models.posts.SimplePost;
import com.example.sdaprojectsocialmediaapp.repository.FriendRequestRepository;
import com.example.sdaprojectsocialmediaapp.repository.PostRepository;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import javafx.application.Application;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

public class MainApp {
    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentRepository();
        FriendRequestRepository friendRequestRepository = new FriendRequestRepository();
        //Application.launch(Router.class, args);

        PostRepository postRepository = new PostRepository();
        postRepository.addStudentActivityReply(1, 4, 3);
    }
}
