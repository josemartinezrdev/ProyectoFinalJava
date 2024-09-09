package com.proyectofinal.detalle_compras.domain.entity;

public class DetCom {
    private int idcompra;
    private int idpedido;

    public DetCom() {
    }

    public DetCom(int idcompra, int idpedido) {
        this.idcompra = idcompra;
        this.idpedido = idpedido;
    }

    public int getIdcompra() {
        return this.idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public int getIdpedido() {
        return this.idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

}
