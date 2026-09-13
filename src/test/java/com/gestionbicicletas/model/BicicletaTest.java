package com.gestionbicicletas.model;

import com.gestionbicicletas.service.GestionTaller;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class BicicletaTest {

        @Test
        void deberiaCrearBicicletaCorrectamente() {

            Cliente cliente = new Cliente(
                    "Carlos Perez",
                    "123456",
                    "3001234567",
                    "Armenia"
            );

            Bicicleta bicicleta = new Bicicleta(
                    "Trek",
                    TipoBicicleta.MTB,
                    "Roja",
                    "ABC123",
                    2024,
                    cliente
            );

            assertEquals("Trek", bicicleta.getMarca());
            assertEquals(TipoBicicleta.MTB, bicicleta.getTipo());
            assertEquals("Roja", bicicleta.getColor());
            assertEquals("ABC123", bicicleta.getSerial());
            assertEquals(2024, bicicleta.getAnio());
            assertSame(cliente, bicicleta.getCliente());
        }

        @Test
        void deberiaActualizarLosDatosDeLaBicicleta() {

            Cliente cliente = new Cliente(
                    "Carlos Perez",
                    "123456",
                    "3001234567",
                    "Armenia"
            );

            Bicicleta bicicleta = new Bicicleta(
                    "Trek",
                    TipoBicicleta.MTB,
                    "Roja",
                    "ABC123",
                    2024,
                    cliente
            );

            bicicleta.setMarca("GW");
            bicicleta.setTipo(TipoBicicleta.RUTA);
            bicicleta.setColor("Negra");
            bicicleta.setSerial("XYZ789");
            bicicleta.setAnio(2025);

            assertEquals("GW", bicicleta.getMarca());
            assertEquals(TipoBicicleta.RUTA, bicicleta.getTipo());
            assertEquals("Negra", bicicleta.getColor());
            assertEquals("XYZ789", bicicleta.getSerial());
            assertEquals(2025, bicicleta.getAnio());
        }

        @Test
        void deberiaIniciarSinOrdenesDeServicio() {

            Cliente cliente = new Cliente(
                    "Carlos Perez",
                    "123456",
                    "3001234567",
                    "Armenia"
            );

            Bicicleta bicicleta = new Bicicleta(
                    "Trek",
                    TipoBicicleta.MTB,
                    "Roja",
                    "ABC123",
                    2024,
                    cliente
            );

            assertTrue(bicicleta.getOrdenesServicio().isEmpty());
        }

        @Test
        void deberiaAgregarOrdenDeServicio() {

            Cliente cliente = new Cliente(
                    "Carlos Perez",
                    "123456",
                    "3001234567",
                    "Armenia"
            );

            Bicicleta bicicleta = new Bicicleta(
                    "Trek",
                    TipoBicicleta.MTB,
                    "Roja",
                    "ABC123",
                    2024,
                    cliente
            );

            Mecanico mecanico = new Mecanico(
                    "Juan Gomez",
                    Especialidad.MANTENIMIENTO_GENERAL,
                    "CERT001"
            );

            OrdenServicio orden = new OrdenServicio(
                    LocalDate.of(2026, 9, 13),
                    LocalTime.of(10, 30),
                    null,
                    mecanico,
                    "Mantenimiento general",
                    "Bicicleta en buen estado",
                    "Ajuste de frenos",
                    50000
            );

            bicicleta.agregarOrdenServicio(orden);

            assertEquals(1, bicicleta.getOrdenesServicio().size());
            assertSame(orden, bicicleta.getOrdenesServicio().get(0));
            assertSame(bicicleta, orden.getBicicleta());
        }

        @Test
        void noDeberiaAgregarLaMismaOrdenDosVeces() {

            Cliente cliente = new Cliente(
                    "Carlos Perez",
                    "123456",
                    "3001234567",
                    "Armenia"
            );

            Bicicleta bicicleta = new Bicicleta(
                    "Trek",
                    TipoBicicleta.MTB,
                    "Roja",
                    "ABC123",
                    2024,
                    cliente
            );

            Mecanico mecanico = new Mecanico(
                    "Juan Gomez",
                    Especialidad.MANTENIMIENTO_GENERAL,
                    "CERT001"
            );

            OrdenServicio orden = new OrdenServicio(
                    LocalDate.of(2026, 9, 13),
                    LocalTime.of(10, 30),
                    null,
                    mecanico,
                    "Mantenimiento general",
                    "Bicicleta en buen estado",
                    "Ajuste de frenos",
                    50000
            );

            bicicleta.agregarOrdenServicio(orden);
            bicicleta.agregarOrdenServicio(orden);

            assertEquals(1, bicicleta.getOrdenesServicio().size());
        }

        @Test
        void deberiaMostrarCorrectamenteElToString() {

            Cliente cliente = new Cliente(
                    "Carlos Perez",
                    "123456",
                    "3001234567",
                    "Armenia"
            );

            Bicicleta bicicleta = new Bicicleta(
                    "Trek",
                    TipoBicicleta.MTB,
                    "Roja",
                    "ABC123",
                    2024,
                    cliente
            );

            assertEquals(
                    "Trek - MTB - Serial: ABC123",
                    bicicleta.toString()
            );
        }
    }


