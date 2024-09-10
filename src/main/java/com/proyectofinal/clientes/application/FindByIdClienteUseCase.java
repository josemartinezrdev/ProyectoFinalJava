package com.proyectofinal.clientes.application;

import com.proyectofinal.clientes.domain.entity.Cliente;
import com.proyectofinal.clientes.domain.service.ClienteService;

public class FindByIdClienteUseCase {
    private final ClienteService clienteService;

    public FindByIdClienteUseCase(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public Cliente execute(String id) {
        return clienteService.findByIdCliente(id);
    }
}
