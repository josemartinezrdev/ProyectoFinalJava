package com.proyectofinal.estados.application;

import com.proyectofinal.estados.domain.service.EstadoService;

public class DeleteEstadoUseCase {
    private final EstadoService estadoService;

    public DeleteEstadoUseCase(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    public void execute(int id) {
        estadoService.deleteEstado(id);
    }

}
