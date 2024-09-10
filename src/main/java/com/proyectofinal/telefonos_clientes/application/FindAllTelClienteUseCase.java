package com.proyectofinal.telefonos_clientes.application;

import java.util.List;

import com.proyectofinal.telefonos_clientes.domain.entity.TelCliente;
import com.proyectofinal.telefonos_clientes.domain.service.TelClienteService;

public class FindAllTelClienteUseCase {

    private final TelClienteService telClienteService;

    public FindAllTelClienteUseCase(TelClienteService telClienteService) {
        this.telClienteService = telClienteService;
    }

    public List<TelCliente> execute() {
        return telClienteService.findAllProductos();
    }

}
