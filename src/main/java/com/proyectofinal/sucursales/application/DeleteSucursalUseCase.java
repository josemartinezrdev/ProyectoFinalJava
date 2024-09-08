package com.proyectofinal.sucursales.application;

import com.proyectofinal.sucursales.domain.service.SucursalService;

public class DeleteSucursalUseCase {
    private final SucursalService sucursalService;

    public DeleteSucursalUseCase(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    public void execute(int id) {
        sucursalService.deleteSucursal(id);
    }
}
