package com.proyectofinal.pedidos.domain.service;

import java.util.List;

import com.proyectofinal.pedidos.domain.entity.Pedido;

public interface PedidoService {
    public void createPedido(Pedido pedido);

    public void updatePedido(Pedido pedido);

    public void deletePedido(int id);

    public List<Pedido> findAllPedidoes();

    public Pedido findByIdPedido(int id);
}
