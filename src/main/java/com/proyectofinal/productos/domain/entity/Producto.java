package com.proyectofinal.productos.domain.entity;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int idcategoria;

    public Producto() {
    }

    public Producto(int id, String nombre, double precio, int idcategoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.idcategoria = idcategoria;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdcategoria() {
        return this.idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

}
