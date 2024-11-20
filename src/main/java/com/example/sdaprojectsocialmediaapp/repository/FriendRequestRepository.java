package com.example.sdaprojectsocialmediaapp.repository;

import com.example.sdaprojectsocialmediaapp.models.FriendRequestComp;
import com.example.sdaprojectsocialmediaapp.models.posts.Question;
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
}
