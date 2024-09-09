package com.proyectofinal.productos_bodegas.domain.entity;

public class ProdBod {
    private int idproducto;
    private int idbodega;

    public ProdBod() {
    }

    public ProdBod(int idproducto, int idbodega) {
        this.idproducto = idproducto;
        this.idbodega = idbodega;
    }

    public int getIdproducto() {
        return this.idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdbodega() {
        return this.idbodega;
    }

    public void setIdbodega(int idbodega) {
        this.idbodega = idbodega;
    }

}
