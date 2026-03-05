module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    requires java.sql;

    // Exports
    exports com.example.demo1;
    exports person;
    exports person.model to javafx.base, javafx.controls;
    exports person.view;
    exports person.controller;
    exports person.model.base;
    exports person.model.utility;

    // Opens
    opens com.example.demo1 to javafx.fxml;
    opens person.model to javafx.base;
    opens person.view to javafx.fxml;
    //opens person.model to javafx.base;  // Changed from 'model' to 'person.model'
}