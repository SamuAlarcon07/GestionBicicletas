package com.gestionbicicletas.model;

public class DetalleRepuesto {

    private Repuesto repuesto;
    private int cantidadUtilizada;

    public DetalleRepuesto(Repuesto repuesto, int cantidadUtilizada) {

        if (repuesto == null) {
            throw new IllegalArgumentException(
                    "El repuesto no puede ser nulo."
            );
        }

        if (cantidadUtilizada <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad utilizada debe ser mayor que cero."
            );
        }

        this.repuesto = repuesto;
        this.cantidadUtilizada = cantidadUtilizada;
    }

    public Repuesto getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }

    public int getCantidadUtilizada() {
        return cantidadUtilizada;
    }

    public void setCantidadUtilizada(int cantidadUtilizada) {
        this.cantidadUtilizada = cantidadUtilizada;
    }

    @Override
    public String toString() {
        return repuesto.getNombre()
                + " - Cantidad utilizada: "
                + cantidadUtilizada;
    }
}