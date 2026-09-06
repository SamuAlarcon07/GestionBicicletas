package com.gestionbicicletas.service;

import com.gestionbicicletas.model.Bicicleta;
import com.gestionbicicletas.model.Cliente;
import com.gestionbicicletas.model.Mecanico;
import com.gestionbicicletas.model.OrdenServicio;
import com.gestionbicicletas.model.Repuesto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionTaller {

    private List<Cliente> clientes;
    private List<Bicicleta> bicicletas;
    private List<Mecanico> mecanicos;
    private List<OrdenServicio> ordenesServicio;
    private List<Repuesto> repuestos;

    public GestionTaller() {
        clientes = new ArrayList<>();
        bicicletas = new ArrayList<>();
        mecanicos = new ArrayList<>();
        ordenesServicio = new ArrayList<>();
        repuestos = new ArrayList<>();
    }

    public void registrarCliente(Cliente cliente) {

        if (buscarClientePorIdentificacion(
                cliente.getIdentificacion()) != null) {

            throw new IllegalArgumentException(
                    "Ya existe un cliente con esa identificación."
            );
        }

        clientes.add(cliente);
    }

    public Cliente buscarClientePorIdentificacion(String identificacion) {

        for (Cliente cliente : clientes) {

            if (cliente.getIdentificacion().equals(identificacion)) {
                return cliente;
            }
        }

        return null;
    }

    public void eliminarCliente(Cliente cliente) {

        if (cliente == null) {
            return;
        }

        if (!cliente.getBicicletas().isEmpty()) {
            throw new IllegalArgumentException(
                    "No se puede eliminar el cliente porque tiene bicicletas asociadas."
            );
        }

        clientes.remove(cliente);
    }

    public void registrarBicicleta(Bicicleta bicicleta) {

        if (buscarBicicletaPorSerial(bicicleta.getSerial()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe una bicicleta con ese serial."
            );
        }

        if (bicicleta.getCliente() == null) {
            throw new IllegalArgumentException(
                    "La bicicleta debe estar asociada a un cliente."
            );
        }

        bicicletas.add(bicicleta);

        Cliente cliente = bicicleta.getCliente();

        if (!cliente.getBicicletas().contains(bicicleta)) {
            cliente.agregarBicicleta(bicicleta);
        }
    }

    public Bicicleta buscarBicicletaPorSerial(String serial) {

        for (Bicicleta bicicleta : bicicletas) {

            if (bicicleta.getSerial().equalsIgnoreCase(serial)) {
                return bicicleta;
            }
        }

        return null;
    }

    public void eliminarBicicleta(Bicicleta bicicleta) {

        if (bicicleta == null) {
            return;
        }

        if (!bicicleta.getOrdenesServicio().isEmpty()) {
            throw new IllegalArgumentException(
                    "No se puede eliminar la bicicleta porque tiene órdenes de servicio."
            );
        }

        Cliente cliente = bicicleta.getCliente();

        if (cliente != null) {
            cliente.getBicicletas().remove(bicicleta);
        }

        bicicletas.remove(bicicleta);
    }

    public void registrarMecanico(Mecanico mecanico) {

        if (buscarMecanicoPorCodigo(
                mecanico.getCodigoCertificacion()) != null) {

            throw new IllegalArgumentException(
                    "Ya existe un mecánico con ese código."
            );
        }

        mecanicos.add(mecanico);
    }

    public Mecanico buscarMecanicoPorCodigo(String codigo) {

        for (Mecanico mecanico : mecanicos) {

            if (mecanico.getCodigoCertificacion().equalsIgnoreCase(codigo)) {
                return mecanico;
            }
        }

        return null;
    }

    public void eliminarMecanico(Mecanico mecanico) {

        if (mecanico == null) {
            return;
        }

        for (OrdenServicio orden : ordenesServicio) {

            if (orden.getMecanico() == mecanico) {
                throw new IllegalArgumentException(
                        "No se puede eliminar el mecánico porque tiene órdenes de servicio."
                );
            }
        }

        mecanicos.remove(mecanico);
    }

    public void registrarOrdenServicio(OrdenServicio orden) {

        if (orden.getBicicleta() == null) {
            throw new IllegalArgumentException(
                    "La orden debe tener una bicicleta."
            );
        }

        if (orden.getMecanico() == null) {
            throw new IllegalArgumentException(
                    "La orden debe tener un mecánico."
            );
        }

        ordenesServicio.add(orden);

        Bicicleta bicicleta = orden.getBicicleta();

        if (!bicicleta.getOrdenesServicio().contains(orden)) {
            bicicleta.agregarOrdenServicio(orden);
        }
    }

    public List<OrdenServicio> buscarHistorialPorSerial(String serial) {

        Bicicleta bicicleta = buscarBicicletaPorSerial(serial);

        if (bicicleta == null) {
            return new ArrayList<>();
        }

        return bicicleta.getOrdenesServicio();
    }

    public List<OrdenServicio> buscarOrdenesPorFecha(LocalDate fecha) {

        List<OrdenServicio> resultado = new ArrayList<>();

        for (OrdenServicio orden : ordenesServicio) {

            if (orden.getFechaIngreso().equals(fecha)) {
                resultado.add(orden);
            }
        }

        return resultado;
    }

    public void registrarRepuesto(Repuesto repuesto) {
        repuestos.add(repuesto);
    }

    public List<Repuesto> obtenerRepuestosStockBajo() {

        List<Repuesto> resultado = new ArrayList<>();

        for (Repuesto repuesto : repuestos) {

            if (repuesto.stockBajo()) {
                resultado.add(repuesto);
            }
        }

        return resultado;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Bicicleta> getBicicletas() {
        return bicicletas;
    }

    public List<Mecanico> getMecanicos() {
        return mecanicos;
    }

    public List<OrdenServicio> getOrdenesServicio() {
        return ordenesServicio;
    }

    public List<Repuesto> getRepuestos() {
        return repuestos;
    }
}