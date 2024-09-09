package com.proyectofinal.proveedores_productos.application;

import com.proyectofinal.proveedores_productos.domain.entity.ProProv;
import com.proyectofinal.proveedores_productos.domain.service.ProProvService;

public class FindByIdProProvUseCase {
    private final ProProvService proProvService;

    public FindByIdProProvUseCase(ProProvService proProvService) {
        this.proProvService = proProvService;
    }

    public ProProv execute(int idproducto, int idproveedor) {
        return proProvService.findProdBodById(idproducto, idproveedor);
    }
}
