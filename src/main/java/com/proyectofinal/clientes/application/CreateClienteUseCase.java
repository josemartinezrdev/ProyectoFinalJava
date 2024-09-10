package com.proyectofinal.clientes.application;

import com.proyectofinal.clientes.domain.entity.Cliente;
import com.proyectofinal.clientes.domain.service.ClienteService;

public class CreateClienteUseCase {
    private final ClienteService clienteService;

    public CreateClienteUseCase(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public void execute(Cliente cliente) {
        clienteService.createCliente(cliente);
    }
}
