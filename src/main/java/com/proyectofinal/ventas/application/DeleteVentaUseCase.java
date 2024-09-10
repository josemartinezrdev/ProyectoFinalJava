package com.proyectofinal.ventas.application;

import com.proyectofinal.ventas.domain.service.VentaService;

public class DeleteVentaUseCase {
    private final VentaService ventaService;

    public DeleteVentaUseCase(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    public void execute(int id) {
        ventaService.deleteVenta(id);
    }
}
