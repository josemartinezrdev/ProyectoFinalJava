package com.proyectofinal.bodegas.application;

import com.proyectofinal.bodegas.domain.entity.Bodega;
import com.proyectofinal.bodegas.domain.service.BodegaService;

public class FindByIdBodegaUseCase {
    private final BodegaService bodegaService;

    public FindByIdBodegaUseCase(BodegaService bodegaService) {
        this.bodegaService = bodegaService;
    }

    public Bodega execute(int id) {
        return bodegaService.findByIdBodega(id);
    }
}
