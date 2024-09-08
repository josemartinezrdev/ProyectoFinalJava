package com.proyectofinal.detalles_pedidos.application;

import java.util.List;

import com.proyectofinal.detalles_pedidos.domain.entity.DetPed;
import com.proyectofinal.detalles_pedidos.domain.service.DetPedService;

public class FindAllDetPedUseCase {
    private final DetPedService detPedService;

    public FindAllDetPedUseCase(DetPedService detPedService) {
        this.detPedService = detPedService;
    }

    public List<DetPed> execute() {
        return detPedService.findAllDetPedes();
    }
}
