package com.gestionbicicletas.controller;

import com.gestionbicicletas.service.GestionTaller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.gestionbicicletas.controller.OrdenServicioController;

import com.gestionbicicletas.controller.BicicletaController;

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

    @FXML
    private void abrirBicicletas() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/com/gestionbicicletas/bicicletas.fxml"
                )
        );

        Parent root = loader.load();

        BicicletaController controller = loader.getController();
        controller.setGestionTaller(gestionTaller);

        Stage stage = new Stage();
        stage.setTitle("Gestión de Bicicletas");
        stage.setScene(new Scene(root, 1100, 650));
        stage.show();
    }

    @FXML
    private void abrirMecanicos() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/com/gestionbicicletas/mecanicos.fxml"
                )
        );

        Parent root = loader.load();

        MecanicoController controller = loader.getController();
        controller.setGestionTaller(gestionTaller);

        Stage stage = new Stage();
        stage.setTitle("Gestión de Mecánicos");
        stage.setScene(new Scene(root, 950, 600));
        stage.show();
    }

    @FXML
    private void abrirOrdenes() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/com/gestionbicicletas/ordenes.fxml"
                )
        );

        Parent root = loader.load();

        OrdenServicioController controller =
                loader.getController();

        controller.setGestionTaller(gestionTaller);

        Stage stage = new Stage();
        stage.setTitle("Órdenes de Servicio");
        stage.setScene(new Scene(root, 1250, 750));
        stage.show();
    }

    @FXML
    private void abrirConsultas() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/com/gestionbicicletas/consultas.fxml"
                )
        );

        Parent root = loader.load();

        ConsultasController controller =
                loader.getController();

        controller.setGestionTaller(gestionTaller);

        Stage stage = new Stage();
        stage.setTitle("Consultas");
        stage.setScene(new Scene(root, 1400, 750));
        stage.show();
    }

    @FXML
    private void abrirRepuestos() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/com/gestionbicicletas/repuestos.fxml"
                )
        );

        Parent root = loader.load();

        RepuestoController controller =
                loader.getController();

        controller.setGestionTaller(gestionTaller);

        Stage stage = new Stage();
        stage.setTitle("Gestión de Repuestos");
        stage.setScene(new Scene(root, 1050, 650));
        stage.show();
    }
}