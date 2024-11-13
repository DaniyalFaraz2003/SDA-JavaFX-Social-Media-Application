package com.example.sdaprojectsocialmediaapp.repository;

import com.example.sdaprojectsocialmediaapp.models.Student;
import com.example.sdaprojectsocialmediaapp.utils.DatabaseConnector;

public class StudentRepository {
    private DatabaseConnector dbConnector;
    public StudentRepository() {
        dbConnector = new DatabaseConnector();
    }

    public void saveStudent(Student student) {

    }


}
