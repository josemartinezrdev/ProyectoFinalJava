package com.proyectofinal.estados.application;

import java.util.List;

import com.proyectofinal.estados.domain.entity.Estado;
import com.proyectofinal.estados.domain.service.EstadoService;

public class FindAllEstadoUseCase {
    private final EstadoService estadoService;

    public FindAllEstadoUseCase(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    public List<Estado> execute() {
        return estadoService.findAllEstadoes();
    }
}
