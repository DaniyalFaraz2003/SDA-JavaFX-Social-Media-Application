package com.example.sdaprojectsocialmediaapp.repository;

import com.example.sdaprojectsocialmediaapp.models.Student;
import com.example.sdaprojectsocialmediaapp.models.engagements.Answer;
import com.example.sdaprojectsocialmediaapp.models.engagements.Comment;
import com.example.sdaprojectsocialmediaapp.models.engagements.StudentActivityReply;
import com.example.sdaprojectsocialmediaapp.models.posts.ActivityPost;
import com.example.sdaprojectsocialmediaapp.models.posts.Question;
import com.example.sdaprojectsocialmediaapp.models.posts.SimplePost;
import com.example.sdaprojectsocialmediaapp.utils.DatabaseConnector;
import com.example.sdaprojectsocialmediaapp.models.posts.Post;
import com.example.sdaprojectsocialmediaapp.models.engagements.Reply;


import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;

public class PostRepository {

    private final DatabaseConnector dbConnector;

    public PostRepository() {
        dbConnector = new DatabaseConnector();
    }

    public String getPostType(int postID){
        String sql1 = "Select * from post where ID = ?";
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
        String sql3 = "Select * from reply where post_id = ?";
        String sql4 = "Select * from Student_ActivityReply where post_id = ?";

        try(Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            PreparedStatement pstmt3 = conn.prepareStatement(sql3);

            pstmt.setInt(1, postID);
            pstmt2.setInt(1, postID);
            pstmt3.setInt(1, postID);
            ResultSet rs1 = pstmt.executeQuery();
            ResultSet rs2 = pstmt2.executeQuery();

            if (rs1.next()) {
                if(rs2.next()){
                    int id = rs2.getInt("ID");
                    int author_id = rs2.getInt("student_id");
                    String title = rs2.getString("title");
                    String description = rs2.getString("description");
                    Timestamp time_stamp = rs2.getTimestamp("time_stamp");
                    ResultSet rs3 = pstmt3.executeQuery();

                    ArrayList<Reply> replies = new ArrayList<>();

                    while(rs3.next()){
                        int p_id = rs3.getInt("post_id");
                        String text = rs3.getString("text");
                        Reply reply = new Reply(p_id, text);
                        replies.add(reply);
                    }
                    PreparedStatement pstmt4 = conn.prepareStatement(sql4);
                    pstmt4.setInt(1, postID);
                    ResultSet rs4 = pstmt4.executeQuery();

                    ArrayList<StudentActivityReply> studentReply = new ArrayList<>();
                    while (rs4.next()){
                        int replyID = rs4.getInt("reply_id");
                        int stdID = rs4.getInt("student_id");
                        StudentActivityReply reply = new StudentActivityReply(stdID, replyID);

                        studentReply.add(reply);
                    }

                    ActivityPost activityPost = new ActivityPost(id, title, description, author_id, time_stamp, replies, studentReply);
                    return activityPost;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error finding posts" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Question getQuestion(int postID){
        String sql = "Select * from question where post_id = ?";
        String sql2 = "Select * from post where ID = ?";
        String sql3 = "Select * from answer where post_id = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, postID);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setInt(1, postID);
                ResultSet rs2 = pstmt2.executeQuery();
                if (rs2.next()) {
                    int id = rs.getInt("post_id");
                    int vote = rs.getInt("votes");
                    Timestamp time_stamp = rs2.getTimestamp("time_stamp");
                    String title = rs2.getString("title");
                    String description = rs2.getString("description");
                    int stdID = rs2.getInt("student_id");

                    PreparedStatement pstmt3 = conn.prepareStatement(sql3);
                    pstmt3.setInt(1, postID);
                    ResultSet rs3 = pstmt3.executeQuery();

                    ArrayList<Answer> answers = new ArrayList<>();
                    while(rs3.next()){
                        int p_id = rs3.getInt("post_id");
                        int studentID = rs3.getInt("student_id");
                        boolean isCorrect = rs3.getBoolean("marked_correct");
                        int votes = rs3.getInt("votes");
                        String text = rs3.getString("text");
                        Timestamp timeStamp = rs3.getTimestamp("time_stamp");

                        Answer ans = new Answer(p_id, studentID, timeStamp, text, votes, isCorrect);
                        answers.add(ans);
                    }

                    Question question = new Question(id, title, description, stdID, time_stamp, answers, vote);
                    return question;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error checking if Student Exists" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public SimplePost getSimplePost(int postID){
        String sql = "Select * from simple_post where post_id = ?";
        String sql2 = "Select * from post where ID = ?";
        String sql3 = "Select * from comment where post_id = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //Set parameters
            pstmt.setInt(1, postID);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setInt(1, postID);
                ResultSet rs2 = pstmt2.executeQuery();
                if (rs2.next()) {
                    int id = rs.getInt("post_id");
                    String url = rs.getString("picture_url");
                    int likes = rs.getInt("likes");
                    Timestamp time_stamp = rs2.getTimestamp("time_stamp");
                    String title = rs2.getString("title");
                    String description = rs2.getString("description");
                    int stdID = rs2.getInt("student_id");

                    PreparedStatement pstmt3 = conn.prepareStatement(sql3);
                    pstmt3.setInt(1, postID);
                    ResultSet rs3 = pstmt3.executeQuery();

                    ArrayList<Comment> comments = new ArrayList<>();
                    while(rs3.next()){
                        int p_id = rs3.getInt("post_id");
                        int studentID = rs3.getInt("student_id");
                        String text = rs3.getString("text");
                        int answerLikes = rs3.getInt("likes");
                        Timestamp timeStamp = rs3.getTimestamp("time_stamp");

                        Comment comment = new Comment(p_id, studentID, timeStamp, text, answerLikes);
                        comments.add(comment);
                    }

                    SimplePost simplePost = new SimplePost(id, title, description, stdID, time_stamp, url, likes, comments);
                    return simplePost;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error checking if Student Exists" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void createActivityPost(int studentID, String title, String description){
        String sql = "INSERT INTO Post (student_id, title, description, time_stamp) VALUES (?, ?, ?, ?)";
        String sql2 = "Select MAX(id) from post";

        try (Connection conn = dbConnector.getConnection()) {
             PreparedStatement pstmt = conn.prepareStatement(sql);

            // Set parameters for the prepared statement
            pstmt.setInt(1, studentID);
            pstmt.setString(1, title);
            pstmt.setString(1, description);
            pstmt.setTimestamp(2, Timestamp.from(Instant.now()));

            // Execute the insert
            pstmt.executeUpdate();

            //System.out.println("Student saved successfully.");

        } catch (SQLException e) {
            System.out.println("Error saving student: " + e.getMessage());
            e.printStackTrace();
        }


    }

}
