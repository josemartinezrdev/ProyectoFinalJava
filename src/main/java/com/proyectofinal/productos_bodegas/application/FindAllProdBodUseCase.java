package com.proyectofinal.productos_bodegas.application;

import java.util.List;

import com.proyectofinal.productos_bodegas.domain.entity.ProdBod;
import com.proyectofinal.productos_bodegas.domain.service.ProdBodService;

public class FindAllProdBodUseCase {
    private final ProdBodService prodBodService;

    public FindAllProdBodUseCase(ProdBodService prodBodService) {
        this.prodBodService = prodBodService;
    }

    public List<ProdBod> execute() {
        return prodBodService.findAllProdBod();
    }

}
