package com.proyectofinal.clientes.application;

import java.util.List;

import com.proyectofinal.clientes.domain.entity.Cliente;
import com.proyectofinal.clientes.domain.service.ClienteService;

public class FindAllClienteUseCase {
    private final ClienteService clienteService;

    public FindAllClienteUseCase(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public List<Cliente> execute() {
        return clienteService.findAllClientes();
    }
}
