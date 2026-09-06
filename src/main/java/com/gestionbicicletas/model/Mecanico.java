package com.gestionbicicletas.model;

public class Mecanico {

    private String nombreCompleto;
    private Especialidad especialidad;
    private String codigoCertificacion;

    public Mecanico(String nombreCompleto, Especialidad especialidad,
                    String codigoCertificacion) {

        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
        this.codigoCertificacion = codigoCertificacion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public String getCodigoCertificacion() {
        return codigoCertificacion;
    }

    public void setCodigoCertificacion(String codigoCertificacion) {
        this.codigoCertificacion = codigoCertificacion;
    }

    @Override
    public String toString() {
        return nombreCompleto + " - " + especialidad;
    }
}