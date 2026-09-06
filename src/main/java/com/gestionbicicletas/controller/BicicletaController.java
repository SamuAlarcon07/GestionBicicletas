package com.gestionbicicletas.controller;

import com.gestionbicicletas.model.Bicicleta;
import com.gestionbicicletas.model.Cliente;
import com.gestionbicicletas.model.TipoBicicleta;
import com.gestionbicicletas.service.GestionTaller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BicicletaController {

    private GestionTaller gestionTaller;

    @FXML
    private ComboBox<Cliente> comboCliente;

    @FXML
    private TextField txtMarca;

    @FXML
    private ComboBox<TipoBicicleta> comboTipo;

    @FXML
    private TextField txtColor;

    @FXML
    private TextField txtSerial;

    @FXML
    private TextField txtAnio;

    @FXML
    private TableView<Bicicleta> tablaBicicletas;

    @FXML
    private TableColumn<Bicicleta, String> columnaCliente;

    @FXML
    private TableColumn<Bicicleta, String> columnaMarca;

    @FXML
    private TableColumn<Bicicleta, String> columnaTipo;

    @FXML
    private TableColumn<Bicicleta, String> columnaColor;

    @FXML
    private TableColumn<Bicicleta, String> columnaSerial;

    @FXML
    private TableColumn<Bicicleta, String> columnaAnio;

    public void setGestionTaller(GestionTaller gestionTaller) {
        this.gestionTaller = gestionTaller;

        cargarClientes();
        cargarTipos();
        actualizarTabla();
    }

    @FXML
    public void initialize() {

        columnaCliente.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getCliente().getNombreCompleto()
                )
        );

        columnaMarca.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getMarca()
                )
        );

        columnaTipo.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getTipo().toString()
                )
        );

        columnaColor.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getColor()
                )
        );

        columnaSerial.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getSerial()
                )
        );

        columnaAnio.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(datos.getValue().getAnio())
                )
        );
    }

    private void cargarClientes() {

        comboCliente.setItems(
                FXCollections.observableArrayList(
                        gestionTaller.getClientes()
                )
        );
    }

    private void cargarTipos() {

        comboTipo.setItems(
                FXCollections.observableArrayList(
                        TipoBicicleta.values()
                )
        );
    }

    @FXML
    private void registrarBicicleta() {

        if (comboCliente.getValue() == null
                || txtMarca.getText().isBlank()
                || comboTipo.getValue() == null
                || txtColor.getText().isBlank()
                || txtSerial.getText().isBlank()
                || txtAnio.getText().isBlank()) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos incompletos",
                    "Por favor, completa todos los campos."
            );

            return;
        }

        try {

            int anio = Integer.parseInt(txtAnio.getText().trim());

            if (anio <= 0) {
                throw new IllegalArgumentException(
                        "El año debe ser un número positivo."
                );
            }

            Bicicleta bicicleta = new Bicicleta(
                    txtMarca.getText().trim(),
                    comboTipo.getValue(),
                    txtColor.getText().trim(),
                    txtSerial.getText().trim(),
                    anio,
                    comboCliente.getValue()
            );

            gestionTaller.registrarBicicleta(bicicleta);

            limpiarCampos();
            actualizarTabla();

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Bicicleta registrada",
                    "La bicicleta se registró correctamente."
            );

        } catch (NumberFormatException e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Año inválido",
                    "El año debe ser un número."
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

        comboCliente.setValue(null);
        txtMarca.clear();
        comboTipo.setValue(null);
        txtColor.clear();
        txtSerial.clear();
        txtAnio.clear();
    }

    private void actualizarTabla() {

        if (gestionTaller == null) {
            return;
        }

        tablaBicicletas.setItems(
                FXCollections.observableArrayList(
                        gestionTaller.getBicicletas()
                )
        );
    }

    @FXML
    private void volverAlMenu() {

        Stage stage = (Stage) tablaBicicletas.getScene().getWindow();
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

    @FXML
    private void eliminarBicicleta() {

        Bicicleta bicicletaSeleccionada =
                tablaBicicletas.getSelectionModel().getSelectedItem();

        if (bicicletaSeleccionada == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Ninguna bicicleta seleccionada",
                    "Selecciona una bicicleta de la tabla."
            );

            return;
        }

        Alert confirmacion = new Alert(
                Alert.AlertType.CONFIRMATION
        );

        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText(
                "¿Deseas eliminar la bicicleta con serial "
                        + bicicletaSeleccionada.getSerial()
                        + "?"
        );

        if (confirmacion.showAndWait().orElse(null)
                == javafx.scene.control.ButtonType.OK) {

            try {

                gestionTaller.eliminarBicicleta(
                        bicicletaSeleccionada
                );

                actualizarTabla();

                mostrarAlerta(
                        Alert.AlertType.INFORMATION,
                        "Bicicleta eliminada",
                        "La bicicleta se eliminó correctamente."
                );

            } catch (IllegalArgumentException e) {

                mostrarAlerta(
                        Alert.AlertType.ERROR,
                        "No se puede eliminar",
                        e.getMessage()
                );
            }
        }
    }
}