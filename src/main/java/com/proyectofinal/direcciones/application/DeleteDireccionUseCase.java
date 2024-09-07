package com.proyectofinal.direcciones.application;

import com.proyectofinal.direcciones.domain.service.DireccionService;

public class DeleteDireccionUseCase {
    private final DireccionService direccionService;

    public DeleteDireccionUseCase(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    public void execute(int id) {
        direccionService.deleteDireccion(id);
    }
}
