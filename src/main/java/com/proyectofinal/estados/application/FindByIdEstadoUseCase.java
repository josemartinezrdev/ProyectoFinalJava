package com.proyectofinal.estados.application;

import com.proyectofinal.estados.domain.entity.Estado;
import com.proyectofinal.estados.domain.service.EstadoService;

public class FindByIdEstadoUseCase {
    private final EstadoService estadoService;

    public FindByIdEstadoUseCase(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    public Estado execute(int id) {
        return estadoService.findByIdEstado(id);
    }

}
