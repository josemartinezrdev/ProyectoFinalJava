package com.proyectofinal.proveedores.application;

import com.proyectofinal.proveedores.domain.entity.Proveedor;
import com.proyectofinal.proveedores.domain.service.ProveedorService;

public class UpdateProveedorUseCase {
    private final ProveedorService proveedorService;

    public UpdateProveedorUseCase(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    public void execute(Proveedor proveedor) {
        proveedorService.updateProveedor(proveedor);
    }
}
