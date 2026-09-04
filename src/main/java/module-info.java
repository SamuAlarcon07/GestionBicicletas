module co.edu.uniquindio.gestionbicicletas.gestionbicicletas {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.gestionbicicletas.gestionbicicletas to javafx.fxml;
    exports co.edu.uniquindio.gestionbicicletas.gestionbicicletas;
}