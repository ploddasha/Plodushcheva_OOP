module com.example.task231 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.task231 to javafx.fxml;
    exports com.example.task231;
}