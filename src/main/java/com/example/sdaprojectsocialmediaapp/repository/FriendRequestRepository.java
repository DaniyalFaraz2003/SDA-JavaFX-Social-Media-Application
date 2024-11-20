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
}
