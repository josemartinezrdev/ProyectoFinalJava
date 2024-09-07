package com.proyectofinal.ciudades.application;

import com.proyectofinal.ciudades.domain.entity.Ciudad;
import com.proyectofinal.ciudades.domain.service.CiudadService;

public class UpdateCiudadUseCase {
    private final CiudadService ciudadService;

    public UpdateCiudadUseCase(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    public void execute(Ciudad ciudad) {
        ciudadService.updateCiudad(ciudad);
    }
}
