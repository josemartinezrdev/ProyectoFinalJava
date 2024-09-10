package com.proyectofinal.clientes.application;

import com.proyectofinal.clientes.domain.entity.Cliente;
import com.proyectofinal.clientes.domain.service.ClienteService;

public class UpdateClienteUseCase {
    private final ClienteService clienteService;

    public UpdateClienteUseCase(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public void execute(Cliente cliente, String id) {
        clienteService.updateCliente(cliente, id);
    }
}
