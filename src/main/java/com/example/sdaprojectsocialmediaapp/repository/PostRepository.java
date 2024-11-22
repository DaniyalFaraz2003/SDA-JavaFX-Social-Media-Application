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

    public String getPostType(int postID) {
        String sql1 = "Select * from post where ID = ?";
        String sql2 = "Select * from simple_post where post_id = ?";
        String sql3 = "Select * from question where post_id = ?";
        String sql4 = "Select * from activity_post where post_id = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            //Set parameters
            pstmt1.setInt(1, postID);

            try (ResultSet rs1 = pstmt1.executeQuery()) {
                if (rs1.next()) {
                    PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                    pstmt2.setInt(1, postID);

                    try (ResultSet rs2 = pstmt2.executeQuery()) {
                        if (rs2.next()) {
                            return "Simple Post";
                        } else {
                            PreparedStatement pstmt3 = conn.prepareStatement(sql3);
                            pstmt3.setInt(1, postID);

                            try (ResultSet rs3 = pstmt3.executeQuery()) {
                                if (rs3.next()) {
                                    return "Question";
                                } else {
                                    PreparedStatement pstmt4 = conn.prepareStatement(sql4);
                                    pstmt4.setInt(1, postID);

                                    try (ResultSet rs4 = pstmt4.executeQuery()) {
                                        if (rs4.next()) {
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

    public ActivityPost getActivityPost(int postID) {
        String sql = "Select * from activity_post where post_id = ?";
        String sql2 = "Select * from post where ID = ?";
        String sql3 = "Select * from reply where post_id = ?";
        String sql4 = "Select * from Student_ActivityReply where post_id = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            PreparedStatement pstmt3 = conn.prepareStatement(sql3);

            pstmt.setInt(1, postID);
            pstmt2.setInt(1, postID);
            pstmt3.setInt(1, postID);
            ResultSet rs1 = pstmt.executeQuery();
            ResultSet rs2 = pstmt2.executeQuery();

            if (rs1.next()) {
                if (rs2.next()) {
                    int id = rs2.getInt("ID");
                    int author_id = rs2.getInt("student_id");
                    String title = rs2.getString("title");
                    String description = rs2.getString("description");
                    Timestamp time_stamp = rs2.getTimestamp("time_stamp");
                    ResultSet rs3 = pstmt3.executeQuery();

                    ArrayList<Reply> replies = new ArrayList<>();

                    while (rs3.next()) {
                        int r_id = rs3.getInt("id");
                        int p_id = rs3.getInt("post_id");
                        String text = rs3.getString("text");
                        Reply reply = new Reply(r_id, p_id, text);
                        replies.add(reply);
                    }
                    PreparedStatement pstmt4 = conn.prepareStatement(sql4);
                    pstmt4.setInt(1, postID);
                    ResultSet rs4 = pstmt4.executeQuery();

                    ArrayList<StudentActivityReply> studentReply = new ArrayList<>();
                    while (rs4.next()) {
                        int replyID = rs4.getInt("reply_id");
                        int stdID = rs4.getInt("student_id");
                        Timestamp timeStamp = rs4.getTimestamp("time_stamp");
                        StudentActivityReply reply = new StudentActivityReply(stdID, replyID, postID, timeStamp);

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

    public Question getQuestion(int postID) {
        String sql = "Select * from question where post_id = ?";
        String sql2 = "Select * from post where ID = ?";
        String sql3 = "Select * from answer where post_id = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, postID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
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
                    while (rs3.next()) {
                        int ans_id = rs3.getInt("id");
                        int p_id = rs3.getInt("post_id");
                        int studentID = rs3.getInt("student_id");
                        boolean isCorrect = rs3.getBoolean("marked_correct");
                        int votes = rs3.getInt("votes");
                        String text = rs3.getString("text");
                        Timestamp timeStamp = rs3.getTimestamp("time_stamp");

                        Answer ans = new Answer(ans_id, p_id, studentID, timeStamp, text, votes, isCorrect);
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

    public SimplePost getSimplePost(int postID) {
        String sql = "Select * from simple_post where post_id = ?";
        String sql2 = "Select * from post where ID = ?";
        String sql3 = "Select * from comment where post_id = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //Set parameters
            pstmt.setInt(1, postID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
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
                    while (rs3.next()) {
                        int commentID = rs3.getInt("id");
                        int p_id = rs3.getInt("post_id");
                        int studentID = rs3.getInt("student_id");
                        String text = rs3.getString("text");
                        int answerLikes = rs3.getInt("likes");
                        Timestamp timeStamp = rs3.getTimestamp("time_stamp");

                        Comment comment = new Comment(p_id, studentID, timeStamp, text, answerLikes);
                        comment.setCommentID(commentID);
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

    public int createActivityPost(int studentID, String title, String description, ArrayList<Reply> replies) {
        String sql = "INSERT INTO Post (student_id, title, description, time_stamp) VALUES (?, ?, ?, ?)";
        String sql2 = "Select MAX(ID) as maxID from post";
        String sql3 = "Insert into Activity_Post (post_id) Values (?)";
        String sql4 = "Insert into Reply (post_id, text) VALUES (?, ?)";
        int newPostId = 0;

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            PreparedStatement pstmt3 = conn.prepareStatement(sql3);
            PreparedStatement pstmt4 = conn.prepareStatement(sql4);

            // Set parameters for the prepared statement
            pstmt.setInt(1, studentID);
            pstmt.setString(2, title);
            pstmt.setString(3, description);
            pstmt.setTimestamp(4, Timestamp.from(Instant.now()));

            // Execute the insert
            pstmt.executeUpdate();
            System.out.println("Post saved successfully.");

            ResultSet rs = pstmt2.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("maxID");
                newPostId = id;
                pstmt3.setInt(1, id);

                pstmt3.executeUpdate();
                System.out.println("Activity Post saved successfully.");

                for (Reply reply : replies) {
                    pstmt4.setInt(1, newPostId);
                    pstmt4.setString(2, reply.getText());
                    pstmt4.executeUpdate();
                }

                System.out.println("Replies saved successfully.");
            }

        } catch (SQLException e) {
            System.out.println("Error saving Activity Post: " + e.getMessage());
            e.printStackTrace();
        }

        return newPostId;
    }

    public int createSimplePost(int studentID, String title, String description, String url, int numLikes) {
        String sql = "INSERT INTO Post (student_id, title, description, time_stamp) VALUES (?, ?, ?, ?)";
        String sql2 = "Select MAX(ID) as maxID from post";
        String sql3 = "Insert into Simple_Post (post_id, picture_url, likes) Values (?, ?, ?)";
        int newPostId = 0;

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            PreparedStatement pstmt3 = conn.prepareStatement(sql3);

            pstmt.setInt(1, studentID);
            pstmt.setString(2, title);
            pstmt.setString(3, description);
            pstmt.setTimestamp(4, Timestamp.from(Instant.now()));

            // Execute the insert
            pstmt.executeUpdate();
            System.out.println("Post saved successfully.");

            ResultSet rs = pstmt2.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("maxID");
                newPostId = id;
                pstmt3.setInt(1, id);
                pstmt3.setString(2, url);
                pstmt3.setInt(3, numLikes);

                pstmt3.executeUpdate();
                System.out.println("Simple Post saved successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error saving Simple Post: " + e.getMessage());
            e.printStackTrace();
        }

        return newPostId;
    }

    public int createQuestion(int studentID, String title, String description, int votes) {
        String sql = "INSERT INTO Post (student_id, title, description, time_stamp) VALUES (?, ?, ?, ?)";
        String sql2 = "Select MAX(ID) as maxID from post";
        String sql3 = "Insert into Question (post_id, votes) Values (?, ?)";
        int newPostId = 0;


        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            PreparedStatement pstmt3 = conn.prepareStatement(sql3);

            pstmt.setInt(1, studentID);
            pstmt.setString(2, title);
            pstmt.setString(3, description);
            pstmt.setTimestamp(4, Timestamp.from(Instant.now()));

            // Execute the insert
            pstmt.executeUpdate();
            System.out.println("Post saved successfully.");

            ResultSet rs = pstmt2.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("maxID");
                newPostId = id;
                pstmt3.setInt(1, id);
                pstmt3.setInt(2, votes);

                pstmt3.executeUpdate();
                System.out.println("Question saved successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error saving Question: " + e.getMessage());
            e.printStackTrace();
        }

        return newPostId;
    }

    public ArrayList<ActivityPost> getAllActivityPosts() {
        String sql = "SELECT * FROM Activity_Post";
        ArrayList<ActivityPost> activityPosts = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int postID = rs.getInt("post_id");
                ActivityPost post = this.getActivityPost(postID);

                if (post != null) {
                    activityPosts.add(post);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting Activity Post: " + e.getMessage());
            e.printStackTrace();
        }

        if (!activityPosts.isEmpty()) {
            return activityPosts;
        }

        return null;
    }

    public ArrayList<SimplePost> getAllSimplePosts() {
        String sql = "SELECT * FROM Simple_Post";
        ArrayList<SimplePost> simplePosts = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int postID = rs.getInt("post_id");
                SimplePost post = this.getSimplePost(postID);

                if (post != null) {
                    simplePosts.add(post);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting Simple Post: " + e.getMessage());
            e.printStackTrace();
        }

        if (!simplePosts.isEmpty()) {
            return simplePosts;
        }

        return null;
    }

    public ArrayList<Question> getAllQuestions() {
        String sql = "SELECT * FROM Question";
        ArrayList<Question> questions = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int postID = rs.getInt("post_id");
                Question post = this.getQuestion(postID);

                if (post != null) {
                    questions.add(post);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting Question Post: " + e.getMessage());
            e.printStackTrace();
        }

        if (!questions.isEmpty()) {
            return questions;
        }

        return null;
    }

    public String getReplyFromId(int replyId) {
        String sql = "Select text from reply where id = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, replyId);
            ResultSet rs1 = pstmt.executeQuery();

            if (rs1.next()) {
                String text = rs1.getString("text");
                return text;
            }

        } catch (SQLException e) {
            System.out.println("Error finding posts" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<SimplePost> getSimplePostByStudentID(int studentID) {
        String sql = "Select * from Post where student_id = ?";
        ArrayList<SimplePost> simplePosts = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, studentID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int postID = rs.getInt("ID");
                if (this.getPostType(postID).equals("Simple Post")) {
                    SimplePost post = this.getSimplePost(postID);
                    simplePosts.add(post);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding posts" + e.getMessage());
            e.printStackTrace();
        }

        if (!simplePosts.isEmpty()) {
            return simplePosts;
        }

        return null;
    }

    public ArrayList<ActivityPost> getActivityPostByStudentID(int studentID) {
        String sql = "Select * from Post where student_id = ?";
        ArrayList<ActivityPost> activityPosts = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, studentID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int postID = rs.getInt("ID");
                if (this.getPostType(postID).equals("Activity Post")) {
                    ActivityPost post = this.getActivityPost(postID);
                    activityPosts.add(post);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding posts" + e.getMessage());
            e.printStackTrace();
        }

        if (!activityPosts.isEmpty()) {
            return activityPosts;
        }

        return null;
    }

    public ArrayList<Question> getQuestionByStudentID(int studentID) {
        String sql = "Select * from Post where student_id = ?";
        ArrayList<Question> questions = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, studentID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int postID = rs.getInt("ID");
                if (this.getPostType(postID).equals("Question")) {
                    Question post = this.getQuestion(postID);
                    questions.add(post);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding posts" + e.getMessage());
            e.printStackTrace();
        }

        if (!questions.isEmpty()) {
            return questions;
        }

        return null;
    }

    public void deleteSimplePost(int postID) {
        String sql1 = "Select * from Simple_Post where post_id = ?";
        String sql2 = "Delete from Comment where post_id = ?";
        String sql3 = "Delete from Simple_Post where post_id = ?";
        String sql4 = "Delete from Post where ID = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setInt(1, postID);
            ResultSet rs1 = pstmt1.executeQuery();

            if (rs1.next()) {
                PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setInt(1, postID);
                pstmt2.executeUpdate();

                PreparedStatement pstmt3 = conn.prepareStatement(sql3);
                pstmt3.setInt(1, postID);
                pstmt3.executeUpdate();

                PreparedStatement pstmt4 = conn.prepareStatement(sql4);
                pstmt4.setInt(1, postID);
                pstmt4.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error deleting post" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteQuestion(int postID) {
        String sql1 = "Select * from Question where post_id = ?";
        String sql2 = "Delete from Answer where post_id = ?";
        String sql3 = "Delete from Question where post_id = ?";
        String sql4 = "Delete from Post where ID = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setInt(1, postID);
            ResultSet rs1 = pstmt1.executeQuery();

            if (rs1.next()) {
                PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setInt(1, postID);
                pstmt2.executeUpdate();

                PreparedStatement pstmt3 = conn.prepareStatement(sql3);
                pstmt3.setInt(1, postID);
                pstmt3.executeUpdate();

                PreparedStatement pstmt4 = conn.prepareStatement(sql4);
                pstmt4.setInt(1, postID);
                pstmt4.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error deleting question" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteActivityPost(int postID) {
        String sql1 = "Select * from Activity_Post where post_id = ?";
        String sql2 = "Delete from Student_ActivityReply where post_id = ?";
        String sql3 = "Delete from Reply where post_id = ?";
        String sql4 = "Delete from Activity_Post where post_id = ?";
        String sql5 = "Delete from Post where ID = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setInt(1, postID);
            ResultSet rs1 = pstmt1.executeQuery();

            if (rs1.next()) {
                PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setInt(1, postID);
                pstmt2.executeUpdate();

                PreparedStatement pstmt3 = conn.prepareStatement(sql3);
                pstmt3.setInt(1, postID);
                pstmt3.executeUpdate();

                PreparedStatement pstmt4 = conn.prepareStatement(sql4);
                pstmt4.setInt(1, postID);
                pstmt4.executeUpdate();

                PreparedStatement pstmt5 = conn.prepareStatement(sql5);
                pstmt5.setInt(1, postID);
                pstmt5.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error deleting question" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateSimplePost(SimplePost post) {
        String sql1 = "Update Post set title = ?, description = ?, time_stamp = ? where ID = ?";
        String sql2 = "Update Simple_Post set picture_url = ? where post_id = ?";

        int id = post.getId();
        String title = post.getTitle();
        String description = post.getDescription();
        Timestamp timeStamp = Timestamp.valueOf(post.getDate());
        String url = post.getPostImageUrl();

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setString(1, title);
            pstmt1.setString(2, description);
            pstmt1.setTimestamp(3, timeStamp);
            pstmt1.setInt(4, id);
            pstmt1.executeUpdate();

            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1, url);
            pstmt2.setInt(2, id);
            pstmt2.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error Updating Simple Post" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateQuestion(Question post) {
        String sql = "Update Post set title = ?, description = ?, time_stamp = ? where ID = ?";

        int id = post.getId();
        String title = post.getTitle();
        String description = post.getDescription();
        Timestamp timeStamp = Timestamp.valueOf(post.getDate());

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setTimestamp(3, timeStamp);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error Updating Question" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateActivityPost(ActivityPost post) {
        String sql1 = "Update Post set title = ?, description = ?, time_stamp = ? where ID = ?";
        String sql2 = "Update Reply set text = ? where ID = ?";

        int id = post.getId();
        String title = post.getTitle();
        String description = post.getDescription();
        Timestamp timeStamp = Timestamp.valueOf(post.getDate());

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setTimestamp(3, timeStamp);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();

            ArrayList<Reply> replies = post.getReplies();

            PreparedStatement pstmt1 = conn.prepareStatement(sql2);

            for (Reply reply : replies) {
                pstmt1.setString(1, reply.getText());
                pstmt1.setInt(2, reply.getId());
                pstmt1.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error Updating Activity Post" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void reactOnSimplePost(int postID, int likes) {
        String sql1 = "Update Simple_Post set likes = ? where post_id = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql1);
            pstmt.setInt(1, likes);
            pstmt.setInt(2, postID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error Reacting on Simple Post" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void voteOnQuestion(int postID, int votes) {
        String sql = "Update Question set votes = ? where post_id = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, votes);
            pstmt.setInt(2, postID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error Reacting on Question" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void likeComment(int commentID, int likes) {
        String sql = "Update Comment set likes = ? where ID = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, likes);
            pstmt.setInt(2, commentID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error Reacting on Comment" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void voteOnAnswer(int answerID, int votes) {
        String sql = "Update Answer set votes = ? where ID = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, votes);
            pstmt.setInt(2, answerID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error Reacting on Answer" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void markAnswerCorrect(int answerID) {
        String sql = "Update Answer set marked_correct = true where ID = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, answerID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error Marking Correct" + e.getMessage());
            e.printStackTrace();
        }
    }

    public int commentOnPost(int postID, int studentID, String text) {
        String sql = "Insert into Comment (post_id, student_id, text, likes, time_stamp) values (?, ?, ?, ?, ?)";
        int commentID = -1;

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, postID);
            pstmt.setInt(2, studentID);
            pstmt.setString(3, text);
            pstmt.setInt(4, 0);
            pstmt.setTimestamp(5, Timestamp.from(Instant.now()));
            pstmt.executeUpdate();

            // Retrieve generated keys
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                commentID = rs.getInt(1); // Get the auto-generated comment ID
            }
            System.out.println("Comment Added");
        } catch (SQLException e) {
            System.out.println("Error Adding Comment" + e.getMessage());
            e.printStackTrace();
        }
        return commentID;
    }

    public int addAnswer(int postID, int studentID, String text) {
        String sql = "Insert into Answer (post_id, student_id, marked_correct, votes, text, time_stamp) values (?, ?, ?, ?, ?, ?)";
        int answerId = -1;

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, postID);
            pstmt.setInt(2, studentID);
            pstmt.setBoolean(3, false);
            pstmt.setInt(4, 0);
            pstmt.setString(5, text);
            pstmt.setTimestamp(6, Timestamp.from(Instant.now()));
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                answerId = rs.getInt(1); // Get the auto-generated comment ID
            }

            System.out.println("Answer Added");
        } catch (SQLException e) {
            System.out.println("Error Adding Answer" + e.getMessage());
            e.printStackTrace();
        }

        return answerId;
    }

    public void addStudentActivityReply(int replyID, int postID, int studentID) {
        String sql = "Insert into Student_ActivityReply (reply_id, post_id, student_id, time_stamp) values (?, ?, ?, ?)";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, replyID);
            pstmt.setInt(2, postID);
            pstmt.setInt(3, studentID);
            pstmt.setTimestamp(4, Timestamp.from(Instant.now()));
            pstmt.executeUpdate();
            System.out.println("Student Activity Reply Added");
        } catch (SQLException e) {
            System.out.println("Error Adding Student Activity Reply" + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean checkUniqueStudentReply(int postID, int studentID) {
        String sql = "Select * from Student_ActivityReply where post_id = ? and student_id = ?";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, postID);
            pstmt.setInt(2, studentID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error Checking Unique Reply" + e.getMessage());
            e.printStackTrace();
        }

        return true;
    }

}

