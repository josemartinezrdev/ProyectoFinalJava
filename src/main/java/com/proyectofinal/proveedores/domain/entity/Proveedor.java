package com.proyectofinal.proveedores.domain.entity;

public class Proveedor {
    private int id;
    private String nombre;
    private int iddireccion;

    public Proveedor() {
    }

    public Proveedor(int id, String nombre, int iddireccion) {
        this.id = id;
        this.nombre = nombre;
        this.iddireccion = iddireccion;
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

    public int getIddireccion() {
        return this.iddireccion;
    }

    public void setIddireccion(int iddireccion) {
        this.iddireccion = iddireccion;
    }

}
