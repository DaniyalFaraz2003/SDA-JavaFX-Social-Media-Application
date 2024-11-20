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
        String sql = "INSERT INTO Student (name, username, phone_number, email, password) VALUES (?, ?, ?, ?, ?)";

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

    public boolean checkUsernameTaken(String username) {
        String sql = "SELECT * FROM Student WHERE username = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //Set parameters
            pstmt.setString(1, username);

            try(ResultSet rs = pstmt.executeQuery()){
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Error checking if Student Exists" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public Student getStudentbyUsername(String username) {
        String sql = "SELECT * FROM Student WHERE username = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //Set parameters
            pstmt.setString(1, username);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    int id = rs.getInt("ID");
                    String user = rs.getString("username");
                    String password = rs.getString("password");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone_number");
                    Student student = new Student(name, user, password, email, phone);
                    student.setId(id);
                    return student;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error checking if Student Exists" + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public Student getStudentByID(int id) {
        String sql = "SELECT * FROM Student WHERE id = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //Set parameters
            pstmt.setInt(1, id);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    int userid = rs.getInt("ID");
                    String user = rs.getString("username");
                    String password = rs.getString("password");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone_number");
                    Student student = new Student(name, user, password, email, phone);
                    student.setId(userid);
                    return student;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error checking if Student Exists" + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateStudent(Student student) {
        String sql = "UPDATE Student SET username = ?, password = ?, name = ?, email = ?, phone_number = ? " +
                "WHERE ID = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameters from the Student object
            pstmt.setString(1, student.getUserName());
            pstmt.setString(2, student.getPassword());
            pstmt.setString(3, student.getName());
            pstmt.setString(4, student.getEmail());
            pstmt.setString(5, student.getPhone());
            pstmt.setInt(6, student.getId()); // ID to identify the record

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Return true if the update was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an exception occurred
        }
    }

}
