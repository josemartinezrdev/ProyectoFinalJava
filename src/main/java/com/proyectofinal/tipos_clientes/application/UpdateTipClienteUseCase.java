package com.proyectofinal.tipos_clientes.application;

import com.proyectofinal.tipos_clientes.domain.entity.TipCliente;
import com.proyectofinal.tipos_clientes.domain.service.TipClienteService;

public class UpdateTipClienteUseCase {
    private final TipClienteService tipClienteService;

    public UpdateTipClienteUseCase(TipClienteService tipClienteService) {
        this.tipClienteService = tipClienteService;
    }

    public void execute(TipCliente tipCliente) {
        tipClienteService.updateTipCliente(tipCliente);
    }
}
