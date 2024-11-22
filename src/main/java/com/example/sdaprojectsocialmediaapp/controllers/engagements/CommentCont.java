package com.example.sdaprojectsocialmediaapp.controllers.engagements;

import com.example.sdaprojectsocialmediaapp.models.engagements.Comment;
import com.example.sdaprojectsocialmediaapp.repository.PostRepository;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import com.example.sdaprojectsocialmediaapp.services.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

public class CommentCont {
    StudentRepository studentRepo = new StudentRepository();
    PostRepository postRepo = new PostRepository();

    @FXML
    private Label comment;

    @FXML
    private Label commentAuthor;

    @FXML
    private Label commentTimestamp;

    @FXML
    private ToggleButton likeBtn;

    @FXML
    private Label likeCount;

    @FXML
    public void initializeComment(Comment comment) {
        this.comment.setText(comment.getContent());
        this.commentAuthor.setText(studentRepo.getStudentByID(comment.getAuthorId()).getName());
        this.commentTimestamp.setText(comment.getDate());
        this.likeCount.setText(Integer.toString(comment.getLikes()));
        this.likeBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            int likes = Integer.parseInt(this.likeCount.getText());
            if (this.likeBtn.isSelected()) {
                this.likeCount.setText(Integer.toString(likes + 1));
                postRepo.likeComment(comment.getCommentID(), likes + 1);
            }
            else {
                this.likeCount.setText(Integer.toString(likes - 1));
                postRepo.likeComment(comment.getCommentID(), likes - 1);
            }

        });
    }
}
