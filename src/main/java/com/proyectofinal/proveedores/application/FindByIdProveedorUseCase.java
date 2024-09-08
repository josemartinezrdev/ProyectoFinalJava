package com.proyectofinal.proveedores.application;

import com.proyectofinal.proveedores.domain.entity.Proveedor;
import com.proyectofinal.proveedores.domain.service.ProveedorService;

public class FindByIdProveedorUseCase {
    private final ProveedorService proveedorService;

    public FindByIdProveedorUseCase(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    public Proveedor execute(int id) {
        return proveedorService.findByIdProveedor(id);
    }

}
