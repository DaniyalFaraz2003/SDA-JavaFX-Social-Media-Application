package com.example.sdaprojectsocialmediaapp.controllers.engagements;

import com.example.sdaprojectsocialmediaapp.models.engagements.StudentActivityReply;
import com.example.sdaprojectsocialmediaapp.repository.PostRepository;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import com.example.sdaprojectsocialmediaapp.services.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReplyCont {

    PostRepository postRepository = new PostRepository();
    StudentRepository studentRepository = new StudentRepository();

    @FXML
    private Label reply;

    @FXML
    private Label replyAuthor;

    @FXML
    private Label replyTimestamp;

    @FXML
    public void initializeReply(StudentActivityReply reply) {
        this.reply.setText(postRepository.getReplyFromId(reply.getReplyID()));
        this.replyAuthor.setText(studentRepository.getStudentByID(reply.getStdID()).getName());
        this.replyTimestamp.setText(reply.getTimestamp());
    }

}
