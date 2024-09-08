package com.proyectofinal.proveedores.application;

import java.util.List;

import com.proyectofinal.proveedores.domain.entity.Proveedor;
import com.proyectofinal.proveedores.domain.service.ProveedorService;

public class FindAllProveedorUseCase {
    private final ProveedorService proveedorService;

    public FindAllProveedorUseCase(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    public List<Proveedor> execute() {
        return proveedorService.findAllProveedores();
    }
}
