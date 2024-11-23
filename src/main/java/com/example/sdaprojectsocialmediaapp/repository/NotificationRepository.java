package com.example.sdaprojectsocialmediaapp.repository;

import com.example.sdaprojectsocialmediaapp.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

public class NotificationRepository {

    private final DatabaseConnector dbConnector;

    public NotificationRepository() {
        dbConnector = new DatabaseConnector();
    }

    public void sendNotification(int senderID, int receiverID, String postType) {
        String sql = "INSERT INTO Notification (sender_id, reciever_id, post_type, time_stamp) VALUES (?,?,?,?)";

        try (Connection conn = dbConnector.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, senderID);
            ps.setInt(2, receiverID);
            ps.setString(3, postType);
            ps.setTimestamp(4, Timestamp.from(Instant.now()));
            ps.executeUpdate();
            System.out.println("Notification sent successfully");
        } catch (SQLException e) {
            System.out.println("error sending notification");
            e.printStackTrace();
        }
    }

}
