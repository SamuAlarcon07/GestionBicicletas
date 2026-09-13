package com.gestionbicicletas.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DetalleRepuestoTest {

    @Test
    void deberiaCrearRepuestoCorrectamente() {

        Repuesto repuesto = new Repuesto("Pastillas de freno", 10, 3);

        assertEquals("Pastillas de freno", repuesto.getNombre());
        assertEquals(10, repuesto.getCantidad());
        assertEquals(3, repuesto.getStockMinimo());
    }

    @Test
    void deberiaActualizarLosDatosDelRepuesto() {

        Repuesto repuesto = new Repuesto("Pastillas de freno", 10, 3);

        repuesto.setNombre("Cadena");
        repuesto.setCantidad(20);
        repuesto.setStockMinimo(5);

        assertEquals("Cadena", repuesto.getNombre());
        assertEquals(20, repuesto.getCantidad());
        assertEquals(5, repuesto.getStockMinimo());
    }

    @Test
    void deberiaDisminuirLaCantidad() {

        Repuesto repuesto = new Repuesto("Pastillas de freno", 10, 3);

        repuesto.disminuirCantidad(4);

        assertEquals(6, repuesto.getCantidad());
    }

    @Test
    void noDeberiaPermitirCantidadCeroONegativa() {

        Repuesto repuesto = new Repuesto("Pastillas de freno", 10, 3);

        assertThrows(IllegalArgumentException.class, () -> {
            repuesto.disminuirCantidad(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            repuesto.disminuirCantidad(-2);
        });
    }

    @Test
    void noDeberiaPermitirDisminuirMasStockDelDisponible() {

        Repuesto repuesto = new Repuesto("Pastillas de freno", 10, 3);

        assertThrows(IllegalArgumentException.class, () -> {
            repuesto.disminuirCantidad(15);
        });
    }

    @Test
    void deberiaIndicarSiElStockEstaBajo() {

        Repuesto repuesto = new Repuesto("Pastillas de freno", 3, 3);

        assertTrue(repuesto.stockBajo());
    }

    @Test
    void deberiaIndicarSiElStockNoEstaBajo() {

        Repuesto repuesto = new Repuesto("Pastillas de freno", 10, 3);

        assertFalse(repuesto.stockBajo());
    }

    @Test
    void deberiaMostrarCorrectamenteElToString() {

        Repuesto repuesto = new Repuesto("Pastillas de freno", 10, 3);

        assertEquals(
                "Pastillas de freno - Cantidad: 10",
                repuesto.toString()
        );
    }
}
