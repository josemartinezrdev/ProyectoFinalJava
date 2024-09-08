package com.proyectofinal.pedidos.application;

import com.proyectofinal.pedidos.domain.service.PedidoService;

public class DeletePedidoUseCase {
    private final PedidoService pedidoService;

    public DeletePedidoUseCase(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    public void execute(int id) {
        pedidoService.deletePedido(id);
    }
}
