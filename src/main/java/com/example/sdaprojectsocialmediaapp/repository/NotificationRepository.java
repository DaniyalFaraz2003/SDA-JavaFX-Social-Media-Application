package com.example.sdaprojectsocialmediaapp.repository;

import com.example.sdaprojectsocialmediaapp.models.notification.Notification;
import com.example.sdaprojectsocialmediaapp.utils.DatabaseConnector;

import java.sql.*;
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

    public void notificationReceived(int notificationID) {
        String sql = "Delete from Notification where id = ?";

        try (Connection conn = dbConnector.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, notificationID);
            pstmt.executeUpdate();
            System.out.println("Notification received successfully");

        } catch (SQLException e) {
            System.out.println("error deleting notification");
            e.printStackTrace();
        }
    }

    public ArrayList<Notification> getNotifications(int receiverID) {
        ArrayList<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM Notification where reciever_id = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, receiverID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int senderID = rs.getInt("sender_id");
                String postType = rs.getString("post_type");
                Timestamp timeStamp = rs.getTimestamp("time_stamp");
                Notification notification = new Notification(id, senderID, receiverID, postType, timeStamp);
                notifications.add(notification);
            }

        } catch (SQLException e) {
            System.out.println("error getting notifications");
            e.printStackTrace();
        }

        return notifications;
    }

}
