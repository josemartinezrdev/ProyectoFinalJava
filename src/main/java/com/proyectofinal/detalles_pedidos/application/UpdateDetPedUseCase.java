package com.proyectofinal.detalles_pedidos.application;

import com.proyectofinal.detalles_pedidos.domain.entity.DetPed;
import com.proyectofinal.detalles_pedidos.domain.service.DetPedService;

public class UpdateDetPedUseCase {
    private final DetPedService detPedService;

    public UpdateDetPedUseCase(DetPedService detPedService) {
        this.detPedService = detPedService;
    }

    public void execute(DetPed detPed) {
        detPedService.updateDetPed(detPed);
    }
}
