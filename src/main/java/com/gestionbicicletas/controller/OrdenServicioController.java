package com.gestionbicicletas.controller;

import com.gestionbicicletas.model.Bicicleta;
import com.gestionbicicletas.model.Mecanico;
import com.gestionbicicletas.model.OrdenServicio;
import com.gestionbicicletas.service.GestionTaller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class OrdenServicioController {

    private GestionTaller gestionTaller;

    @FXML
    private DatePicker fechaIngreso;

    @FXML
    private TextField txtHora;

    @FXML
    private ComboBox<Bicicleta> comboBicicleta;

    @FXML
    private ComboBox<Mecanico> comboMecanico;

    @FXML
    private TextArea txtMotivo;

    @FXML
    private TextArea txtDiagnostico;

    @FXML
    private TextArea txtTrabajos;

    @FXML
    private TextField txtCosto;

    @FXML
    private TableView<OrdenServicio> tablaOrdenes;

    @FXML
    private TableColumn<OrdenServicio, String> columnaFecha;

    @FXML
    private TableColumn<OrdenServicio, String> columnaHora;

    @FXML
    private TableColumn<OrdenServicio, String> columnaBicicleta;

    @FXML
    private TableColumn<OrdenServicio, String> columnaMecanico;

    @FXML
    private TableColumn<OrdenServicio, String> columnaMotivo;

    @FXML
    private TableColumn<OrdenServicio, String> columnaCosto;

    public void setGestionTaller(GestionTaller gestionTaller) {

        this.gestionTaller = gestionTaller;

        cargarBicicletas();
        cargarMecanicos();
        actualizarTabla();
    }

    @FXML
    public void initialize() {

        columnaFecha.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getFechaIngreso().toString()
                )
        );

        columnaHora.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getHoraIngreso().format(
                                DateTimeFormatter.ofPattern("HH:mm")
                        )
                )
        );

        columnaBicicleta.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getBicicleta().getSerial()
                )
        );

        columnaMecanico.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getMecanico().getNombreCompleto()
                )
        );

        columnaMotivo.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getMotivoServicio()
                )
        );

        columnaCosto.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        String.format(
                                "$%,.0f",
                                datos.getValue().getCostoTotal()
                        )
                )
        );
    }

    private void cargarBicicletas() {

        comboBicicleta.setItems(
                FXCollections.observableArrayList(
                        gestionTaller.getBicicletas()
                )
        );
    }

    private void cargarMecanicos() {

        comboMecanico.setItems(
                FXCollections.observableArrayList(
                        gestionTaller.getMecanicos()
                )
        );
    }

    @FXML
    private void registrarOrden() {

        if (fechaIngreso.getValue() == null
                || txtHora.getText().isBlank()
                || comboBicicleta.getValue() == null
                || comboMecanico.getValue() == null
                || txtMotivo.getText().isBlank()
                || txtDiagnostico.getText().isBlank()
                || txtTrabajos.getText().isBlank()
                || txtCosto.getText().isBlank()) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos incompletos",
                    "Por favor, completa todos los campos."
            );

            return;
        }

        try {

            LocalTime hora = LocalTime.parse(
                    txtHora.getText().trim(),
                    DateTimeFormatter.ofPattern("HH:mm")
            );

            double costo = Double.parseDouble(
                    txtCosto.getText()
                            .trim()
                            .replace(",", ".")
            );

            if (costo < 0) {
                throw new IllegalArgumentException(
                        "El costo no puede ser negativo."
                );
            }

            OrdenServicio orden = new OrdenServicio(
                    fechaIngreso.getValue(),
                    hora,
                    comboBicicleta.getValue(),
                    comboMecanico.getValue(),
                    txtMotivo.getText().trim(),
                    txtDiagnostico.getText().trim(),
                    txtTrabajos.getText().trim(),
                    costo
            );

            gestionTaller.registrarOrdenServicio(orden);

            limpiarCampos();
            actualizarTabla();

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Orden registrada",
                    "La orden de servicio se registró correctamente."
            );

        } catch (java.time.format.DateTimeParseException e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Hora inválida",
                    "La hora debe tener el formato HH:mm. Ejemplo: 14:30"
            );

        } catch (NumberFormatException e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Costo inválido",
                    "El costo debe ser un número válido."
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

        fechaIngreso.setValue(null);
        txtHora.clear();
        comboBicicleta.setValue(null);
        comboMecanico.setValue(null);
        txtMotivo.clear();
        txtDiagnostico.clear();
        txtTrabajos.clear();
        txtCosto.clear();
    }

    private void actualizarTabla() {

        if (gestionTaller == null) {
            return;
        }

        tablaOrdenes.setItems(
                FXCollections.observableArrayList(
                        gestionTaller.getOrdenesServicio()
                )
        );
    }

    @FXML
    private void volverAlMenu() {

        Stage stage = (Stage) tablaOrdenes.getScene().getWindow();
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