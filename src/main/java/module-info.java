module com.example.projectopi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projectopi to javafx.fxml;
    exports com.example.projectopi;
}