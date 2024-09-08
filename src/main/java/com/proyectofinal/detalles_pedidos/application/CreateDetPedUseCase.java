package com.proyectofinal.detalles_pedidos.application;

import com.proyectofinal.detalles_pedidos.domain.entity.DetPed;
import com.proyectofinal.detalles_pedidos.domain.service.DetPedService;

public class CreateDetPedUseCase {
    private final DetPedService detPedService;

    public CreateDetPedUseCase(DetPedService detPedService) {
        this.detPedService = detPedService;
    }

    public void execute(DetPed detPed) {
        detPedService.createDetPed(detPed);
    }
}
