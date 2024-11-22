package com.example.sdaprojectsocialmediaapp.controllers.posts;

import com.example.sdaprojectsocialmediaapp.Router;
import com.example.sdaprojectsocialmediaapp.controllers.MainController;
import com.example.sdaprojectsocialmediaapp.controllers.engagements.AnswerCont;
import com.example.sdaprojectsocialmediaapp.models.engagements.Answer;
import com.example.sdaprojectsocialmediaapp.models.posts.Question;
import com.example.sdaprojectsocialmediaapp.repository.PostRepository;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import com.example.sdaprojectsocialmediaapp.services.Session;
import com.example.sdaprojectsocialmediaapp.utils.Validate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;


public class QuestionCont extends MainController {
    private StudentRepository studentRepository = new StudentRepository();
    private PostRepository postRepository = new PostRepository();
    private int id;

    @FXML
    private TextField answerBox;

    @FXML
    private VBox answers;

    @FXML
    private Label errorText;

    @FXML
    private VBox container;

    @FXML
    private Label authorName;

    @FXML
    private Label description;

    @FXML
    private Pane pane;

    @FXML
    private Label postType;

    @FXML
    private Label timestamp;

    @FXML
    private Label title;

    @FXML
    private ToggleButton voteButton;

    @FXML
    private Label votes;

    @FXML
    private Button updateBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button submit;

    @FXML
    private TextField postTitle;

    @FXML
    private TextArea postContent;

    @FXML
    private Label warning;

    @FXML
    public String getPostType() {
        return postType.getText();
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

    //int studentID, String title, String description, int votes
    @FXML
    void submit(MouseEvent event) throws IOException {
        // Create an activity post object
        if (Validate.isValidPostTitle(postTitle.getText())) {
            if (Validate.isValidPostDescription(postContent.getText())) {
                postRepository.createQuestion(Session.getSessionVariable().getId(), postTitle.getText().trim(), postContent.getText().trim(), 0);
                Router.navigateTo("Question Page");
            } else {
                errorText.setText("Invalid Post Description");
            }
        } else {
            errorText.setText("Invalid Post Title");
        }


    }

    @FXML
    public void initializePost(Question question, boolean isHomepage, Stage stage) {
        // Fetch data to populate post
        this.id = question.getId();
        this.title.setText(question.getTitle());
        this.description.setText(question.getDescription());
        this.timestamp.setText("Posted On: " + question.getDate());
        this.authorName.setText("By: " + studentRepository.getStudentByID(question.getAuthorId()).getName());
        this.votes.setText(Integer.toString(question.getUpVotes()));
        this.voteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            int votes = Integer.parseInt(this.votes.getText());
            if (voteButton.isSelected()) {
                this.votes.setText(Integer.toString(votes + 1));
                postRepository.voteOnQuestion(id, votes + 1);
            }
            else {
                this.votes.setText(Integer.toString(votes - 1));
                postRepository.voteOnQuestion(id, votes - 1);
            }
        });
        if (Session.getSessionVariable().getId() != question.getAuthorId() || isHomepage) {
            this.updateBtn.setVisible(false);
            this.deleteBtn.setVisible(false);
        } else {
            this.updateBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/question/question_form.fxml"));
                try {
                    Parent root = loader.load();
                    QuestionCont controller = loader.getController();
                    controller.initializeForm(question);
                    stage.setScene(new Scene(root));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            this.deleteBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                PostRepository postRepository = new PostRepository();
                postRepository.deleteQuestion(id);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/question/question_page.fxml"));
                try {
                    Parent root = loader.load();
                    QuestionCont controller = loader.getController();
                    controller.initializePage(stage);
                    stage.setScene(new Scene(root));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        try {
            for (Answer answer : question.getAnswers()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/question/answer.fxml"));
                Pane commentPane = loader.load();
                AnswerCont controller = loader.getController();
                controller.initializeAnswer(answer);
                this.answers.getChildren().add(commentPane);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initializePage(Stage stage) throws IOException {
        PostRepository postRepository = new PostRepository();
        ArrayList<Question> questions = postRepository.getQuestionByStudentID(Session.getSessionVariable().getId());
        if (questions == null)
            warning.setVisible(true);
        else if (container != null) {
            container.getChildren().clear();
            for (Question question : questions) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/question/question.fxml"));
                pane = loader.load();
                QuestionCont controller = loader.getController();
                controller.initializePost(question, false, stage);
                container.getChildren().add(pane);
            }
        }
    }

    @FXML
    public void initializeForm(Question question) {
        if (question != null) {
            this.submit.setText("Update");
            this.postTitle.setText(question.getTitle());
            this.postContent.setText(question.getDescription());
            submit.removeEventHandler(MouseEvent.MOUSE_CLICKED, submit.getOnMouseClicked());
            submit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                question.setTitle(postTitle.getText());
                question.setDescription(postContent.getText());
                question.setDate(new Timestamp(System.currentTimeMillis()));
                PostRepository postRepository = new PostRepository();
                postRepository.updateQuestion(question);
                try {
                    Router.navigateTo("Question Page");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
