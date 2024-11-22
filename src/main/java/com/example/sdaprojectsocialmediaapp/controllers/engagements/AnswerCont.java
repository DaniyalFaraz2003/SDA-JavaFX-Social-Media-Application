package com.example.sdaprojectsocialmediaapp.controllers.engagements;

import com.example.sdaprojectsocialmediaapp.models.engagements.Answer;
import com.example.sdaprojectsocialmediaapp.models.engagements.Comment;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

public class AnswerCont {

    StudentRepository studentRepo = new StudentRepository();

    @FXML
    private Label answer;

    @FXML
    private Label answerAuthor;

    @FXML
    private Label answerTimestamp;

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
    public void initializeAnswer(Answer answer) {
        this.answer.setText(answer.getContent());
        this.answerAuthor.setText("By: " + studentRepo.getStudentByID(answer.getAuthorId()).getName());
        this.answerTimestamp.setText("Given On: " + answer.getDate());
        this.likeCount.setText(Integer.toString(answer.getUpVotes()));
        // arranging all the meta-data with proper spacing so that no overlapping occurs
        Platform.runLater(() -> {
            double answerHeight = this.answer.getBoundsInParent().getHeight();
            double y_coordinate = this.answer.getLayoutY() + answerHeight + 34.5;
            this.answerAuthor.setLayoutY(y_coordinate);
            this.answerTimestamp.setLayoutY(y_coordinate);
            this.likeBtn.setLayoutY(y_coordinate - 15.0);
            this.likeCount.setLayoutY(y_coordinate - 13.0);
        });
    }

}
