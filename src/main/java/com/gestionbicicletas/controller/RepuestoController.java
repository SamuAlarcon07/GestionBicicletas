package com.gestionbicicletas.controller;

import com.gestionbicicletas.model.Repuesto;
import com.gestionbicicletas.service.GestionTaller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RepuestoController {

    private GestionTaller gestionTaller;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtStockMinimo;

    @FXML
    private TableView<Repuesto> tablaRepuestos;

    @FXML
    private TableColumn<Repuesto, String> columnaNombre;

    @FXML
    private TableColumn<Repuesto, String> columnaCantidad;

    @FXML
    private TableColumn<Repuesto, String> columnaStockMinimo;

    @FXML
    private TableColumn<Repuesto, String> columnaEstado;

    @FXML
    private Label lblAlertas;

    public void setGestionTaller(GestionTaller gestionTaller) {
        this.gestionTaller = gestionTaller;

        actualizarTabla();
        actualizarAlertas();
    }

    @FXML
    public void initialize() {

        columnaNombre.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getNombre()
                )
        );

        columnaCantidad.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(datos.getValue().getCantidad())
                )
        );

        columnaStockMinimo.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(datos.getValue().getStockMinimo())
                )
        );

        columnaEstado.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().stockBajo()
                                ? "STOCK BAJO"
                                : "Disponible"
                )
        );
    }

    @FXML
    private void registrarRepuesto() {

        if (txtNombre.getText().isBlank()
                || txtCantidad.getText().isBlank()
                || txtStockMinimo.getText().isBlank()) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos incompletos",
                    "Por favor, completa todos los campos."
            );

            return;
        }

        try {

            int cantidad = Integer.parseInt(
                    txtCantidad.getText().trim()
            );

            int stockMinimo = Integer.parseInt(
                    txtStockMinimo.getText().trim()
            );

            if (cantidad < 0 || stockMinimo < 0) {

                mostrarAlerta(
                        Alert.AlertType.WARNING,
                        "Valores inválidos",
                        "La cantidad y el stock mínimo no pueden ser negativos."
                );

                return;
            }

            Repuesto repuesto = new Repuesto(
                    txtNombre.getText().trim(),
                    cantidad,
                    stockMinimo
            );

            gestionTaller.registrarRepuesto(repuesto);

            limpiarCampos();
            actualizarTabla();
            actualizarAlertas();

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Repuesto registrado",
                    "El repuesto se registró correctamente."
            );

        } catch (NumberFormatException e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Cantidad inválida",
                    "La cantidad y el stock mínimo deben ser números enteros."
            );
        }
    }

    @FXML
    private void limpiarCampos() {

        txtNombre.clear();
        txtCantidad.clear();
        txtStockMinimo.clear();
    }

    private void actualizarTabla() {

        if (gestionTaller == null) {
            return;
        }

        tablaRepuestos.setItems(
                FXCollections.observableArrayList(
                        gestionTaller.getRepuestos()
                )
        );
    }

    private void actualizarAlertas() {

        if (gestionTaller == null) {
            return;
        }

        int cantidadAlertas =
                gestionTaller.obtenerRepuestosStockBajo().size();

        if (cantidadAlertas == 0) {

            lblAlertas.setText(
                    "✓ No hay repuestos con stock bajo."
            );

        } else {

            lblAlertas.setText(
                    "⚠ Repuestos con stock bajo: "
                            + cantidadAlertas
            );
        }
    }

    @FXML
    private void mostrarStockBajo() {

        var repuestosStockBajo =
                gestionTaller.obtenerRepuestosStockBajo();

        if (repuestosStockBajo.isEmpty()) {

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Stock",
                    "No hay repuestos con stock bajo."
            );

            return;
        }

        StringBuilder mensaje = new StringBuilder();

        for (Repuesto repuesto : repuestosStockBajo) {

            mensaje.append("• ")
                    .append(repuesto.getNombre())
                    .append(" — Cantidad: ")
                    .append(repuesto.getCantidad())
                    .append(" — Mínimo: ")
                    .append(repuesto.getStockMinimo())
                    .append("\n");
        }

        mostrarAlerta(
                Alert.AlertType.WARNING,
                "Repuestos con stock bajo",
                mensaje.toString()
        );
    }

    @FXML
    private void volverAlMenu() {

        Stage stage =
                (Stage) tablaRepuestos.getScene().getWindow();

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