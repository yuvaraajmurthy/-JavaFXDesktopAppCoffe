module com.example.project4_213 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project4_213 to javafx.fxml;
    exports com.example.project4_213;
}