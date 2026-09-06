package com.gestionbicicletas;

import com.gestionbicicletas.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import com.gestionbicicletas.model.Especialidad;
import com.gestionbicicletas.model.TipoBicicleta;
import com.gestionbicicletas.service.GestionTaller;

public class Main {

    public static void main(String[] args) {

        // Crear un cliente
        Cliente cliente = new Cliente(
                "Carlos Rodriguez",
                "1098765432",
                "3001234567",
                "Armenia, Quindío"
        );

        // Crear una bicicleta
        Bicicleta bicicleta = new Bicicleta(
                "Trek",
                TipoBicicleta.MTB,
                "Negro",
                "TRK-2024-001",
                2024,
                cliente
        );

        // Asociar la bicicleta con el cliente
        cliente.agregarBicicleta(bicicleta);

        // Crear un mecánico
        Mecanico mecanico = new Mecanico(
                "Andrés Gómez",
                Especialidad.FRENOS_Y_TRANSMISION,
                "MEC-001"
        );

        // Crear una orden de servicio
        OrdenServicio orden = new OrdenServicio(
                LocalDate.of(2026, 9, 5),
                LocalTime.of(14, 30),
                bicicleta,
                mecanico,
                "La bicicleta presenta problemas al cambiar de velocidad",
                "Desgaste en el sistema de transmisión",
                "Ajuste de cambios y reemplazo de cadena",
                85000
        );

        // Asociar la orden con la bicicleta
        bicicleta.agregarOrdenServicio(orden);

        // Crear un repuesto
        Repuesto repuesto = new Repuesto(
                "Cadena de bicicleta",
                3,
                5
        );

        GestionTaller gestionTaller = new GestionTaller();

        gestionTaller.registrarCliente(cliente);
        gestionTaller.registrarBicicleta(bicicleta);
        gestionTaller.registrarMecanico(mecanico);
        gestionTaller.registrarOrdenServicio(orden);
        gestionTaller.registrarRepuesto(repuesto);

        // Mostrar información
        System.out.println("===== CLIENTE =====");
        System.out.println("Nombre: " + cliente.getNombreCompleto());
        System.out.println("Identificación: " + cliente.getIdentificacion());

        System.out.println("\n===== BICICLETA =====");
        System.out.println("Marca: " + bicicleta.getMarca());
        System.out.println("Tipo: " + bicicleta.getTipo());
        System.out.println("Serial: " + bicicleta.getSerial());

        System.out.println("\n===== MECÁNICO =====");
        System.out.println("Nombre: " + mecanico.getNombreCompleto());
        System.out.println("Especialidad: " + mecanico.getEspecialidad());

        System.out.println("\n===== ORDEN DE SERVICIO =====");
        System.out.println("Fecha: " + orden.getFechaIngreso());
        System.out.println("Hora: " + orden.getHoraIngreso());
        System.out.println("Motivo: " + orden.getMotivoServicio());
        System.out.println("Diagnóstico: " + orden.getDiagnostico());
        System.out.println("Trabajos: " + orden.getTrabajosRealizados());
        System.out.println("Costo: $" + orden.getCostoTotal());

        System.out.println("\n===== HISTORIAL =====");
        System.out.println(
                "Cantidad de servicios de la bicicleta: "
                        + bicicleta.getOrdenesServicio().size()
        );

        System.out.println("\n===== STOCK =====");
        System.out.println("Repuesto: " + repuesto.getNombre());
        System.out.println("Cantidad: " + repuesto.getCantidad());
        System.out.println("Stock mínimo: " + repuesto.getStockMinimo());
        System.out.println("¿Stock bajo?: " + repuesto.stockBajo());

        System.out.println(
                "\nClientes registrados: "
                        + gestionTaller.getClientes().size()
        );

        System.out.println(
                "Bicicletas registradas: "
                        + gestionTaller.getBicicletas().size()
        );

        System.out.println(
                "Mecánicos registrados: "
                        + gestionTaller.getMecanicos().size()
        );

        System.out.println(
                "Órdenes registradas: "
                        + gestionTaller.getOrdenesServicio().size()
        );

        System.out.println(
                "Historial de la bicicleta: "
                        + gestionTaller.buscarHistorialPorSerial(
                        "TRK-2024-001"
                ).size()
        );

        System.out.println(
                "Órdenes del 05/09/2026: "
                        + gestionTaller.buscarOrdenesPorFecha(
                        LocalDate.of(2026, 9, 5)
                ).size()
        );

        System.out.println(
                "Repuestos con stock bajo: "
                        + gestionTaller.obtenerRepuestosStockBajo().size()
        );
    }


}