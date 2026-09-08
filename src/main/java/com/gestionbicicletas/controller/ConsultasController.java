package com.gestionbicicletas.controller;

import com.gestionbicicletas.model.OrdenServicio;
import com.gestionbicicletas.service.GestionTaller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class ConsultasController {

    private GestionTaller gestionTaller;

    // -------------------------
    // HISTORIAL POR SERIAL
    // -------------------------

    @FXML
    private TextField txtSerial;

    @FXML
    private TableView<OrdenServicio> tablaHistorial;

    @FXML
    private TableColumn<OrdenServicio, String> columnaHistorialFecha;

    @FXML
    private TableColumn<OrdenServicio, String> columnaHistorialHora;

    @FXML
    private TableColumn<OrdenServicio, String> columnaHistorialMecanico;

    @FXML
    private TableColumn<OrdenServicio, String> columnaHistorialMotivo;

    @FXML
    private TableColumn<OrdenServicio, String> columnaHistorialDiagnostico;

    @FXML
    private TableColumn<OrdenServicio, String> columnaHistorialTrabajos;

    @FXML
    private TableColumn<OrdenServicio, String> columnaHistorialCosto;

    // -------------------------
    // BÚSQUEDA POR FECHA
    // -------------------------

    @FXML
    private DatePicker fechaBusqueda;

    @FXML
    private TableView<OrdenServicio> tablaFecha;

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

    @FXML
    private Label lblResultadoHistorial;

    @FXML
    private Label lblResultadoFecha;

    public void setGestionTaller(GestionTaller gestionTaller) {
        this.gestionTaller = gestionTaller;
    }

    @FXML
    public void initialize() {

        // Tabla de historial

        columnaHistorialFecha.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getFechaIngreso().toString()
                )
        );

        columnaHistorialHora.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getHoraIngreso().format(
                                DateTimeFormatter.ofPattern("HH:mm")
                        )
                )
        );

        columnaHistorialMecanico.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getMecanico().getNombreCompleto()
                )
        );

        columnaHistorialMotivo.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getMotivoServicio()
                )
        );

        columnaHistorialDiagnostico.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getDiagnostico()
                )
        );

        columnaHistorialTrabajos.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getTrabajosRealizados()
                )
        );

        columnaHistorialCosto.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        String.format(
                                "$%,.0f",
                                datos.getValue().getCostoTotal()
                        )
                )
        );

        // Tabla de búsqueda por fecha

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

    @FXML
    private void buscarHistorial() {

        if (txtSerial.getText().isBlank()) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Serial vacío",
                    "Escribe el serial de una bicicleta."
            );

            return;
        }

        String serial = txtSerial.getText().trim();

        if (gestionTaller.buscarBicicletaPorSerial(serial) == null) {

            tablaHistorial.getItems().clear();

            lblResultadoHistorial.setText(
                    "No se encontró una bicicleta con ese serial."
            );

            return;
        }

        var historial = gestionTaller.buscarHistorialPorSerial(serial);

        tablaHistorial.setItems(
                FXCollections.observableArrayList(historial)
        );

        lblResultadoHistorial.setText(
                "Órdenes encontradas: " + historial.size()
        );
    }

    @FXML
    private void buscarPorFecha() {

        if (fechaBusqueda.getValue() == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Fecha no seleccionada",
                    "Selecciona una fecha para realizar la búsqueda."
            );

            return;
        }

        var resultado = gestionTaller.buscarOrdenesPorFecha(
                fechaBusqueda.getValue()
        );

        tablaFecha.setItems(
                FXCollections.observableArrayList(resultado)
        );

        lblResultadoFecha.setText(
                "Órdenes encontradas: " + resultado.size()
        );
    }

    @FXML
    private void volverAlMenu() {

        Stage stage = (Stage) tablaHistorial.getScene().getWindow();
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