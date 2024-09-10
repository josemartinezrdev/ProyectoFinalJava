package com.proyectofinal.detalles_ventas.application;

import com.proyectofinal.detalles_ventas.domain.entity.DetVenta;
import com.proyectofinal.detalles_ventas.domain.service.DetVentaService;

public class CreateDetVentaUseCase {
    private final DetVentaService detVentaService;

    public CreateDetVentaUseCase(DetVentaService detVentaService) {
        this.detVentaService = detVentaService;
    }

    public void execute(DetVenta detVenta) {
        detVentaService.createVenta(detVenta);
    }
}
