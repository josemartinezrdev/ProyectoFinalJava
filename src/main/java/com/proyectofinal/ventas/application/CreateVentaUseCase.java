package com.proyectofinal.ventas.application;

import com.proyectofinal.ventas.domain.entity.Venta;
import com.proyectofinal.ventas.domain.service.VentaService;

public class CreateVentaUseCase {
    private final VentaService ventaService;

    public CreateVentaUseCase(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    public void execute(Venta venta) {
        ventaService.createVenta(venta);
    }
}
