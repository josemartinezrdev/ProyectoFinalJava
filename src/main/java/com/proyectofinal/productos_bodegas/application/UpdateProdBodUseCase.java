package com.proyectofinal.productos_bodegas.application;

import com.proyectofinal.productos_bodegas.domain.entity.ProdBod;
import com.proyectofinal.productos_bodegas.domain.service.ProdBodService;

public class UpdateProdBodUseCase {
    private final ProdBodService prodBodService;

    public UpdateProdBodUseCase(ProdBodService prodBodService) {
        this.prodBodService = prodBodService;
    }

    public void execute(ProdBod prodBod, int idproducto, int idbodega) {
        prodBodService.updateProdBod(prodBod, idproducto, idbodega);
    }
}
