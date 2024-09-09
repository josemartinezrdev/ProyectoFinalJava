package com.proyectofinal.productos_bodegas.application;

import com.proyectofinal.productos_bodegas.domain.service.ProdBodService;

public class DeleteProdBodUseCase {
    private final ProdBodService prodBodService;

    public DeleteProdBodUseCase(ProdBodService prodBodService) {
        this.prodBodService = prodBodService;
    }

    public void execute(int idproducto, int idbodega) {
        prodBodService.deleteProdBod(idproducto, idbodega);
    }
}
