package com.proyectofinal.telefonos_clientes.application;

import com.proyectofinal.telefonos_clientes.domain.entity.TelCliente;
import com.proyectofinal.telefonos_clientes.domain.service.TelClienteService;

public class UpdateTelClienteUseCase {
    private final TelClienteService telClienteService;

    public UpdateTelClienteUseCase(TelClienteService telClienteService) {
        this.telClienteService = telClienteService;
    }

    public void execute(TelCliente telCliente) {
        telClienteService.updateProducto(telCliente);
    }

}
