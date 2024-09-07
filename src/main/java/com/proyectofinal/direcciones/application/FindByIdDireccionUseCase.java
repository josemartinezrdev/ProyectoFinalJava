package com.proyectofinal.direcciones.application;

import com.proyectofinal.direcciones.domain.entity.Direccion;
import com.proyectofinal.direcciones.domain.service.DireccionService;

public class FindByIdDireccionUseCase {
    private final DireccionService direccionService;

    public FindByIdDireccionUseCase(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    public Direccion execute(int id) {
        return direccionService.findByIdDireccion(id);
    }
}
