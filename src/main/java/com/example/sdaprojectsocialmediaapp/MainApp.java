package com.example.sdaprojectsocialmediaapp;
import com.example.sdaprojectsocialmediaapp.models.HomePageApp;
import com.example.sdaprojectsocialmediaapp.models.RegisterApp;

import com.example.sdaprojectsocialmediaapp.utils.DatabaseConnector;
import javafx.application.Application;

public class MainApp {
    public static void main(String[] args) {
        DatabaseConnector conn = new DatabaseConnector();
        conn.getConnection();
        Application.launch(RegisterApp.class, args);
    }
}
