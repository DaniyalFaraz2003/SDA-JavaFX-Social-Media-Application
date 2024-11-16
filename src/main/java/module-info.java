module com.example.sdaprojectsocialmediaapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.example.sdaprojectsocialmediaapp to javafx.fxml;
    opens com.example.sdaprojectsocialmediaapp.controllers to javafx.fxml;
    exports com.example.sdaprojectsocialmediaapp;
    exports com.example.sdaprojectsocialmediaapp.models;
    opens com.example.sdaprojectsocialmediaapp.controllers.friend_request to javafx.fxml;

}