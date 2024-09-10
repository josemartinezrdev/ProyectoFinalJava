package com.proyectofinal.detalles_ventas.application;

import com.proyectofinal.detalles_ventas.domain.service.DetVentaService;

public class DeleteDetVentaUseCase {
    private final DetVentaService detVentaService;

    public DeleteDetVentaUseCase(DetVentaService detVentaService) {
        this.detVentaService = detVentaService;
    }

    public void execute(int id) {
        detVentaService.deleteVenta(id);
    }
}
