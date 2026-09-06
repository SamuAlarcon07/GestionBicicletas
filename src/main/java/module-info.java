module co.edu.uniquindio.gestionbicicletas.gestionbicicletas {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gestionbicicletas to javafx.fxml;
    exports com.gestionbicicletas;
}