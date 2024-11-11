package com.example.sdaprojectsocialmediaapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class HomepageCont {
    @FXML
    private Stage stage;

    @FXML
    private ComboBox<String> comboBox;

    public void setComboBox() {
        this.comboBox.getItems().add("Date");
        this.comboBox.getItems().add("Reactions");
        this.comboBox.getItems().add("Comments");
        this.comboBox.getItems().add("Answers");
    }

    @FXML
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
