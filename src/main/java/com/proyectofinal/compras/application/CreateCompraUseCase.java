package com.proyectofinal.compras.application;

import com.proyectofinal.compras.domain.entity.Compra;
import com.proyectofinal.compras.domain.service.CompraService;

public class CreateCompraUseCase {
    private final CompraService compraService;

    public CreateCompraUseCase(CompraService compraService) {
        this.compraService = compraService;
    }

    public void execute(Compra compra) {
        compraService.createCompra(compra);
    }

}
