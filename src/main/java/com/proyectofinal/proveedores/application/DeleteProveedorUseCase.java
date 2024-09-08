package com.proyectofinal.proveedores.application;

import com.proyectofinal.proveedores.domain.service.ProveedorService;

public class DeleteProveedorUseCase {
    private final ProveedorService proveedorService;

    public DeleteProveedorUseCase(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    public void execute(int id) {
        proveedorService.deleteProveedor(id);
    }
}
