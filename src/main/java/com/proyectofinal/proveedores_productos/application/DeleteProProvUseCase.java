package com.proyectofinal.proveedores_productos.application;

import com.proyectofinal.proveedores_productos.domain.service.ProProvService;

public class DeleteProProvUseCase {
    private final ProProvService proProvService;

    public DeleteProProvUseCase(ProProvService proProvService) {
        this.proProvService = proProvService;
    }

    public void execute(int idproducto, int idproveedor) {
        proProvService.deleteProdBod(idproducto, idproveedor);
    }
}
