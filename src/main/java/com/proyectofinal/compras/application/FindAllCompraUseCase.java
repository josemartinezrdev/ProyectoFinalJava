package com.proyectofinal.compras.application;

import java.util.List;

import com.proyectofinal.compras.domain.entity.Compra;
import com.proyectofinal.compras.domain.service.CompraService;

public class FindAllCompraUseCase {
    private final CompraService compraService;

    public FindAllCompraUseCase(CompraService compraService) {
        this.compraService = compraService;
    }

    public List<Compra> execute() {
        return compraService.findAllCompras();
    }
}
