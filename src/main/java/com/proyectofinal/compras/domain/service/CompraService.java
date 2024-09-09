package com.proyectofinal.compras.domain.service;

import java.util.List;

import com.proyectofinal.compras.domain.entity.Compra;

public interface CompraService {
    public void createCompra(Compra compra);

    public void updateCompra(Compra compra);

    public void deleteCompra(int id);

    public List<Compra> findAllCompras();

    public Compra findByIdCompra(int id);
}
