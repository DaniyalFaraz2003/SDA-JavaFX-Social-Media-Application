package com.example.sdaprojectsocialmediaapp;

import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import javafx.application.Application;

public class MainApp {
    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentRepository();
        //System.out.println(studentRepository.checkStudentExists("i221096"));
        Application.launch(Router.class, args);
    }
}
