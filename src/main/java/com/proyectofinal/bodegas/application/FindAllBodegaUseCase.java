package com.proyectofinal.bodegas.application;

import java.util.List;

import com.proyectofinal.bodegas.domain.entity.Bodega;
import com.proyectofinal.bodegas.domain.service.BodegaService;

public class FindAllBodegaUseCase {
    private final BodegaService bodegaService;

    public FindAllBodegaUseCase(BodegaService bodegaService) {
        this.bodegaService = bodegaService;
    }

    public List<Bodega> execute() {
        return bodegaService.findAllBodegaes();
    }
}
