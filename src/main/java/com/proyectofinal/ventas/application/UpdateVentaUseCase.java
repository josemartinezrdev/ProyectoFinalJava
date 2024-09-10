package com.proyectofinal.ventas.application;

import com.proyectofinal.ventas.domain.entity.Venta;
import com.proyectofinal.ventas.domain.service.VentaService;

public class UpdateVentaUseCase {
    private final VentaService ventaService;

    public UpdateVentaUseCase(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    public void execute(Venta venta) {
        ventaService.updateVenta(venta);
    }
}
