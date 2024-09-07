package com.proyectofinal.direcciones.domain.entity;

public class Direccion {
    private int id;
    private String calle;
    private String carrera;
    private int idciudad;

    public Direccion() {
    }

    public Direccion(int id, String calle, String carrera, int idciudad) {
        this.id = id;
        this.calle = calle;
        this.carrera = carrera;
        this.idciudad = idciudad;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalle() {
        return this.calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCarrera() {
        return this.carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getIdciudad() {
        return this.idciudad;
    }

    public void setIdciudad(int idciudad) {
        this.idciudad = idciudad;
    }

}
