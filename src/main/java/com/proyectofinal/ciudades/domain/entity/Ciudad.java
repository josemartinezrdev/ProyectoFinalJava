package com.proyectofinal.ciudades.domain.entity;

public class Ciudad {
    private int id;
    private String nombre;
    private int idpais;

    public Ciudad() {
    }

    public Ciudad(int id, String nombre, int idpais) {
        this.id = id;
        this.nombre = nombre;
        this.idpais = idpais;
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

    public int getIdpais() {
        return this.idpais;
    }

    public void setIdpais(int idpais) {
        this.idpais = idpais;
    }

}
