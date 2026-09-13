package com.gestionbicicletas.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepuestoTest { @Test
void CrearRepuesto() {

    Repuesto repuesto = new Repuesto(
            "Pastillas de freno",
            10,
            3
    );

    assertEquals("Pastillas de freno", repuesto.getNombre());
    assertEquals(10, repuesto.getCantidad());
    assertEquals(3, repuesto.getStockMinimo());
}

    @Test
    void CambiarElNombre() {

        Repuesto repuesto = new Repuesto(
                "Pastillas de freno",
                10,
                3
        );

        repuesto.setNombre("Cadena");

        assertEquals("Cadena", repuesto.getNombre());
    }

    @Test
    void CambiarLaCantidad() {

        Repuesto repuesto = new Repuesto(
                "Pastillas de freno",
                10,
                3
        );

        repuesto.setCantidad(20);

        assertEquals(20, repuesto.getCantidad());
    }

    @Test
    void CambiarElStockMinimo() {

        Repuesto repuesto = new Repuesto(
                "Pastillas de freno",
                10,
                3
        );

        repuesto.setStockMinimo(5);

        assertEquals(5, repuesto.getStockMinimo());
    }

    @Test
    void DisminuirLaCantidad() {

        Repuesto repuesto = new Repuesto(
                "Pastillas de freno",
                10,
                3
        );

        repuesto.disminuirCantidad(4);

        assertEquals(6, repuesto.getCantidad());
    }

    @Test
    void noDisminuirCantidadCero() {

        Repuesto repuesto = new Repuesto(
                "Pastillas de freno",
                10,
                3
        );

        IllegalArgumentException excepcion = assertThrows(
                IllegalArgumentException.class,
                () -> repuesto.disminuirCantidad(0)
        );

        assertEquals(
                "La cantidad a disminuir debe ser mayor que cero.",
                excepcion.getMessage()
        );
    }

    @Test
    void noDisminuirCantidadNegativa() {

        Repuesto repuesto = new Repuesto(
                "Pastillas de freno",
                10,
                3
        );

        IllegalArgumentException excepcion = assertThrows(
                IllegalArgumentException.class,
                () -> repuesto.disminuirCantidad(-2)
        );

        assertEquals(
                "La cantidad a disminuir debe ser mayor que cero.",
                excepcion.getMessage()
        );
    }

    @Test
    void noDisminuirMasStockDelDisponible() {

        Repuesto repuesto = new Repuesto(
                "Pastillas de freno",
                10,
                3
        );

        IllegalArgumentException excepcion = assertThrows(
                IllegalArgumentException.class,
                () -> repuesto.disminuirCantidad(15)
        );

        assertEquals(
                "No hay suficiente stock del repuesto: Pastillas de freno",
                excepcion.getMessage()
        );
    }

    @Test
    void DisminuirTodoElStock() {

        Repuesto repuesto = new Repuesto(
                "Pastillas de freno",
                10,
                3
        );

        repuesto.disminuirCantidad(10);

        assertEquals(0, repuesto.getCantidad());
    }

    @Test
    void elStockEstaBajoCuandoEsIgualAlMinimo() {

        Repuesto repuesto = new Repuesto(
                "Pastillas de freno",
                3,
                3
        );

        assertTrue(repuesto.stockBajo());
    }

    @Test
    void elStockEstaBajoCuandoEsMenorAlMinimo() {

        Repuesto repuesto = new Repuesto(
                "Pastillas de freno",
                2,
                3
        );

        assertTrue(repuesto.stockBajo());
    }

    @Test
    void elStockNoEstaBajo() {

        Repuesto repuesto = new Repuesto(
                "Pastillas de freno",
                10,
                3
        );

        assertFalse(repuesto.stockBajo());
    }

    @Test
    void mostrarCorrectamenteElToString() {

        Repuesto repuesto = new Repuesto(
                "Pastillas de freno",
                10,
                3
        );

        assertEquals(
                "Pastillas de freno - Cantidad: 10",
                repuesto.toString()
        );
    }
}

