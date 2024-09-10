package com.proyectofinal.ventas.application;

import com.proyectofinal.ventas.domain.entity.Venta;
import com.proyectofinal.ventas.domain.service.VentaService;

public class FindByIdVentaUseCase {
    private final VentaService ventaService;

    public FindByIdVentaUseCase(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    public Venta execute(int id) {
        return ventaService.findByIdVenta(id);
    }
}
