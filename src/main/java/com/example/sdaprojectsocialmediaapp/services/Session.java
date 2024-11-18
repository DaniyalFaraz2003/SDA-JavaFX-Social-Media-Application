package com.example.sdaprojectsocialmediaapp.services;

import com.example.sdaprojectsocialmediaapp.models.Student;
import com.example.sdaprojectsocialmediaapp.models.posts.Post;
import com.example.sdaprojectsocialmediaapp.repository.PostRepository;

import java.util.ArrayList;

public class Session {
    private static Student student;

    public static void maintainSession(Student st) {
        student = st;
    }

    public static Student getSessionVariable(){
        return student;
    }
}
