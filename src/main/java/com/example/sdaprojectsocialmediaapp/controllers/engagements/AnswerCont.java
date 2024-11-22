package com.example.sdaprojectsocialmediaapp.controllers.engagements;

import com.example.sdaprojectsocialmediaapp.models.engagements.Answer;
import com.example.sdaprojectsocialmediaapp.models.engagements.Comment;
import com.example.sdaprojectsocialmediaapp.models.posts.Question;
import com.example.sdaprojectsocialmediaapp.repository.PostRepository;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import com.example.sdaprojectsocialmediaapp.services.Session;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.util.Objects;

public class AnswerCont {

    StudentRepository studentRepo = new StudentRepository();
    PostRepository postRepo = new PostRepository();

    @FXML
    private AnchorPane pane;

    @FXML
    private Label answer;

    @FXML
    private Label answerAuthor;

    @FXML
    private Button markCorrect;

    @FXML
    private ImageView tickSign;

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

        this.markCorrect.setVisible(false);
        this.answer.setText(answer.getContent());
        this.answerAuthor.setText("By: " + studentRepo.getStudentByID(answer.getAuthorId()).getName());
        this.answerTimestamp.setText("Given On: " + answer.getDate());
        this.likeCount.setText(Integer.toString(answer.getUpVotes()));

        Question question = postRepo.getQuestion(answer.getPostId());
        if (question.getAuthorId() == Session.getSessionVariable().getId()) {
            markCorrect.setVisible(true);
        }
        if (answer.isCorrect()) {
            String filePath = Objects.requireNonNull(getClass().getResource("/images/tick.png")).toExternalForm();
            Image image = new Image(filePath);
            tickSign.setImage(image);
        }
        // arranging all the meta-data with proper spacing so that no overlapping occurs
        Platform.runLater(() -> {
            double answerHeight = this.answer.getBoundsInParent().getHeight();
            double y_coordinate = this.answer.getLayoutY() + answerHeight + 34.5;
            this.answerAuthor.setLayoutY(y_coordinate);
            this.answerTimestamp.setLayoutY(y_coordinate);
            this.likeBtn.setLayoutY(y_coordinate - 15.0);
            this.markCorrect.setLayoutY(y_coordinate - 15.0);
            this.tickSign.setLayoutY(y_coordinate - 15.0);
            this.likeCount.setLayoutY(y_coordinate - 13.0);
        });
    }

}
