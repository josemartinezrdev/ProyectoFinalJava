package com.proyectofinal.compras.application;

import com.proyectofinal.compras.domain.entity.Compra;
import com.proyectofinal.compras.domain.service.CompraService;

public class FindByIdCompraUseCase {
    private final CompraService compraService;

    public FindByIdCompraUseCase(CompraService compraService) {
        this.compraService = compraService;
    }

    public Compra execute(int id) {
        return compraService.findByIdCompra(id);
    }

}
