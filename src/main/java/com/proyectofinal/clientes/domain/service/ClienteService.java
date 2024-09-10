package com.proyectofinal.clientes.domain.service;

import java.util.List;

import com.proyectofinal.clientes.domain.entity.Cliente;

public interface ClienteService {
    public void createCliente(Cliente cliente);

    public void updateCliente(Cliente cliente, String id);

    public void deleteCliente(String id);

    public List<Cliente> findAllClientes();

    public Cliente findByIdCliente(String id);
}
