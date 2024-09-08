package com.proyectofinal.pedidos.application;

import com.proyectofinal.pedidos.domain.entity.Pedido;
import com.proyectofinal.pedidos.domain.service.PedidoService;

public class FindByIdPedidoUseCase {
    private final PedidoService pedidoService;

    public FindByIdPedidoUseCase(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    public Pedido execute(int id) {
        return pedidoService.findByIdPedido(id);
    }
}
