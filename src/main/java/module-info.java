module co.edu.uniquindio.gestionbicicletas.gestionbicicletas {

    requires javafx.controls;
    requires javafx.fxml;

    exports com.gestionbicicletas;
    exports com.gestionbicicletas.controller;

    opens com.gestionbicicletas.controller to javafx.fxml;
}