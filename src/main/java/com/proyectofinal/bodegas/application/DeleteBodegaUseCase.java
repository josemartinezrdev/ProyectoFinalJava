package com.proyectofinal.bodegas.application;

import com.proyectofinal.bodegas.domain.service.BodegaService;

public class DeleteBodegaUseCase {
    private final BodegaService bodegaService;

    public DeleteBodegaUseCase(BodegaService bodegaService) {
        this.bodegaService = bodegaService;
    }

    public void execute(int id) {
        bodegaService.deleteBodega(id);
    }

}
