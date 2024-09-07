package com.proyectofinal.paises.application;

import com.proyectofinal.paises.domain.entity.Pais;
import com.proyectofinal.paises.domain.service.PaisService;

public class FindByIdPaisUseCase {
    private final PaisService paisService;

    public FindByIdPaisUseCase(PaisService paisService) {
        this.paisService = paisService;
    }

    public Pais execute(int id) {
        return paisService.findByIdPais(id);
    }

}
