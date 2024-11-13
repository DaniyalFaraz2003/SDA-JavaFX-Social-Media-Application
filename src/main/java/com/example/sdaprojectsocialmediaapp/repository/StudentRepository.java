package com.example.sdaprojectsocialmediaapp.repository;

import com.example.sdaprojectsocialmediaapp.models.Student;
import com.example.sdaprojectsocialmediaapp.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class StudentRepository {
    private final DatabaseConnector dbConnector;
    public StudentRepository() {
        dbConnector = new DatabaseConnector();
    }

    public void saveStudent(Student student) {
        String sql = "INSERT INTO Student (name, username, phone, email, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameters for the prepared statement
            pstmt.setString(1, student.getFirstName() + " " + student.getLastName());
            pstmt.setString(2, student.getUserName());
            pstmt.setString(3, student.getPhone());
            pstmt.setString(4, student.getEmail());
            pstmt.setString(5, student.getPassword());

            // Execute the insert
            pstmt.executeUpdate();
            System.out.println("Student saved successfully.");

        } catch (SQLException e) {
            System.out.println("Error saving student: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
