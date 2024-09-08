package com.proyectofinal.pedidos.domain.entity;

public class Pedido {

    private int id;
    private double total;
    private int idestado;

    public Pedido() {
    }

    public Pedido(int id, double total, int idestado) {
        this.id = id;
        this.total = total;
        this.idestado = idestado;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdestado() {
        return this.idestado;
    }

    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }

}
