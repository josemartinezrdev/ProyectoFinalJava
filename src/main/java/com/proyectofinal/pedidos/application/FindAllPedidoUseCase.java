package com.proyectofinal.pedidos.application;

import java.util.List;

import com.proyectofinal.pedidos.domain.entity.Pedido;
import com.proyectofinal.pedidos.domain.service.PedidoService;

public class FindAllPedidoUseCase {
    private final PedidoService pedidoService;

    public FindAllPedidoUseCase(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    public List<Pedido> execute() {
        return pedidoService.findAllPedidoes();
    }
}
