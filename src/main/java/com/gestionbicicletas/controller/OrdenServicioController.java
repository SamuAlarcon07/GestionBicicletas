package com.gestionbicicletas.controller;

import com.gestionbicicletas.model.Bicicleta;
import com.gestionbicicletas.model.DetalleRepuesto;
import com.gestionbicicletas.model.Mecanico;
import com.gestionbicicletas.model.OrdenServicio;
import com.gestionbicicletas.model.Repuesto;
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

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class OrdenServicioController {

    private GestionTaller gestionTaller;

    // =========================
    // CAMPOS DE LA ORDEN
    // =========================

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


    // =========================
    // CAMPOS DE REPUESTOS
    // =========================

    @FXML
    private ComboBox<Repuesto> comboRepuesto;

    @FXML
    private TextField txtCantidadRepuesto;

    @FXML
    private TableView<DetalleRepuesto> tablaRepuestos;

    @FXML
    private TableColumn<DetalleRepuesto, String> columnaRepuesto;

    @FXML
    private TableColumn<DetalleRepuesto, String> columnaCantidad;


    // =========================
    // TABLA DE ÓRDENES
    // =========================

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

    @FXML
    private TableColumn<OrdenServicio, String> columnaRepuestos;


    // =========================
    // CONEXIÓN CON GESTIONTALLER
    // =========================

    public void setGestionTaller(GestionTaller gestionTaller) {

        this.gestionTaller = gestionTaller;

        cargarBicicletas();
        cargarMecanicos();
        cargarRepuestos();
        actualizarTabla();
    }


    // =========================
    // INICIALIZACIÓN
    // =========================

    @FXML
    public void initialize() {

        // -------------------------
        // COLUMNAS DE ÓRDENES
        // -------------------------

        columnaFecha.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue()
                                .getFechaIngreso()
                                .toString()
                )
        );

        columnaHora.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue()
                                .getHoraIngreso()
                                .format(
                                        DateTimeFormatter.ofPattern("HH:mm")
                                )
                )
        );

        columnaBicicleta.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue()
                                .getBicicleta()
                                .getSerial()
                )
        );

        columnaMecanico.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue()
                                .getMecanico()
                                .getNombreCompleto()
                )
        );

        columnaMotivo.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue()
                                .getMotivoServicio()
                )
        );

        columnaCosto.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        String.format(
                                "$%,.0f",
                                datos.getValue()
                                        .getCostoTotal()
                        )
                )
        );


        // -------------------------
        // COLUMNA DE REPUESTOS
        // -------------------------

        columnaRepuestos.setCellValueFactory(
                datos -> {

                    StringBuilder repuestos =
                            new StringBuilder();

                    for (DetalleRepuesto detalle :
                            datos.getValue()
                                    .getRepuestosUtilizados()) {

                        if (repuestos.length() > 0) {
                            repuestos.append(", ");
                        }

                        repuestos.append(
                                        detalle.getRepuesto().getNombre()
                                )
                                .append(" (")
                                .append(
                                        detalle.getCantidadUtilizada()
                                )
                                .append(")");
                    }

                    return new javafx.beans.property.SimpleStringProperty(
                            repuestos.toString()
                    );
                }
        );


        // -------------------------
        // COLUMNAS DE LA TABLA
        // DE REPUESTOS
        // -------------------------

        columnaRepuesto.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        datos.getValue()
                                .getRepuesto()
                                .getNombre()
                )
        );

        columnaCantidad.setCellValueFactory(
                datos -> new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(
                                datos.getValue()
                                        .getCantidadUtilizada()
                        )
                )
        );
    }


    // =========================
    // CARGAR BICICLETAS
    // =========================

    private void cargarBicicletas() {

        comboBicicleta.setItems(
                FXCollections.observableArrayList(
                        gestionTaller.getBicicletas()
                )
        );
    }


    // =========================
    // CARGAR MECÁNICOS
    // =========================

    private void cargarMecanicos() {

        comboMecanico.setItems(
                FXCollections.observableArrayList(
                        gestionTaller.getMecanicos()
                )
        );
    }


    // =========================
    // CARGAR REPUESTOS
    // =========================

    private void cargarRepuestos() {

        comboRepuesto.setItems(
                FXCollections.observableArrayList(
                        gestionTaller.getRepuestos()
                )
        );
    }


    // =========================
    // AGREGAR REPUESTO
    // =========================

    @FXML
    private void agregarRepuesto() {

        Repuesto repuesto = comboRepuesto.getValue();

        if (repuesto == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Repuesto no seleccionado",
                    "Selecciona un repuesto."
            );

            return;
        }

        if (txtCantidadRepuesto.getText().isBlank()) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Cantidad incompleta",
                    "Indica la cantidad de unidades utilizadas."
            );

            return;
        }

        try {

            int cantidad = Integer.parseInt(
                    txtCantidadRepuesto.getText().trim()
            );

            if (cantidad <= 0) {

                mostrarAlerta(
                        Alert.AlertType.WARNING,
                        "Cantidad inválida",
                        "La cantidad debe ser mayor que cero."
                );

                return;
            }

            if (cantidad > repuesto.getCantidad()) {

                mostrarAlerta(
                        Alert.AlertType.WARNING,
                        "Stock insuficiente",
                        "Solo hay "
                                + repuesto.getCantidad()
                                + " unidades disponibles de "
                                + repuesto.getNombre()
                                + "."
                );

                return;
            }


            // Evitar que el mismo repuesto
            // se agregue dos veces a la misma orden.

            for (DetalleRepuesto detalle :
                    tablaRepuestos.getItems()) {

                if (detalle.getRepuesto() == repuesto) {

                    mostrarAlerta(
                            Alert.AlertType.WARNING,
                            "Repuesto repetido",
                            "Ese repuesto ya fue agregado a la orden."
                    );

                    return;
                }
            }


            DetalleRepuesto detalle =
                    new DetalleRepuesto(
                            repuesto,
                            cantidad
                    );

            tablaRepuestos.getItems().add(detalle);


            comboRepuesto.setValue(null);
            txtCantidadRepuesto.clear();

        } catch (NumberFormatException e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Cantidad inválida",
                    "La cantidad debe ser un número entero."
            );
        }
    }


    // =========================
    // ELIMINAR REPUESTO
    // =========================

    @FXML
    private void eliminarRepuesto() {

        DetalleRepuesto seleccionado =
                tablaRepuestos
                        .getSelectionModel()
                        .getSelectedItem();

        if (seleccionado == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Sin selección",
                    "Selecciona un repuesto de la tabla para eliminarlo."
            );

            return;
        }

        tablaRepuestos.getItems().remove(seleccionado);
    }


    // =========================
    // REGISTRAR ORDEN
    // =========================

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
                    "Por favor, completa todos los campos de la orden."
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


            OrdenServicio orden =
                    new OrdenServicio(
                            fechaIngreso.getValue(),
                            hora,
                            comboBicicleta.getValue(),
                            comboMecanico.getValue(),
                            txtMotivo.getText().trim(),
                            txtDiagnostico.getText().trim(),
                            txtTrabajos.getText().trim(),
                            costo
                    );


            // Agregar los repuestos
            // seleccionados a la orden.

            for (DetalleRepuesto detalle :
                    tablaRepuestos.getItems()) {

                orden.agregarRepuesto(
                        detalle.getRepuesto(),
                        detalle.getCantidadUtilizada()
                );
            }


            // Registrar la orden.
            // GestionTaller también se encarga
            // de descontar el stock.

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


    // =========================
    // LIMPIAR CAMPOS
    // =========================

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

        comboRepuesto.setValue(null);

        txtCantidadRepuesto.clear();

        tablaRepuestos.getItems().clear();
    }


    // =========================
    // ACTUALIZAR TABLA
    // =========================

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


    // =========================
    // VOLVER AL MENÚ
    // =========================

    @FXML
    private void volverAlMenu() {

        Stage stage =
                (Stage) tablaOrdenes
                        .getScene()
                        .getWindow();

        stage.close();
    }


    // =========================
    // MOSTRAR ALERTAS
    // =========================

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