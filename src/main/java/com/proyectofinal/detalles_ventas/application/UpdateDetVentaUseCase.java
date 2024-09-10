package com.proyectofinal.detalles_ventas.application;

import com.proyectofinal.detalles_ventas.domain.entity.DetVenta;
import com.proyectofinal.detalles_ventas.domain.service.DetVentaService;

public class UpdateDetVentaUseCase {
    private final DetVentaService detVentaService;

    public UpdateDetVentaUseCase(DetVentaService detVentaService) {
        this.detVentaService = detVentaService;
    }

    public void execute(DetVenta detVenta) {
        detVentaService.updateVenta(detVenta);
    }
}
