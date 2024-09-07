package com.proyectofinal.direcciones.application;

import java.util.List;

import com.proyectofinal.direcciones.domain.entity.Direccion;
import com.proyectofinal.direcciones.domain.service.DireccionService;

public class FindAllDireccionUseCase {
    private final DireccionService direccionService;

    public FindAllDireccionUseCase(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    public List<Direccion> execute() {
        return direccionService.findAllDirecciones();
    }
}
