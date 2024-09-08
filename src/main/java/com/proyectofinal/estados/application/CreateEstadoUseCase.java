package com.proyectofinal.estados.application;

import com.proyectofinal.estados.domain.entity.Estado;
import com.proyectofinal.estados.domain.service.EstadoService;

public class CreateEstadoUseCase {

    private final EstadoService estadoService;

    public CreateEstadoUseCase(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    public void execute(Estado estado) {
        estadoService.createEstado(estado);
    }

}
