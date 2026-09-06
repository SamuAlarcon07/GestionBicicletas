package com.gestionbicicletas.model;

public class Repuesto {

    private String nombre;
    private int cantidad;
    private int stockMinimo;

    public Repuesto(String nombre, int cantidad, int stockMinimo) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.stockMinimo = stockMinimo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public boolean stockBajo() {
        return cantidad <= stockMinimo;
    }

    @Override
    public String toString() {
        return nombre + " - Cantidad: " + cantidad;
    }
}