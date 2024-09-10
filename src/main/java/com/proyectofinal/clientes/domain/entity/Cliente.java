package com.proyectofinal.clientes.domain.entity;

public class Cliente {
    private String id;
    private String nombre;
    private String apellido;
    private int idtipocliente;
    private int iddireccion;

    public Cliente() {
    }

    public Cliente(String id, String nombre, String apellido, int idtipocliente, int iddireccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.idtipocliente = idtipocliente;
        this.iddireccion = iddireccion;
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

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getIdtipocliente() {
        return this.idtipocliente;
    }

    public void setIdtipocliente(int idtipocliente) {
        this.idtipocliente = idtipocliente;
    }

    public int getIddireccion() {
        return this.iddireccion;
    }

    public void setIddireccion(int iddireccion) {
        this.iddireccion = iddireccion;
    }

}
