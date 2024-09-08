package com.proyectofinal.productos.application;

import com.proyectofinal.productos.domain.entity.Producto;
import com.proyectofinal.productos.domain.service.ProductoService;

public class FindByIdProductoUseCase {
    private final ProductoService productoService;

    public FindByIdProductoUseCase(ProductoService productoService) {
        this.productoService = productoService;
    }

    public Producto execute(int id) {
        return productoService.findByIdProducto(id);
    }

}