package com.proyectofinal.ventas.domain.entity;

import java.sql.Date;

public class Venta {
    private int id;
    private Date fecha;
    private double total;
    private String idempleado;
    private String idcliente;

    public Venta() {
    }

    public Venta(int id, Date fecha, double total, String idempleado, String idcliente) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.idempleado = idempleado;
        this.idcliente = idcliente;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getIdempleado() {
        return this.idempleado;
    }

    public void setIdempleado(String idempleado) {
        this.idempleado = idempleado;
    }

    public String getIdcliente() {
        return this.idcliente;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

}
