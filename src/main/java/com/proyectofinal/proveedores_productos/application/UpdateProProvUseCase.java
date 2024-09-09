package com.proyectofinal.proveedores_productos.application;

import com.proyectofinal.proveedores_productos.domain.entity.ProProv;
import com.proyectofinal.proveedores_productos.domain.service.ProProvService;

public class UpdateProProvUseCase {
    private final ProProvService proProvService;

    public UpdateProProvUseCase(ProProvService proProvService) {
        this.proProvService = proProvService;
    }

    public void execute(ProProv proProv, int idproducto, int idproveedor) {
        proProvService.updateProdBod(proProv, idproducto, idproveedor);
    }

}
