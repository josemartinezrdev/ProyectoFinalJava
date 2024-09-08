package com.proyectofinal.pedidos.application;

import com.proyectofinal.pedidos.domain.entity.Pedido;
import com.proyectofinal.pedidos.domain.service.PedidoService;

public class UpdatePedidoUseCase {
    private final PedidoService pedidoService;

    public UpdatePedidoUseCase(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    public void execute(Pedido pedido) {
        pedidoService.updatePedido(pedido);
    }
}
