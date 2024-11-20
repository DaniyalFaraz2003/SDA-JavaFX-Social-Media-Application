package com.example.sdaprojectsocialmediaapp.controllers;

import com.example.sdaprojectsocialmediaapp.Router;
import com.example.sdaprojectsocialmediaapp.repository.StudentRepository;
import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import com.example.sdaprojectsocialmediaapp.models.Student;

import java.io.IOException;

public class ViewProfileCont extends MainController {

    @FXML
    private Label email;

    @FXML
    private Label name;

    @FXML
    private Label phone;

    @FXML
    private Label username;

    @FXML
    void openRequestsPage(MouseEvent event) throws IOException {
        Router.navigateTo("Friend Request Page");
    }

    @FXML
    public void initializePage(String username) {
        StudentRepository repo = new StudentRepository();
        Student student  = repo.getStudentbyUsername(username);
        name.setText(student.getName());
        phone.setText(student.getPhone());
        email.setText(student.getEmail());
        this.username.setText(username);
    }

}
