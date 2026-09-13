package com.gestionbicicletas.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class OrdenServicioTest {
    @Test
    void crearOrdenServicio() {

        LocalDate fecha = LocalDate.of(2026, 9, 13);
        LocalTime hora = LocalTime.of(10, 30);

        OrdenServicio orden = new OrdenServicio(
                fecha,
                hora,
                null,
                null,
                "Cambio de cadena",
                "Cadena desgastada",
                "Se realizó cambio de cadena",
                50000
        );

        assertEquals(fecha, orden.getFechaIngreso());
        assertEquals(hora, orden.getHoraIngreso());
        assertNull(orden.getBicicleta());
        assertNull(orden.getMecanico());
        assertEquals("Cambio de cadena", orden.getMotivoServicio());
        assertEquals("Cadena desgastada", orden.getDiagnostico());
        assertEquals("Se realizó cambio de cadena", orden.getTrabajosRealizados());
        assertEquals(50000, orden.getCostoTotal());
        assertNotNull(orden.getRepuestosUtilizados());
        assertTrue(orden.getRepuestosUtilizados().isEmpty());
    }

    @Test
    void GettersYSetters() {

        OrdenServicio orden = new OrdenServicio(
                LocalDate.of(2026, 9, 13),
                LocalTime.of(10, 30),
                null,
                null,
                "Revisión",
                "Problema en frenos",
                "Revisión de frenos",
                30000
        );

        LocalDate nuevaFecha = LocalDate.of(2026, 9, 14);
        LocalTime nuevaHora = LocalTime.of(14, 45);

        orden.setFechaIngreso(nuevaFecha);
        orden.setHoraIngreso(nuevaHora);
        orden.setMotivoServicio("Cambio de frenos");
        orden.setDiagnostico("Pastillas desgastadas");
        orden.setTrabajosRealizados("Se cambiaron las pastillas");
        orden.setCostoTotal(75000);

        assertEquals(nuevaFecha, orden.getFechaIngreso());
        assertEquals(nuevaHora, orden.getHoraIngreso());
        assertEquals("Cambio de frenos", orden.getMotivoServicio());
        assertEquals("Pastillas desgastadas", orden.getDiagnostico());
        assertEquals(
                "Se cambiaron las pastillas",
                orden.getTrabajosRealizados()
        );
        assertEquals(75000, orden.getCostoTotal());
    }

    @Test
    void agregarRepuesto() {

        OrdenServicio orden = new OrdenServicio(
                LocalDate.of(2026, 9, 13),
                LocalTime.of(10, 30),
                null,
                null,
                "Cambio de cadena",
                "Cadena dañada",
                "Cambio de cadena",
                50000
        );

        Repuesto repuesto = new Repuesto(
                "Cadena",
                10,
                3
        );

        orden.agregarRepuesto(repuesto, 2);

        assertEquals(1, orden.getRepuestosUtilizados().size());

        DetalleRepuesto detalle =
                orden.getRepuestosUtilizados().get(0);

        assertEquals(repuesto, detalle.getRepuesto());
        assertEquals(2, detalle.getCantidadUtilizada());
    }

    @Test
    void agregarVariosRepuestos() {

        OrdenServicio orden = new OrdenServicio(
                LocalDate.of(2026, 9, 13),
                LocalTime.of(10, 30),
                null,
                null,
                "Mantenimiento general",
                "Varios componentes desgastados",
                "Mantenimiento completo",
                100000
        );

        Repuesto cadena = new Repuesto(
                "Cadena",
                10,
                3
        );

        Repuesto frenos = new Repuesto(
                "Pastillas de freno",
                20,
                5
        );

        orden.agregarRepuesto(cadena, 2);
        orden.agregarRepuesto(frenos, 4);

        assertEquals(2, orden.getRepuestosUtilizados().size());

        assertEquals(
                cadena,
                orden.getRepuestosUtilizados()
                        .get(0)
                        .getRepuesto()
        );

        assertEquals(
                2,
                orden.getRepuestosUtilizados()
                        .get(0)
                        .getCantidadUtilizada()
        );

        assertEquals(
                frenos,
                orden.getRepuestosUtilizados()
                        .get(1)
                        .getRepuesto()
        );

        assertEquals(
                4,
                orden.getRepuestosUtilizados()
                        .get(1)
                        .getCantidadUtilizada()
        );
    }

    @Test
    void noAgregarRepuestoConCantidadCero() {

        OrdenServicio orden = new OrdenServicio(
                LocalDate.of(2026, 9, 13),
                LocalTime.of(10, 30),
                null,
                null,
                "Mantenimiento",
                "Revisión",
                "Revisión general",
                30000
        );

        Repuesto repuesto = new Repuesto(
                "Cadena",
                10,
                3
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> orden.agregarRepuesto(repuesto, 0)
        );

        assertTrue(
                orden.getRepuestosUtilizados().isEmpty()
        );
    }

    @Test
    void noAgregarRepuestoNulo() {

        OrdenServicio orden = new OrdenServicio(
                LocalDate.of(2026, 9, 13),
                LocalTime.of(10, 30),
                null,
                null,
                "Mantenimiento",
                "Revisión",
                "Revisión general",
                30000
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> orden.agregarRepuesto(null, 2)
        );

        assertTrue(
                orden.getRepuestosUtilizados().isEmpty()
        );
    }
}