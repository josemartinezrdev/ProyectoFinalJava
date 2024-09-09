package com.proyectofinal.compras.application;

import com.proyectofinal.compras.domain.service.CompraService;

public class DeleteCompraUseCase {
    private final CompraService compraService;

    public DeleteCompraUseCase(CompraService compraService) {
        this.compraService = compraService;
    }

    public void execute(int id) {
        compraService.deleteCompra(id);
    }

}
