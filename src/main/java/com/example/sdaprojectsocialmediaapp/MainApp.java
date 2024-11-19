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

public class MainApp {
    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentRepository();
//        System.out.println(studentRepository.checkStudentExists("i221096"));
        Application.launch(Router.class, args);
//        PostRepository postRepository = new PostRepository();
//        System.out.println(postRepository.getPostType(2));
//
//        SimplePost post = postRepository.getSimplePost(2);
//        System.out.println(post.getId());
//        System.out.println(post.getDescription());
//        System.out.println(post.getTitle());
//        System.out.println(post.getAuthorId());
//        System.out.println(post.getPostImageUrl());
//        System.out.println(post.getNumberOfLikes());
//        for(Comment c : post.getComments()){
//            System.out.println(c.getAuthorId());
//            System.out.println(c.getLikes());
//            System.out.println(c.getContent());
//            System.out.println(c.getDate());
//            System.out.println(c.getPostId());
//        }
    }
}
