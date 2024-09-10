package com.proyectofinal.telefonos_clientes.application;

import com.proyectofinal.telefonos_clientes.domain.entity.TelCliente;
import com.proyectofinal.telefonos_clientes.domain.service.TelClienteService;

public class CreateTelClienteUseCase {
    private final TelClienteService telClienteService;

    public CreateTelClienteUseCase(TelClienteService telClienteService) {
        this.telClienteService = telClienteService;
    }

    public void execute(TelCliente telCliente) {
        telClienteService.createProducto(telCliente);
    }
}
