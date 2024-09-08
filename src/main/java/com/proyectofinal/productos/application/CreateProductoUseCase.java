package com.proyectofinal.productos.application;

import com.proyectofinal.productos.domain.entity.Producto;
import com.proyectofinal.productos.domain.service.ProductoService;

public class CreateProductoUseCase {
    private final ProductoService productoService;

    public CreateProductoUseCase(ProductoService productoService) {
        this.productoService = productoService;
    }

    public void execute(Producto producto) {
        productoService.createProducto(producto);
    }
}
