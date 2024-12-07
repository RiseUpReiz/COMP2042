module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    exports com.example.demo.controller;
    opens com.example.demo.levels to javafx.fxml;
    opens com.example.demo.displays to javafx.fxml;
    opens com.example.demo.planes to javafx.fxml;
    opens com.example.demo.projectiles to javafx.fxml;
    opens com.example.demo.menu to javafx.fxml;
    opens com.example.demo.controller to javafx.fxml;
}