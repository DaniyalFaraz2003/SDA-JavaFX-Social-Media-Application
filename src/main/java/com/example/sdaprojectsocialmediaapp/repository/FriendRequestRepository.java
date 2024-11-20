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

    public ArrayList<Integer> getAllFriends(int studentId) {
        String sql = "Select * from Friend Where (student_id_from = ? or student_id_to = ?) and status = ?";
        ArrayList<Integer> friends = new ArrayList<>();
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
                Timestamp timeStamp = rs1.getTimestamp("time_stamp");

                FriendRequestComp fr = new FriendRequestComp(id, id_from, id_to, timeStamp, st);
                if (fr.getToId() == studentId) {
                    friends.add(fr.getFromId());
                } else if (fr.getFromId() == studentId) {
                    friends.add(fr.getToId());
                }

            }

            return friends;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeFriendRequest(int toId, int fromId) {
        // SQL query to delete entries where the IDs match in any order
        String sql = "DELETE FROM Friend " +
                "WHERE (student_id_from = ? AND student_id_to = ?) " +
                " and status = FALSE";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the parameters for both ID combinations
            pstmt.setInt(1, fromId);
            pstmt.setInt(2, toId);

            // Execute the update
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " friend request(s) deleted.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeFriend(int toId, int fromId) {
        // SQL query to delete entries where the IDs match in any order
        String sql = "DELETE FROM Friend " +
                "WHERE (student_id_from = ? AND student_id_to = ?) " +
                "   OR (student_id_from = ? AND student_id_to = ?) and status = TRUE";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the parameters for both ID combinations
            pstmt.setInt(1, toId);
            pstmt.setInt(2, fromId);
            pstmt.setInt(3, fromId);
            pstmt.setInt(4, toId);

            // Execute the update
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " friend(s) deleted.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void acceptFriendRequest(int toId, int fromId) {
        // SQL query to update the status of the friend request
        String sql = "UPDATE Friend " +
                "SET status = TRUE " +
                "WHERE student_id_from = ? AND student_id_to = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the parameters for the query
            pstmt.setInt(1, fromId);
            pstmt.setInt(2, toId);

            // Execute the update
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Friend request accepted.");
            } else {
                System.out.println("No matching friend request found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
