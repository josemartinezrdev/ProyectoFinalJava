package com.proyectofinal.productos_bodegas.application;

import com.proyectofinal.productos_bodegas.domain.entity.ProdBod;
import com.proyectofinal.productos_bodegas.domain.service.ProdBodService;

public class CreateProdBodUseCase {
    private final ProdBodService prodBodService;

    public CreateProdBodUseCase(ProdBodService prodBodService) {
        this.prodBodService = prodBodService;
    }

    public void execute(ProdBod prodBod) {
        prodBodService.createProdBod(prodBod);
    }
}
