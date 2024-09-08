package com.proyectofinal.pedidos.application;

import com.proyectofinal.pedidos.domain.entity.Pedido;
import com.proyectofinal.pedidos.domain.service.PedidoService;

public class CreatePedidoUseCase {
    private final PedidoService pedidoService;

    public CreatePedidoUseCase(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    public void execute(Pedido pedido) {
        pedidoService.createPedido(pedido);
    }
}
