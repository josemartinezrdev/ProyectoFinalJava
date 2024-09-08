package com.proyectofinal.proveedores.application;

import com.proyectofinal.proveedores.domain.entity.Proveedor;
import com.proyectofinal.proveedores.domain.service.ProveedorService;

public class CreateProveedorUseCase {
        private final ProveedorService proveedorService;


    public CreateProveedorUseCase(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    public void execute(Proveedor proveedor) {
        proveedorService.createProveedor(proveedor);
    }
}
