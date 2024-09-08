package com.proyectofinal.sucursales.domain.entity;

public class Sucursal {
    private int id;
    private String nombre;
    private int iddireccion;
    private int idempresa;

    public Sucursal() {
    }

    public Sucursal(int id, String nombre, int iddireccion, int idempresa) {
        this.id = id;
        this.nombre = nombre;
        this.iddireccion = iddireccion;
        this.idempresa = idempresa;
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

    public int getIdempresa() {
        return this.idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

}
