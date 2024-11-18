package com.example.sdaprojectsocialmediaapp.repository;

import com.example.sdaprojectsocialmediaapp.models.Student;
import com.example.sdaprojectsocialmediaapp.models.posts.ActivityPost;
import com.example.sdaprojectsocialmediaapp.utils.DatabaseConnector;
import com.example.sdaprojectsocialmediaapp.models.posts.Post;

import java.sql.*;
import java.util.ArrayList;

public class PostRepository {

    private final DatabaseConnector dbConnector;

    public PostRepository() {
        dbConnector = new DatabaseConnector();
    }

    public ArrayList<Post> getPost(int postID, String type){
        String sql = "Select * from posts where studentID = ?";
        ArrayList<Post> posts = new ArrayList<>();

        try(Connection conn = dbConnector.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, postID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
//                Post post = new Post(rs.getInt("id"), rs.getString("title"), rs.getString("description"), "", rs.getInt("student_id"), rs.getTimestamp("time_stamp"));
//                posts.add(post);
            }

        } catch (SQLException e) {
            System.out.println("Error finding posts" + e.getMessage());
            e.printStackTrace();
        }
        return posts;
    }

    public String getPostType(int postID){
        String sql1 = "Select * from posts where ID = ?";
        String sql2 = "Select * from simple_post where post_id = ?";
        String sql3 = "Select * from question where post_id = ?";
        String sql4 = "Select * from activity_post where post_id = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            //Set parameters
            pstmt1.setInt(1, postID);

            try(ResultSet rs1 = pstmt1.executeQuery()){
                if(rs1.next()){
                    PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                    pstmt2.setInt(1, postID);

                    try(ResultSet rs2 = pstmt2.executeQuery()){
                        if(rs2.next()){
                            return "Simple Post";
                        } else {
                            PreparedStatement pstmt3 = conn.prepareStatement(sql3);
                            pstmt3.setInt(1, postID);

                            try(ResultSet rs3 = pstmt3.executeQuery()){
                                if(rs3.next()){
                                    return "Question";
                                } else {
                                    PreparedStatement pstmt4 = conn.prepareStatement(sql4);
                                    pstmt4.setInt(1, postID);

                                    try(ResultSet rs4 = pstmt4.executeQuery()){
                                        if(rs4.next()){
                                            return "Activity Post";
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error checking if Student Exists" + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public ActivityPost getActivityPost(int postID){
        String sql = "Select * from activity_post where post_id = ?";
        String sql2 = "Select * from post where ID = ?";

        try(Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);

            pstmt.setInt(1, postID);
            pstmt2.setInt(1, postID);
            ResultSet rs1 = pstmt.executeQuery();
            ResultSet rs2 = pstmt2.executeQuery();

            while (rs1.next()) {
                int id = rs2.getInt("ID");
                int author_id = rs2.getInt("student_id");
                String title = rs2.getString("title");
                String description = rs2.getString("description");
                String time_stamp = rs2.getString("time_stamp");
                Timestamp time = Timestamp.valueOf(time_stamp);
                ActivityPost post = new ActivityPost(id, title, description, author_id, time, null);
                return post;
            }

        } catch (SQLException e) {
            System.out.println("Error finding posts" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


}
