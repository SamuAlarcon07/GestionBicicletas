package com.gestionbicicletas.controller;

import com.gestionbicicletas.model.Cliente;
import com.gestionbicicletas.service.GestionTaller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ClienteController {

    private GestionTaller gestionTaller;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TableColumn<Cliente, String> columnaNombre;

    @FXML
    private TableColumn<Cliente, String> columnaIdentificacion;

    @FXML
    private TableColumn<Cliente, String> columnaTelefono;

    @FXML
    private TableColumn<Cliente, String> columnaDireccion;

    public void setGestionTaller(GestionTaller gestionTaller) {
        this.gestionTaller = gestionTaller;
        actualizarTabla();
    }

    @FXML
    public void initialize() {

        columnaNombre.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getNombreCompleto()
                )
        );

        columnaIdentificacion.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getIdentificacion()
                )
        );

        columnaTelefono.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getTelefono()
                )
        );

        columnaDireccion.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getDireccion()
                )
        );
    }

    @FXML
    private void registrarCliente() {

        if (txtNombre.getText().isBlank()
                || txtIdentificacion.getText().isBlank()
                || txtTelefono.getText().isBlank()
                || txtDireccion.getText().isBlank()) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos incompletos",
                    "Por favor, completa todos los campos."
            );
            return;
        }

        try {

            Cliente cliente = new Cliente(
                    txtNombre.getText().trim(),
                    txtIdentificacion.getText().trim(),
                    txtTelefono.getText().trim(),
                    txtDireccion.getText().trim()
            );

            gestionTaller.registrarCliente(cliente);

            limpiarCampos();
            actualizarTabla();

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Cliente registrado",
                    "El cliente se registró correctamente."
            );

        } catch (IllegalArgumentException e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "No se pudo registrar",
                    e.getMessage()
            );
        }
    }

    @FXML
    private void limpiarCampos() {
        txtNombre.clear();
        txtIdentificacion.clear();
        txtTelefono.clear();
        txtDireccion.clear();
    }

    private void actualizarTabla() {

        if (gestionTaller == null) {
            return;
        }

        tablaClientes.setItems(
                FXCollections.observableArrayList(
                        gestionTaller.getClientes()
                )
        );
    }

    @FXML
    private void volverAlMenu() {

        Stage stage = (Stage) tablaClientes.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String titulo,
            String mensaje) {

        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}