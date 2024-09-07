package com.proyectofinal.paises.application;

import java.util.List;

import com.proyectofinal.paises.domain.entity.Pais;
import com.proyectofinal.paises.domain.service.PaisService;

public class FindAllPaisUseCase {
    private final PaisService paisService;

    public FindAllPaisUseCase(PaisService paisService) {
        this.paisService = paisService;
    }

    public List<Pais> execute() {
        return paisService.findAllPaises();
    }
}
