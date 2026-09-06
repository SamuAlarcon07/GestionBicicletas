package com.gestionbicicletas.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class OrdenServicio {

    private LocalDate fechaIngreso;
    private LocalTime horaIngreso;
    private Bicicleta bicicleta;
    private Mecanico mecanico;
    private String motivoServicio;
    private String diagnostico;
    private String trabajosRealizados;
    private double costoTotal;

    public OrdenServicio(LocalDate fechaIngreso,
                         LocalTime horaIngreso,
                         Bicicleta bicicleta,
                         Mecanico mecanico,
                         String motivoServicio,
                         String diagnostico,
                         String trabajosRealizados,
                         double costoTotal) {

        this.fechaIngreso = fechaIngreso;
        this.horaIngreso = horaIngreso;
        this.bicicleta = bicicleta;
        this.mecanico = mecanico;
        this.motivoServicio = motivoServicio;
        this.diagnostico = diagnostico;
        this.trabajosRealizados = trabajosRealizados;
        this.costoTotal = costoTotal;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalTime getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(Bicicleta bicicleta) {
        this.bicicleta = bicicleta;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public String getMotivoServicio() {
        return motivoServicio;
    }

    public void setMotivoServicio(String motivoServicio) {
        this.motivoServicio = motivoServicio;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTrabajosRealizados() {
        return trabajosRealizados;
    }

    public void setTrabajosRealizados(String trabajosRealizados) {
        this.trabajosRealizados = trabajosRealizados;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }
}