package com.gestionbicicletas.controller;

import com.gestionbicicletas.model.Especialidad;
import com.gestionbicicletas.model.Mecanico;
import com.gestionbicicletas.service.GestionTaller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MecanicoController {

    private GestionTaller gestionTaller;

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<Especialidad> comboEspecialidad;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TableView<Mecanico> tablaMecanicos;

    @FXML
    private TableColumn<Mecanico, String> columnaNombre;

    @FXML
    private TableColumn<Mecanico, String> columnaEspecialidad;

    @FXML
    private TableColumn<Mecanico, String> columnaCodigo;

    public void setGestionTaller(GestionTaller gestionTaller) {
        this.gestionTaller = gestionTaller;

        cargarEspecialidades();
        actualizarTabla();
    }

    @FXML
    public void initialize() {

        columnaNombre.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getNombreCompleto()
                )
        );

        columnaEspecialidad.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getEspecialidad().toString()
                )
        );

        columnaCodigo.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getCodigoCertificacion()
                )
        );
    }

    private void cargarEspecialidades() {

        comboEspecialidad.setItems(
                FXCollections.observableArrayList(
                        Especialidad.values()
                )
        );
    }

    @FXML
    private void registrarMecanico() {

        if (txtNombre.getText().isBlank()
                || comboEspecialidad.getValue() == null
                || txtCodigo.getText().isBlank()) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos incompletos",
                    "Por favor, completa todos los campos."
            );

            return;
        }

        try {

            Mecanico mecanico = new Mecanico(
                    txtNombre.getText().trim(),
                    comboEspecialidad.getValue(),
                    txtCodigo.getText().trim()
            );

            gestionTaller.registrarMecanico(mecanico);

            limpiarCampos();
            actualizarTabla();

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Mecánico registrado",
                    "El mecánico se registró correctamente."
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
        comboEspecialidad.setValue(null);
        txtCodigo.clear();
    }

    private void actualizarTabla() {

        if (gestionTaller == null) {
            return;
        }

        tablaMecanicos.setItems(
                FXCollections.observableArrayList(
                        gestionTaller.getMecanicos()
                )
        );
    }

    @FXML
    private void volverAlMenu() {

        Stage stage = (Stage) tablaMecanicos.getScene().getWindow();
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
    private void eliminarMecanico() {

        Mecanico mecanicoSeleccionado =
                tablaMecanicos.getSelectionModel().getSelectedItem();

        if (mecanicoSeleccionado == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Ningún mecánico seleccionado",
                    "Selecciona un mecánico de la tabla."
            );

            return;
        }

        Alert confirmacion = new Alert(
                Alert.AlertType.CONFIRMATION
        );

        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText(
                "¿Deseas eliminar al mecánico "
                        + mecanicoSeleccionado.getNombreCompleto()
                        + "?"
        );

        if (confirmacion.showAndWait().orElse(null)
                == javafx.scene.control.ButtonType.OK) {

            try {

                gestionTaller.eliminarMecanico(
                        mecanicoSeleccionado
                );

                actualizarTabla();

                mostrarAlerta(
                        Alert.AlertType.INFORMATION,
                        "Mecánico eliminado",
                        "El mecánico se eliminó correctamente."
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