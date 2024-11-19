package com.example.sdaprojectsocialmediaapp;

import com.example.sdaprojectsocialmediaapp.models.engagements.Answer;
import com.example.sdaprojectsocialmediaapp.models.engagements.Comment;
import com.example.sdaprojectsocialmediaapp.models.engagements.Reply;
import com.example.sdaprojectsocialmediaapp.models.engagements.StudentActivityReply;
import com.example.sdaprojectsocialmediaapp.models.posts.ActivityPost;
import com.example.sdaprojectsocialmediaapp.models.posts.Question;
import com.example.sdaprojectsocialmediaapp.models.posts.SimplePost;
import com.example.sdaprojectsocialmediaapp.repository.PostRepository;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import javafx.application.Application;

import java.util.ArrayList;

public class MainApp {
    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentRepository();
//        System.out.println(studentRepository.checkStudentExists("i221096"));
        //Application.launch(Router.class, args);
        PostRepository postRepository = new PostRepository();
        ArrayList<Question> posts = postRepository.getAllQuestions();

        for (Question post : posts) {
            System.out.println(post.getId());
            System.out.println(post.getAuthorId());
            System.out.println(post.getTitle());
            System.out.println(post.getDescription());
            System.out.println(post.getDate());

            for (Answer answer  : post.getAnswers()) {
                System.out.println(answer.getContent());
                System.out.println(answer.getUpVotes());
                System.out.println(answer.getDate());
            }
        }
    }
}
