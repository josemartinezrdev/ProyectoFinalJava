package com.proyectofinal.tipos_clientes.application;

import com.proyectofinal.tipos_clientes.domain.entity.TipCliente;
import com.proyectofinal.tipos_clientes.domain.service.TipClienteService;

public class CreateTipClienteUseCase {
    private final TipClienteService tipClienteService;

    public CreateTipClienteUseCase(TipClienteService tipClienteService) {
        this.tipClienteService = tipClienteService;
    }

    public void execute(TipCliente tipCliente) {
        tipClienteService.createTipCliente(tipCliente);
    }
}