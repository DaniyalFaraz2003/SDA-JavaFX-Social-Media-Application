module com.example.sdaprojectsocialmediaapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires jdk.compiler;
    requires jdk.jfr;


    exports com.example.sdaprojectsocialmediaapp;
    opens com.example.sdaprojectsocialmediaapp to javafx.fxml;
    opens com.example.sdaprojectsocialmediaapp.controllers to javafx.fxml;
    opens com.example.sdaprojectsocialmediaapp.controllers.friend_request to javafx.fxml;
    opens com.example.sdaprojectsocialmediaapp.controllers.posts to javafx.fxml;
    opens com.example.sdaprojectsocialmediaapp.controllers.friends to javafx.fxml;
    opens com.example.sdaprojectsocialmediaapp.controllers.engagements to javafx.fxml;

}