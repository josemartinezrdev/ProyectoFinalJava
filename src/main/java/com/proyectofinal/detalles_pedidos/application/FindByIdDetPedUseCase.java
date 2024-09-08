package com.proyectofinal.detalles_pedidos.application;

import com.proyectofinal.detalles_pedidos.domain.entity.DetPed;
import com.proyectofinal.detalles_pedidos.domain.service.DetPedService;

public class FindByIdDetPedUseCase {
    private final DetPedService detPedService;

    public FindByIdDetPedUseCase(DetPedService detPedService) {
        this.detPedService = detPedService;
    }

    public DetPed execute(int id) {
        return detPedService.findByIdDetPed(id);
    }
}
