package com.proyectofinal.tipos_clientes.application;

import com.proyectofinal.tipos_clientes.domain.service.TipClienteService;

public class DeleteTipClienteUseCase {
    private final TipClienteService tipClienteService;

    public DeleteTipClienteUseCase(TipClienteService tipClienteService) {
        this.tipClienteService = tipClienteService;
    }

    public void execute(int id) {
        tipClienteService.deleteTipCliente(id);
    }
}
