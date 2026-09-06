package com.gestionbicicletas.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String nombreCompleto;
    private String identificacion;
    private String telefono;
    private String direccion;
    private List<Bicicleta> bicicletas;

    public Cliente(String nombreCompleto, String identificacion,
                   String telefono, String direccion) {

        this.nombreCompleto = nombreCompleto;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.direccion = direccion;
        this.bicicletas = new ArrayList<>();
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Bicicleta> getBicicletas() {
        return bicicletas;
    }

    public void agregarBicicleta(Bicicleta bicicleta) {
        if (!bicicletas.contains(bicicleta)) {
            bicicletas.add(bicicleta);
            bicicleta.setCliente(this);
        }
    }

    @Override
    public String toString() {
        return nombreCompleto + " - " + identificacion;
    }
}