package com.proyectofinal.ventas.application;

import java.util.List;

import com.proyectofinal.ventas.domain.entity.Venta;
import com.proyectofinal.ventas.domain.service.VentaService;

public class FindAllVentaUseCase {
    private final VentaService ventaService;

    public FindAllVentaUseCase(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    public List<Venta> execute() {
        return ventaService.findAllVentas();
    }
}
