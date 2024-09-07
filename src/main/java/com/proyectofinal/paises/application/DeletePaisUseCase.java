package com.proyectofinal.paises.application;

import com.proyectofinal.paises.domain.service.PaisService;

public class DeletePaisUseCase {
    private final PaisService paisService;

    public DeletePaisUseCase(PaisService paisService) {
        this.paisService = paisService;
    }

    public void execute(int id) {
        paisService.deletePais(id);
    }
}
