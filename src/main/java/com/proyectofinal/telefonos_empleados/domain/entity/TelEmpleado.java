package com.proyectofinal.telefonos_empleados.domain.entity;

public class TelEmpleado {

    private int id;
    private String telefono;
    private String idempleado;

    public TelEmpleado() {
    }

    public TelEmpleado(int id, String telefono, String idempleado) {
        this.id = id;
        this.telefono = telefono;
        this.idempleado = idempleado;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIdempleado() {
        return this.idempleado;
    }

    public void setIdempleado(String idempleado) {
        this.idempleado = idempleado;
    }

}
