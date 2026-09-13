package com.gestionbicicletas.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MecanicoTest {

    @Test
    void deberiaCrearMecanicoCorrectamente() {

        Mecanico mecanico = new Mecanico(
                "Carlos Perez",
                Especialidad.MANTENIMIENTO_GENERAL,
                "CERT001"
        );

        assertEquals("Carlos Perez", mecanico.getNombreCompleto());
        assertEquals(Especialidad.MANTENIMIENTO_GENERAL, mecanico.getEspecialidad());
        assertEquals("CERT001", mecanico.getCodigoCertificacion());
    }

    @Test
    void deberiaActualizarLosDatosDelMecanico() {

        Mecanico mecanico = new Mecanico(
                "Carlos Perez",
                Especialidad.BICICLETAS_ELECTRICAS,
                "CERT001"
        );

        mecanico.setNombreCompleto("Juan Gomez");
        mecanico.setEspecialidad(Especialidad.FRENOS_Y_TRANSMISION);
        mecanico.setCodigoCertificacion("CERT002");

        assertEquals("Juan Gomez", mecanico.getNombreCompleto());
        assertEquals(Especialidad.FRENOS_Y_TRANSMISION, mecanico.getEspecialidad());
        assertEquals("CERT002", mecanico.getCodigoCertificacion());
    }

    @Test
    void deberiaMostrarCorrectamenteElToString() {

        Mecanico mecanico = new Mecanico(
                "Carlos Perez",
                Especialidad. MANTENIMIENTO_GENERAL,
                "CERT001"
        );

        assertEquals(
                "Carlos Perez - " + Especialidad. MANTENIMIENTO_GENERAL,
                mecanico.toString()
        );
    }
}