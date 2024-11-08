module com.example.sdaprojectsocialmediaapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sdaprojectsocialmediaapp to javafx.fxml;
//    exports com.example.sdaprojectsocialmediaapp.Frontend;
    exports com.example.sdaprojectsocialmediaapp.models;
}