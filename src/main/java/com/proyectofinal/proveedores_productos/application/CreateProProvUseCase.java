package com.proyectofinal.proveedores_productos.application;

import com.proyectofinal.proveedores_productos.domain.entity.ProProv;
import com.proyectofinal.proveedores_productos.domain.service.ProProvService;

public class CreateProProvUseCase {
    private final ProProvService proProvService;

    public CreateProProvUseCase(ProProvService proProvService) {
        this.proProvService = proProvService;
    }

    public void execute(ProProv proProv) {
        proProvService.createProdBod(proProv);
    }

}
