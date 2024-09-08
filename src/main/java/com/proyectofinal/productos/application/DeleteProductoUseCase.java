package com.proyectofinal.productos.application;

import com.proyectofinal.productos.domain.service.ProductoService;

public class DeleteProductoUseCase {
    private final ProductoService productoService;

    public DeleteProductoUseCase(ProductoService productoService) {
        this.productoService = productoService;
    }

    public void execute(int id) {
        productoService.deleteProducto(id);
    }
}
