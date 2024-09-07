package com.proyectofinal.paises.application;

import com.proyectofinal.paises.domain.entity.Pais;
import com.proyectofinal.paises.domain.service.PaisService;

public class CreatePaisUseCase {
    private final PaisService paisService;

    public CreatePaisUseCase(PaisService paisService) {
        this.paisService = paisService;
    }

    public void execute(Pais pais) {
        paisService.createPais(pais);
    }

}
