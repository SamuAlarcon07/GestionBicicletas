package com.gestionbicicletas.model;

public class Mecanico {

    private String nombreCompleto;
    private String especialidad;
    private String codigoCertificacion;

    public Mecanico(String nombreCompleto, String especialidad,
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
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