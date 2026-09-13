package com.gestionbicicletas.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DetalleRepuestoTest {
    @Test
    void crearDetalleRepuesto() {

        Repuesto repuesto = new Repuesto("Cadena", 10, 3);

        DetalleRepuesto detalle = new DetalleRepuesto(repuesto, 2);

        assertEquals(repuesto, detalle.getRepuesto());
        assertEquals(2, detalle.getCantidadUtilizada());
    }

    @Test
    void noPermitirRepuestoNulo() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new DetalleRepuesto(null, 2)
        );

        assertEquals(
                "El repuesto no puede ser nulo.",
                exception.getMessage()
        );
    }

    @Test
    void noPermitirCantidadCero() {

        Repuesto repuesto = new Repuesto("Cadena", 10, 3);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new DetalleRepuesto(repuesto, 0)
        );

        assertEquals(
                "La cantidad utilizada debe ser mayor que cero.",
                exception.getMessage()
        );
    }

    @Test
    void noPermitirCantidadNegativa() {

        Repuesto repuesto = new Repuesto("Cadena", 10, 3);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new DetalleRepuesto(repuesto, -2)
        );

        assertEquals(
                "La cantidad utilizada debe ser mayor que cero.",
                exception.getMessage()
        );
    }

    @Test
    void gettersYSetters() {

        Repuesto repuesto1 = new Repuesto("Cadena", 10, 3);
        Repuesto repuesto2 = new Repuesto("Freno", 5, 2);

        DetalleRepuesto detalle = new DetalleRepuesto(repuesto1, 2);

        detalle.setRepuesto(repuesto2);
        detalle.setCantidadUtilizada(4);

        assertEquals(repuesto2, detalle.getRepuesto());
        assertEquals(4, detalle.getCantidadUtilizada());
    }

    @Test
    void probarToString() {

        Repuesto repuesto = new Repuesto("Cadena", 10, 3);

        DetalleRepuesto detalle = new DetalleRepuesto(repuesto, 3);

        assertEquals(
                "Cadena - Cantidad utilizada: 3",
                detalle.toString()
        );
    }
}