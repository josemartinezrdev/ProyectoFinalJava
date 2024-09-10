package com.proyectofinal.tipos_clientes.application;

import java.util.List;

import com.proyectofinal.tipos_clientes.domain.entity.TipCliente;
import com.proyectofinal.tipos_clientes.domain.service.TipClienteService;

public class FindAllTipClienteUseCase {
    private final TipClienteService tipClienteService;

    public FindAllTipClienteUseCase(TipClienteService tipClienteService) {
        this.tipClienteService = tipClienteService;
    }

    public List<TipCliente> execute() {
        return tipClienteService.findAllTipClientees();
    }
}
