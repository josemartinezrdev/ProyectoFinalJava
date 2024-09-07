package com.proyectofinal.ciudades.application;

import com.proyectofinal.ciudades.domain.service.CiudadService;

public class DeleteCiudadUseCase {
    private final CiudadService ciudadService;

    public DeleteCiudadUseCase(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    public void execute(int id) {
        ciudadService.deleteCiudad(id);
    }
}
