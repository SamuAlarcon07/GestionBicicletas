package com.gestionbicicletas;

import com.gestionbicicletas.service.GestionTaller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private GestionTaller gestionTaller;

    @Override
    public void start(Stage stage) throws IOException {

        gestionTaller = new GestionTaller();

        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource("/com/gestionbicicletas/menu-principal.fxml")
        );

        Scene scene = new Scene(loader.load(), 1000, 650);

        stage.setTitle("Gestión de Bicicletas");
        stage.setScene(scene);
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}