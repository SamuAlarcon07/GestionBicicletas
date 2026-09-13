package com.gestionbicicletas.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepuestoTest {
    @Test
    void crearRepuesto() {

        Repuesto repuesto = new Repuesto(
                "Cadena",
                10,
                3
        );

        assertEquals("Cadena", repuesto.getNombre());
        assertEquals(10, repuesto.getCantidad());
        assertEquals(3, repuesto.getStockMinimo());
    }

    @Test
    void GettersYSetters() {

        Repuesto repuesto = new Repuesto(
                "Cadena",
                10,
                3
        );

        repuesto.setNombre("Freno");
        repuesto.setCantidad(20);
        repuesto.setStockMinimo(5);

        assertEquals("Freno", repuesto.getNombre());
        assertEquals(20, repuesto.getCantidad());
        assertEquals(5, repuesto.getStockMinimo());
    }

    @Test
    void disminuirCantidad() {

        Repuesto repuesto = new Repuesto(
                "Cadena",
                10,
                3
        );

        repuesto.disminuirCantidad(4);

        assertEquals(6, repuesto.getCantidad());
    }

    @Test
    void noDisminuirCantidadCero() {

        Repuesto repuesto = new Repuesto(
                "Cadena",
                10,
                3
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> repuesto.disminuirCantidad(0)
        );

        assertEquals(
                "La cantidad a disminuir debe ser mayor que cero.",
                exception.getMessage()
        );

        assertEquals(10, repuesto.getCantidad());
    }

    @Test
    void noDisminuirCantidadNegativa() {

        Repuesto repuesto = new Repuesto(
                "Cadena",
                10,
                3
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> repuesto.disminuirCantidad(-2)
        );

        assertEquals(
                "La cantidad a disminuir debe ser mayor que cero.",
                exception.getMessage()
        );

        assertEquals(10, repuesto.getCantidad());
    }

    @Test
    void noDisminuirMasCantidadQueElStock() {

        Repuesto repuesto = new Repuesto(
                "Cadena",
                10,
                3
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> repuesto.disminuirCantidad(15)
        );

        assertEquals(
                "No hay suficiente stock del repuesto: Cadena",
                exception.getMessage()
        );

        assertEquals(10, repuesto.getCantidad());
    }

    @Test
    void stockBajoDebeRetornarTrue() {

        Repuesto repuesto = new Repuesto(
                "Cadena",
                3,
                5
        );

        assertTrue(repuesto.stockBajo());
    }

    @Test
    void stockBajoDebeRetornarFalse() {

        Repuesto repuesto = new Repuesto(
                "Cadena",
                10,
                5
        );

        assertFalse(repuesto.stockBajo());
    }

    @Test
    void stockBajoCuandoCantidadEsIgualAlMinimo() {

        Repuesto repuesto = new Repuesto(
                "Cadena",
                5,
                5
        );

        assertTrue(repuesto.stockBajo());
    }

    @Test
    void ToString() {

        Repuesto repuesto = new Repuesto(
                "Cadena",
                10,
                3
        );

        assertEquals(
                "Cadena - Cantidad: 10",
                repuesto.toString()
        );
    }
}