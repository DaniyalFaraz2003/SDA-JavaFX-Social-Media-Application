package com.example.sdaprojectsocialmediaapp.repository;

import com.example.sdaprojectsocialmediaapp.models.FriendRequestComp;
import com.example.sdaprojectsocialmediaapp.models.engagements.Reply;
import com.example.sdaprojectsocialmediaapp.models.engagements.StudentActivityReply;
import com.example.sdaprojectsocialmediaapp.models.posts.ActivityPost;
import com.example.sdaprojectsocialmediaapp.models.posts.Question;
import com.example.sdaprojectsocialmediaapp.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;

public class FriendRequestRepository {
    private final DatabaseConnector dbConnector;

    public FriendRequestRepository() {
        dbConnector = new DatabaseConnector();
    }

    public ArrayList<FriendRequestComp> getAllFriends() {
        return null;
    }

    public ArrayList<FriendRequestComp> getAllFriends(int studentId) {
        String sql = "Select * from Friend Where (student_id_from = ? or student_id_to = ?) and status = ?";
        ArrayList<FriendRequestComp> friends = new ArrayList<>();
        boolean status = true;

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, studentId);
            pstmt.setInt(2, studentId);
            pstmt.setBoolean(3, status);
            ResultSet rs1 = pstmt.executeQuery();

            while (rs1.next()) {
                int id = rs1.getInt("ID");
                int id_from = rs1.getInt("student_id_from");
                int id_to = rs1.getInt("student_id_to");
                boolean st = rs1.getBoolean("status");

                FriendRequestComp fr = new FriendRequestComp(id, id_from, id_to)

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
