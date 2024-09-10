package com.proyectofinal.telefonos_clientes.domain.service;

import java.util.List;

import com.proyectofinal.telefonos_clientes.domain.entity.TelCliente;

public interface TelClienteService {
    public void createProducto(TelCliente telcliente);

    public void updateProducto(TelCliente telcliente);

    public void deleteProducto(int id);

    public List<TelCliente> findAllProductos();

    public TelCliente findByIdProducto(int id);
}
