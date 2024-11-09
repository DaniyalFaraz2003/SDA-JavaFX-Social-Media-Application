module com.example.sdaprojectsocialmediaapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.sdaprojectsocialmediaapp to javafx.fxml;
    opens com.example.sdaprojectsocialmediaapp.controllers to javafx.fxml;
    exports com.example.sdaprojectsocialmediaapp.models;
}