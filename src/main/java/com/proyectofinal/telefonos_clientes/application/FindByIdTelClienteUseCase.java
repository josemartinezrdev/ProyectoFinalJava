package com.proyectofinal.telefonos_clientes.application;

import com.proyectofinal.telefonos_clientes.domain.entity.TelCliente;
import com.proyectofinal.telefonos_clientes.domain.service.TelClienteService;

public class FindByIdTelClienteUseCase {

    private final TelClienteService telClienteService;

    public FindByIdTelClienteUseCase(TelClienteService telClienteService) {
        this.telClienteService = telClienteService;
    }

    public TelCliente execute(int id) {
        return telClienteService.findByIdProducto(id);
    }

}
