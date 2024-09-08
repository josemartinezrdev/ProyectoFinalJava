package com.proyectofinal.bodegas.application;

import com.proyectofinal.bodegas.domain.entity.Bodega;
import com.proyectofinal.bodegas.domain.service.BodegaService;

public class CreateBodegaUseCase {
    private final BodegaService bodegaService;

    public CreateBodegaUseCase(BodegaService bodegaService) {
        this.bodegaService = bodegaService;
    }

    public void execute(Bodega bodega) {
        bodegaService.createBodega(bodega);
    }
}
