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

    public ArrayList<FriendRequestComp> getAllFriends() {
        return null;
    }

    public ArrayList<FriendRequestComp> getAllFriends(int studentId) {
        String sql = "Select * from Friend Where (student_id_from = ? or student_id_to = ?) and status = ?";
        ArrayList<FriendRequestComp> friends = new ArrayList<>();
        boolean status = true;

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt;
        }

        return null;
    }
}
