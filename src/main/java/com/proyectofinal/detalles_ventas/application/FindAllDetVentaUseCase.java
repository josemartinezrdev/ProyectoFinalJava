package com.proyectofinal.detalles_ventas.application;

import java.util.List;

import com.proyectofinal.detalles_ventas.domain.entity.DetVenta;
import com.proyectofinal.detalles_ventas.domain.service.DetVentaService;

public class FindAllDetVentaUseCase {
    private final DetVentaService detVentaService;

    public FindAllDetVentaUseCase(DetVentaService detVentaService) {
        this.detVentaService = detVentaService;
    }

    public List<DetVenta> execute() {
        return detVentaService.findAllVentas();
    }
}
