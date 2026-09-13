package com.gestionbicicletas.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {
    @Test
    void CrearClienteCorrectamente() {

        Cliente cliente = new Cliente(
                "Carlos Perez",
                "123456",
                "3001234567",
                "Armenia"
        );

        assertEquals("Carlos Perez", cliente.getNombreCompleto());
        assertEquals("123456", cliente.getIdentificacion());
        assertEquals("3001234567", cliente.getTelefono());
        assertEquals("Armenia", cliente.getDireccion());
    }

    @Test
    void ActualizarLosDatosDelCliente() {

        Cliente cliente = new Cliente(
                "Carlos Perez",
                "123456",
                "3001234567",
                "Armenia"
        );

        cliente.setNombreCompleto("Juan Gomez");
        cliente.setIdentificacion("789456");
        cliente.setTelefono("3119876543");
        cliente.setDireccion("Bogota");

        assertEquals("Juan Gomez", cliente.getNombreCompleto());
        assertEquals("789456", cliente.getIdentificacion());
        assertEquals("3119876543", cliente.getTelefono());
        assertEquals("Bogota", cliente.getDireccion());
    }

    @Test
    void IniciarSinBicicletas() {

        Cliente cliente = new Cliente(
                "Carlos Perez",
                "123456",
                "3001234567",
                "Armenia"
        );

        assertTrue(cliente.getBicicletas().isEmpty());
    }

    @Test
    void AgregarBicicleta() {

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
                null
        );

        cliente.agregarBicicleta(bicicleta);

        assertEquals(1, cliente.getBicicletas().size());
        assertSame(bicicleta, cliente.getBicicletas().get(0));
        assertSame(cliente, bicicleta.getCliente());
    }

    @Test
    void noDeberiaAgregarLaMismaBicicletaDosVeces() {

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
                null
        );

        cliente.agregarBicicleta(bicicleta);
        cliente.agregarBicicleta(bicicleta);

        assertEquals(1, cliente.getBicicletas().size());
    }

    @Test
    void MostrarCorrectamenteElToString() {

        Cliente cliente = new Cliente(
                "Carlos Perez",
                "123456",
                "3001234567",
                "Armenia"
        );

        assertEquals(
                "Carlos Perez - 123456",
                cliente.toString()
        );
    }
}