package com.example.sdaprojectsocialmediaapp.repository;

import com.example.sdaprojectsocialmediaapp.models.FriendRequestComp;
import com.example.sdaprojectsocialmediaapp.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;

public class FriendRequestRepository {
    private final DatabaseConnector dbConnector;

    public FriendRequestRepository() {
        dbConnector = new DatabaseConnector();
    }

    public ArrayList<FriendRequestComp> getAllFriendsRequests(int studentID) {
        ArrayList<FriendRequestComp> friendRequests = new ArrayList<>();
        String sql = "SELECT * FROM Friend WHERE student_id_to = ? and status = ?";
        boolean status = false;

        try(Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, studentID);
            pstmt.setBoolean(2, status);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                int fromID = rs.getInt("student_id_from");
                int toID = rs.getInt("student_id_to");
                Timestamp timeStamp = rs.getTimestamp("time_stamp");

                FriendRequestComp comp = new FriendRequestComp(id, fromID, toID, timeStamp, status);
                friendRequests.add(comp);
            }

        } catch (SQLException e) {
            System.out.println("Error finding friends" + e.getMessage());
            e.printStackTrace();
        }

        if(!friendRequests.isEmpty()) {
            return friendRequests;
        }

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
