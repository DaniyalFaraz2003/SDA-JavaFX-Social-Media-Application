package com.example.sdaprojectsocialmediaapp.repository;

import com.example.sdaprojectsocialmediaapp.models.Student;
import com.example.sdaprojectsocialmediaapp.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            pstmt.setString(1, student.getName());
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

    public boolean checkStudentExists(String username, String password) {
        String sql = "SELECT * FROM Student WHERE username = ? and password = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Set parameters for the prepared statement
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try(ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            System.out.println("Error checking if Student Exists" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

}
