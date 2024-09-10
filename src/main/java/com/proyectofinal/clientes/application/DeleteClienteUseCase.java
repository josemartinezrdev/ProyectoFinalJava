package com.proyectofinal.clientes.application;

import com.proyectofinal.clientes.domain.service.ClienteService;

public class DeleteClienteUseCase {
    private final ClienteService clienteService;

    public DeleteClienteUseCase(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public void execute(String id) {
        clienteService.deleteCliente(id);
    }
}
