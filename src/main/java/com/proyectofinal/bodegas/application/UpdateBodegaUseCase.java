package com.proyectofinal.bodegas.application;

import com.proyectofinal.bodegas.domain.entity.Bodega;
import com.proyectofinal.bodegas.domain.service.BodegaService;

public class UpdateBodegaUseCase {

    private final BodegaService bodegaService;

    public UpdateBodegaUseCase(BodegaService bodegaService) {
        this.bodegaService = bodegaService;
    }

    public void execute(Bodega bodega) {
        bodegaService.updateBodega(bodega);
    }

}
