module com.appacademia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;
    requires java.desktop;
    requires cloudinary.core;
    requires javafx.base;

    opens com.appacademia to javafx.fxml;
    opens com.appacademia.controller to javafx.fxml;
    opens com.appacademia.model to javafx.fxml;

    exports com.appacademia;
    exports com.appacademia.controller;
    exports com.appacademia.model;
}
