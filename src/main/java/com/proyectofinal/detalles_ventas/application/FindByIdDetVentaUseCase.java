package com.proyectofinal.detalles_ventas.application;

import com.proyectofinal.detalles_ventas.domain.entity.DetVenta;
import com.proyectofinal.detalles_ventas.domain.service.DetVentaService;

public class FindByIdDetVentaUseCase {
    private final DetVentaService detVentaService;

    public FindByIdDetVentaUseCase(DetVentaService detVentaService) {
        this.detVentaService = detVentaService;
    }

    public DetVenta execute(int id) {
        return detVentaService.findByIdVenta(id);
    }
}
