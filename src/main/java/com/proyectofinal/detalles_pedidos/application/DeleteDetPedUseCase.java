package com.proyectofinal.detalles_pedidos.application;

import com.proyectofinal.detalles_pedidos.domain.service.DetPedService;

public class DeleteDetPedUseCase {
    private final DetPedService detPedService;

    public DeleteDetPedUseCase(DetPedService detPedService) {
        this.detPedService = detPedService;
    }

    public void execute(int id) {
        detPedService.deleteDetPed(id);
    }
}
