package com.proyectofinal.ciudades.application;

import com.proyectofinal.ciudades.domain.entity.Ciudad;
import com.proyectofinal.ciudades.domain.service.CiudadService;

public class CreateCiudadUseCase {
    private final CiudadService ciudadService;

    public CreateCiudadUseCase(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    public void execute(Ciudad ciudad) {
        ciudadService.createCiudad(ciudad);
    }
}
