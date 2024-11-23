package com.example.sdaprojectsocialmediaapp.repository;

import com.example.sdaprojectsocialmediaapp.models.chat.Message;
import com.example.sdaprojectsocialmediaapp.utils.DatabaseConnector;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;

public class MessageRepository {
    private final DatabaseConnector dbConnector;

    public MessageRepository() {
        this.dbConnector = new DatabaseConnector();
    }

    public ArrayList<Message> getChat(int loggedInID, int otherID){
        String sql = "SELECT * FROM Message WHERE (student_id_from = ? AND student_id_to = ?) or (student_id_from = ? AND student_id_to = ?) order by time_stamp";
        ArrayList<Message> messages = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection();) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, loggedInID);
            pstmt.setInt(2, otherID);
            pstmt.setInt(3, otherID);
            pstmt.setInt(4, loggedInID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String text = rs.getString("text");
                Timestamp timestamp = rs.getTimestamp("time_stamp");
                int fromID = rs.getInt("student_id_from");
                int toID = rs.getInt("student_id_to");
                int id = rs.getInt("ID");
                Message message = new Message(toID, fromID, text, timestamp);
                message.setId(id);
                messages.add(message);
            }

        } catch (SQLException e) {
            System.out.println("Error getting messages" + e.getMessage());
            e.printStackTrace();
        }

        return messages;
    }

    public void saveChat(int fromID, int toID, String text){
        String sql = "INSERT into Message (student_id_from, student_id_to, text, time_stamp) VALUES (?, ?, ?, ?)";

        try (Connection conn = dbConnector.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, fromID);
            pstmt.setInt(2, toID);
            pstmt.setString(3, text);
            pstmt.setTimestamp(4, Timestamp.from(Instant.now()));
            pstmt.executeUpdate();
            System.out.println("Message saved successfully");

        } catch (SQLException e) {
            System.out.println("Error saving message" + e.getMessage());
            e.printStackTrace();
        }
    }
}
