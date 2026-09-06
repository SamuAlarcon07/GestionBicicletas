package com.gestionbicicletas.model;

import java.util.ArrayList;
import java.util.List;

public class Bicicleta {

    private String marca;
    private String tipo;
    private String color;
    private String serial;
    private int anio;
    private Cliente cliente;
    private List<OrdenServicio> ordenesServicio;

    public Bicicleta(String marca, String tipo, String color,
                     String serial, int anio, Cliente cliente) {

        this.marca = marca;
        this.tipo = tipo;
        this.color = color;
        this.serial = serial;
        this.anio = anio;
        this.cliente = cliente;
        this.ordenesServicio = new ArrayList<>();
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<OrdenServicio> getOrdenesServicio() {
        return ordenesServicio;
    }

    public void agregarOrdenServicio(OrdenServicio orden) {
        ordenesServicio.add(orden);
    }

    @Override
    public String toString() {
        return marca + " - " + tipo + " - Serial: " + serial;
    }
}