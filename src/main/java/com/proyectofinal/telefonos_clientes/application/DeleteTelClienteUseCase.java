package com.proyectofinal.telefonos_clientes.application;

import com.proyectofinal.telefonos_clientes.domain.service.TelClienteService;

public class DeleteTelClienteUseCase {
    private final TelClienteService telClienteService;

    public DeleteTelClienteUseCase(TelClienteService telClienteService) {
        this.telClienteService = telClienteService;
    }

    public void execute(int id) {
        telClienteService.deleteProducto(id);
    }
}
