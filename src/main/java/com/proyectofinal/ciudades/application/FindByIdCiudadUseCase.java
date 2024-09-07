package com.proyectofinal.ciudades.application;

import com.proyectofinal.ciudades.domain.entity.Ciudad;
import com.proyectofinal.ciudades.domain.service.CiudadService;

public class FindByIdCiudadUseCase {
    private final CiudadService ciudadService;

    public FindByIdCiudadUseCase(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    public Ciudad execute(int id) {
        return ciudadService.findByIdCiudad(id);
    }
}
