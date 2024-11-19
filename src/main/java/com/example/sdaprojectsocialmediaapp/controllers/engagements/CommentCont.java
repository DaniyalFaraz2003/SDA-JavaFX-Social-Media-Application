package com.example.sdaprojectsocialmediaapp.controllers.engagements;

import com.example.sdaprojectsocialmediaapp.models.engagements.Comment;
import com.example.sdaprojectsocialmediaapp.services.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

public class CommentCont {
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
    void handleLike(MouseEvent event) {
        int likes = Integer.parseInt(this.likeCount.getText());
        if (this.likeBtn.isSelected())
            this.likeCount.setText(Integer.toString(likes + 1));
        else
            this.likeCount.setText(Integer.toString(likes - 1));
    }

    @FXML
    public void initializeComment(Comment comment) {
        this.comment.setText(comment.getContent());
        this.commentAuthor.setText(Session.getSessionVariable().getName());
        this.commentTimestamp.setText(comment.getDate());
        this.likeCount.setText(Integer.toString(comment.getLikes()));
    }
}
