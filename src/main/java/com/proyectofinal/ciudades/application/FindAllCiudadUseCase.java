package com.proyectofinal.ciudades.application;

import java.util.List;

import com.proyectofinal.ciudades.domain.entity.Ciudad;
import com.proyectofinal.ciudades.domain.service.CiudadService;

public class FindAllCiudadUseCase {
    private final CiudadService ciudadService;

    public FindAllCiudadUseCase(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    public List<Ciudad> execute() {
        return ciudadService.findAllCiudades();
    }
}
