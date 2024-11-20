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
        Application.launch(Router.class, args);

//        PostRepository postRepository = new PostRepository();
//        ArrayList<ActivityPost> post = postRepository.getActivityPostByStudentID(3);
//
//        for(ActivityPost activityPost : post) {
//            System.out.println(activityPost.getAuthorId());
//            System.out.println(activityPost.getTitle());
//            System.out.println(activityPost.getDescription());
//            System.out.println(activityPost.getDate());
//            //System.out.println(activityPost.get);
//
//            for(Reply reply : activityPost.getReplies()) {
//                System.out.println(reply.getPostID());
//                System.out.println(reply.getText());
//                System.out.println(reply.getId());
//            }
//
//            for (StudentActivityReply sar : activityPost.getStudentReplies()){
//                System.out.println(sar.getReplyID());
//            }
//        }
    }
}
