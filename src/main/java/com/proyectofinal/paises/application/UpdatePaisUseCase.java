package com.proyectofinal.paises.application;

import com.proyectofinal.paises.domain.entity.Pais;
import com.proyectofinal.paises.domain.service.PaisService;

public class UpdatePaisUseCase {
    private final PaisService paisService;

    public UpdatePaisUseCase(PaisService paisService) {
        this.paisService = paisService;
    }

    public void execute(Pais pais) {
        paisService.updatePais(pais);
    }

}
