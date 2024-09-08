package com.proyectofinal.estados.application;

import com.proyectofinal.estados.domain.entity.Estado;
import com.proyectofinal.estados.domain.service.EstadoService;

public class UpdateEstadoUseCase {
    private final EstadoService estadoService;

    public UpdateEstadoUseCase(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    public void execute(Estado estado) {
        estadoService.updateEstado(estado);
    }

}
