package com.example.sdaprojectsocialmediaapp;
import com.example.sdaprojectsocialmediaapp.models.HomePageApp;
import com.example.sdaprojectsocialmediaapp.models.RegisterApp;

import com.example.sdaprojectsocialmediaapp.models.Student;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import com.example.sdaprojectsocialmediaapp.utils.DatabaseConnector;
import javafx.application.Application;

public class MainApp {
    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentRepository();
        //System.out.println(studentRepository.checkStudentExists("i221096"));
        Application.launch(RegisterApp.class, args);
    }
}
