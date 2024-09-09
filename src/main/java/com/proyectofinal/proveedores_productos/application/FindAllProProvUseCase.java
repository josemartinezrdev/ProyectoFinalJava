package com.proyectofinal.proveedores_productos.application;

import java.util.List;

import com.proyectofinal.proveedores_productos.domain.entity.ProProv;
import com.proyectofinal.proveedores_productos.domain.service.ProProvService;

public class FindAllProProvUseCase {
    private final ProProvService proProvService;

    public FindAllProProvUseCase(ProProvService proProvService) {
        this.proProvService = proProvService;
    }

    public List<ProProv> execute() {
        return proProvService.findAllProdBod();
    }

}
