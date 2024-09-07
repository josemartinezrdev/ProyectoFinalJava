package com.proyectofinal.direcciones.application;

import com.proyectofinal.direcciones.domain.entity.Direccion;
import com.proyectofinal.direcciones.domain.service.DireccionService;

public class UpdateDireccionUseCase {
    private final DireccionService direccionService;

    public UpdateDireccionUseCase(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    public void execute(Direccion direccion) {
        direccionService.updateDireccion(direccion);
    }
}
