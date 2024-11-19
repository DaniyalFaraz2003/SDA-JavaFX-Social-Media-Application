package com.example.sdaprojectsocialmediaapp.controllers.posts;

import com.example.sdaprojectsocialmediaapp.Router;
import com.example.sdaprojectsocialmediaapp.controllers.MainController;
import com.example.sdaprojectsocialmediaapp.controllers.engagements.AnswerCont;
import com.example.sdaprojectsocialmediaapp.models.engagements.Answer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Timestamp;


public class QuestionCont extends MainController {
    @FXML
    private Pane pane;

    @FXML
    private Label postType;

    @FXML
    private Label votes;

    @FXML
    private TextField answerBox;

    @FXML
    private VBox answers;

    @FXML
    private ToggleButton voteButton;

    @FXML
    public String getPostType() {
        return postType.getText();
    }

    @FXML
    void handleUpVote(MouseEvent event) {
        int votes = Integer.parseInt(this.votes.getText());
        if (voteButton.isSelected()) {
            this.votes.setText(Integer.toString(votes + 1));
        }
        else
            this.votes.setText(Integer.toString(votes - 1));
    }

    @FXML
    void handleAnswer(MouseEvent event) throws IOException {
        String answerString = answerBox.getText();
        answerBox.setText("");
        Answer answer = new Answer(0, 1, new Timestamp(System.currentTimeMillis()), answerString, 10, true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/question/answer.fxml"));
        Pane answerPane = loader.load();
        AnswerCont controller = loader.getController();
        controller.initializeAnswer(answer);
        answers.getChildren().add(answerPane);
    }

    @FXML
    void createNewQuestion(MouseEvent event) throws IOException {
        // Routing to the post creation page
        Router.navigateTo("Question Form");
    }

    @FXML
    void submit(MouseEvent event) throws IOException {
        // Create an activity post object

        // Insert post data to database

        // Return back to Activity posts page
        Router.navigateTo("Question Page");
    }

    @FXML
    public void initializePost() {
        // Fetch data to populate post
    }

    @FXML
    public void initializePage() {
        // Fetch data to populate page
    }

    @FXML
    public void initializeForm() {
        // Fetch data to populate form
    }
}
