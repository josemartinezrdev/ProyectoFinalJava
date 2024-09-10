package com.proyectofinal.detalles_ventas.domain.entity;

public class DetVenta {
    private int id;
    private int idventa;
    private int total;
    private int idproducto;
    private int cantidad;

    public DetVenta() {
    }

    public DetVenta(int id, int idventa, int total, int idproducto, int cantidad) {
        this.id = id;
        this.idventa = idventa;
        this.total = total;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdventa() {
        return this.idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIdproducto() {
        return this.idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
