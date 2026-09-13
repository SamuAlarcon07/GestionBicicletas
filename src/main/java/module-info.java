module co.edu.uniquindio.gestionbicicletas.gestionbicicletas {

    requires javafx.controls;
    requires javafx.fxml;

    // JUnit 5
    requires org.junit.jupiter.api;

    exports com.gestionbicicletas;
    exports com.gestionbicicletas.controller;

    opens com.gestionbicicletas.controller to javafx.fxml;

    // Permite a JUnit acceder a las clases de prueba mediante reflexión
    opens com.gestionbicicletas.model to org.junit.platform.commons;
}