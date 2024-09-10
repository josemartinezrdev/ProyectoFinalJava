package com.proyectofinal.telefonos_clientes.domain.entity;

public class TelCliente {

    private int id;
    private String telefono;
    private String idcliente;

    public TelCliente() {
    }

    public TelCliente(int id, String telefono, String idcliente) {
        this.id = id;
        this.telefono = telefono;
        this.idcliente = idcliente;
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

    public String getIdcliente() {
        return this.idcliente;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

}
