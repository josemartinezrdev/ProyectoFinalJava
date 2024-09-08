package com.proyectofinal.productos.domain.service;

import java.util.List;

import com.proyectofinal.productos.domain.entity.Producto;

public interface ProductoService {
    public void createProducto(Producto producto);

    public void updateProducto(Producto producto);

    public void deleteProducto(int id);

    public List<Producto> findAllProductos();

    public Producto findByIdProducto(int id);
}
