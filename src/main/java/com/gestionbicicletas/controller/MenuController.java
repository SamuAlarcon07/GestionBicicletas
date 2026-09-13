package com.gestionbicicletas.controller;

import com.gestionbicicletas.service.GestionTaller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.StackPane;

import javafx.scene.shape.Rectangle;

import javafx.stage.Stage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class MenuController {

    private GestionTaller gestionTaller;


    // =========================================================
    // ELEMENTOS DEL MENU PRINCIPAL
    // =========================================================

    @FXML
    private Label lblFecha;

    @FXML
    private Label lblHora;


    // =========================================================
    // ELEMENTOS DEL BANNER
    // =========================================================

    @FXML
    private StackPane bannerContainer;

    @FXML
    private ImageView bannerImage;


    // =========================================================
    // RELOJ
    // =========================================================

    private Timeline reloj;


    // =========================================================
    // INICIALIZACIÓN
    // =========================================================

    @FXML
    public void initialize() {

        configurarBanner();

        configurarFechaHora();
    }


    // =========================================================
    // CONFIGURAR BANNER
    // =========================================================

    private void configurarBanner() {

        URL imagenURL = getClass().getResource(
                "/com/gestionbicicletas/Banner.png"
        );

        if (imagenURL == null) {

            System.out.println(
                    "ERROR: No se encontró banner-taller.png"
            );

            return;
        }


        Image imagen = new Image(
                imagenURL.toExternalForm()
        );


        bannerImage.setImage(imagen);


        // -----------------------------------------------------
        // AJUSTE DEL TAMAÑO
        // -----------------------------------------------------

        bannerImage.fitWidthProperty().bind(
                bannerContainer.widthProperty()
        );

        bannerImage.fitHeightProperty().bind(
                bannerContainer.heightProperty()
        );


        // Mantener las proporciones originales
        bannerImage.setPreserveRatio(true);


        // -----------------------------------------------------
        // BORDES REDONDEADOS
        // -----------------------------------------------------

        Rectangle clip = new Rectangle();


        clip.widthProperty().bind(
                bannerContainer.widthProperty()
        );

        clip.heightProperty().bind(
                bannerContainer.heightProperty()
        );


        clip.setArcWidth(30);

        clip.setArcHeight(30);


        bannerContainer.setClip(clip);
    }


    // =========================================================
    // FECHA Y HORA
    // =========================================================

    private void configurarFechaHora() {

        actualizarFechaHora();


        reloj = new Timeline(
                new KeyFrame(
                        Duration.seconds(1),
                        event -> actualizarFechaHora()
                )
        );


        reloj.setCycleCount(
                Timeline.INDEFINITE
        );


        reloj.play();
    }


    private void actualizarFechaHora() {

        LocalDateTime ahora =
                LocalDateTime.now();


        DateTimeFormatter formatoFecha =
                DateTimeFormatter.ofPattern(
                        "EEEE, d 'de' MMMM 'de' yyyy",
                        new Locale("es", "CO")
                );


        DateTimeFormatter formatoHora =
                DateTimeFormatter.ofPattern(
                        "hh:mm a",
                        new Locale("es", "CO")
                );


        if (lblFecha != null) {

            String fecha =
                    ahora.format(formatoFecha);

            fecha =
                    fecha.substring(0, 1).toUpperCase()
                            + fecha.substring(1);

            lblFecha.setText(fecha);
        }


        if (lblHora != null) {

            lblHora.setText(
                    ahora.format(formatoHora)
            );
        }
    }


    // =========================================================
    // GESTIÓN DEL TALLER
    // =========================================================

    public void setGestionTaller(
            GestionTaller gestionTaller
    ) {

        this.gestionTaller = gestionTaller;
    }


    // =========================================================
    // CLIENTES
    // =========================================================

    @FXML
    private void abrirClientes()
            throws IOException {

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/com/gestionbicicletas/clientes.fxml"
                        )
                );


        Parent root = loader.load();


        ClienteController controller =
                loader.getController();


        controller.setGestionTaller(
                gestionTaller
        );


        Stage stage = new Stage();


        stage.setTitle(
                "Gestión de Clientes"
        );


        stage.setScene(
                new Scene(
                        root,
                        1000,
                        650
                )
        );


        stage.show();
    }


    // =========================================================
    // BICICLETAS
    // =========================================================

    @FXML
    private void abrirBicicletas()
            throws IOException {

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/com/gestionbicicletas/bicicletas.fxml"
                        )
                );


        Parent root = loader.load();


        BicicletaController controller =
                loader.getController();


        controller.setGestionTaller(
                gestionTaller
        );


        Stage stage = new Stage();


        stage.setTitle(
                "Gestión de Bicicletas"
        );


        stage.setScene(
                new Scene(
                        root,
                        1100,
                        650
                )
        );


        stage.show();
    }


    // =========================================================
    // MECÁNICOS
    // =========================================================

    @FXML
    private void abrirMecanicos()
            throws IOException {

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/com/gestionbicicletas/mecanicos.fxml"
                        )
                );


        Parent root = loader.load();


        MecanicoController controller =
                loader.getController();


        controller.setGestionTaller(
                gestionTaller
        );


        Stage stage = new Stage();


        stage.setTitle(
                "Gestión de Mecánicos"
        );


        stage.setScene(
                new Scene(
                        root,
                        950,
                        600
                )
        );


        stage.show();
    }


    // =========================================================
    // ÓRDENES DE SERVICIO
    // =========================================================

    @FXML
    private void abrirOrdenes()
            throws IOException {

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/com/gestionbicicletas/ordenes.fxml"
                        )
                );


        Parent root = loader.load();


        OrdenServicioController controller =
                loader.getController();


        controller.setGestionTaller(
                gestionTaller
        );


        Stage stage = new Stage();


        stage.setTitle(
                "Órdenes de Servicio"
        );


        stage.setScene(
                new Scene(
                        root,
                        1250,
                        750
                )
        );


        stage.show();
    }


    // =========================================================
    // CONSULTAS
    // =========================================================

    @FXML
    private void abrirConsultas()
            throws IOException {

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/com/gestionbicicletas/consultas.fxml"
                        )
                );


        Parent root = loader.load();


        ConsultasController controller =
                loader.getController();


        controller.setGestionTaller(
                gestionTaller
        );


        Stage stage = new Stage();


        stage.setTitle(
                "Consultas"
        );


        stage.setScene(
                new Scene(
                        root,
                        1400,
                        750
                )
        );


        stage.show();
    }


    // =========================================================
    // REPUESTOS
    // =========================================================

    @FXML
    private void abrirRepuestos()
            throws IOException {

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/com/gestionbicicletas/repuestos.fxml"
                        )
                );


        Parent root = loader.load();


        RepuestoController controller =
                loader.getController();


        controller.setGestionTaller(
                gestionTaller
        );


        Stage stage = new Stage();


        stage.setTitle(
                "Gestión de Repuestos"
        );


        stage.setScene(
                new Scene(
                        root,
                        1050,
                        650
                )
        );


        stage.show();
    }
}