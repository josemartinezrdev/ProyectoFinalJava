package com.proyectofinal.tipos_clientes.application;

import com.proyectofinal.tipos_clientes.domain.entity.TipCliente;
import com.proyectofinal.tipos_clientes.domain.service.TipClienteService;

public class FindByIdTipClienteUseCase {

    private final TipClienteService tipClienteService;

    public FindByIdTipClienteUseCase(TipClienteService tipClienteService) {
        this.tipClienteService = tipClienteService;
    }

    public TipCliente execute(int id) {
        return tipClienteService.findByIdTipCliente(id);
    }
}
