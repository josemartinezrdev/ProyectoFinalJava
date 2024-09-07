package com.proyectofinal.direcciones.application;

import com.proyectofinal.direcciones.domain.entity.Direccion;
import com.proyectofinal.direcciones.domain.service.DireccionService;

public class CreateDireccionUseCase {
    private final DireccionService direccionService;

    public CreateDireccionUseCase(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    public void execute(Direccion direccion) {
        direccionService.createDireccion(direccion);
    }
}
