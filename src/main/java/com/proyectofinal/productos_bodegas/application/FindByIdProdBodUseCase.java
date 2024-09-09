package com.proyectofinal.productos_bodegas.application;

import com.proyectofinal.productos_bodegas.domain.entity.ProdBod;
import com.proyectofinal.productos_bodegas.domain.service.ProdBodService;

public class FindByIdProdBodUseCase {
        private final ProdBodService prodBodService;   

    public FindByIdProdBodUseCase(ProdBodService prodBodService) {
        this.prodBodService = prodBodService;
    }

    public ProdBod execute(int idproducto, int iddbodega) {
        return prodBodService.findProdBodById(idproducto, iddbodega);
    }
}
