package com.proyectofinal.compras.application;

import com.proyectofinal.compras.domain.entity.Compra;
import com.proyectofinal.compras.domain.service.CompraService;

public class UpdateCompraUseCase {
    private final CompraService compraService;

    public UpdateCompraUseCase(CompraService compraService) {
        this.compraService = compraService;
    }

    public void execute(Compra compra) {
        compraService.updateCompra(compra);
    }

}
