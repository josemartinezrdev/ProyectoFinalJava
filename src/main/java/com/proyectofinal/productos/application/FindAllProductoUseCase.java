package com.proyectofinal.productos.application;

import java.util.List;

import com.proyectofinal.productos.domain.entity.Producto;
import com.proyectofinal.productos.domain.service.ProductoService;

public class FindAllProductoUseCase {
    private final ProductoService productoService;

    public FindAllProductoUseCase(ProductoService productoService) {
        this.productoService = productoService;
    }

    public List<Producto> execute() {
        return productoService.findAllProductos();
    }
}
