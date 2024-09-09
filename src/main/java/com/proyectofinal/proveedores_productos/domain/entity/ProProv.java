package com.proyectofinal.proveedores_productos.domain.entity;

public class ProProv {
    private int idproducto;
    private int idproveedor;

    public ProProv() {
    }

    public ProProv(int idproducto, int idproveedor) {
        this.idproducto = idproducto;
        this.idproveedor = idproveedor;
    }

    public int getIdproducto() {
        return this.idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdproveedor() {
        return this.idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

}
