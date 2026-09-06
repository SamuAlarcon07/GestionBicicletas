package com.gestionbicicletas.controller;

import com.gestionbicicletas.service.GestionTaller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    private GestionTaller gestionTaller;

    public void setGestionTaller(GestionTaller gestionTaller) {
        this.gestionTaller = gestionTaller;
    }

    @FXML
    private void abrirClientes() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/gestionbicicletas/clientes.fxml")
        );

        Parent root = loader.load();

        ClienteController controller = loader.getController();
        controller.setGestionTaller(gestionTaller);

        Stage stage = new Stage();
        stage.setTitle("Gestión de Clientes");
        stage.setScene(new Scene(root, 1000, 650));
        stage.show();
    }
}