package com.proyectofinal.detalles_pedidos.domain.entity;

public class DetPed {
    private int id;
    private int idproducto;
    private int idpedido;
    private int idproveedor;

    public DetPed() {
    }

    public DetPed(int id, int idproducto, int idpedido, int idproveedor) {
        this.id = id;
        this.idproducto = idproducto;
        this.idpedido = idpedido;
        this.idproveedor = idproveedor;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdproducto() {
        return this.idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdpedido() {
        return this.idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public int getIdproveedor() {
        return this.idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

}
