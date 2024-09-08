package com.proyectofinal.sucursales.application;

import com.proyectofinal.sucursales.domain.entity.Sucursal;
import com.proyectofinal.sucursales.domain.service.SucursalService;

public class UpdateSucursalUseCase {
    private final SucursalService sucursalService;

    public UpdateSucursalUseCase(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    public void execute(Sucursal sucursal) {
        sucursalService.updateSucursal(sucursal);
    }
}
