package com.proyectofinal.empleados.domain.entity;

public class Empleado {
    private String id;
    private String nombre;
    private int idsucursal;

    public Empleado() {
    }

    public Empleado(String id, String nombre, int idsucursal) {
        this.id = id;
        this.nombre = nombre;
        this.idsucursal = idsucursal;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdsucursal() {
        return this.idsucursal;
    }

    public void setIdsucursal(int idsucursal) {
        this.idsucursal = idsucursal;
    }

}
