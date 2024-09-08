package com.proyectofinal.productos.application;

import com.proyectofinal.productos.domain.entity.Producto;
import com.proyectofinal.productos.domain.service.ProductoService;

public class UpdateProductoUseCase {
    private final ProductoService productoService;

    public UpdateProductoUseCase(ProductoService productoService) {
        this.productoService = productoService;
    }

    public void execute(Producto producto) {
        productoService.updateProducto(producto);
    }

}
