package com.proyectofinal.sucursales.application;

import com.proyectofinal.sucursales.domain.entity.Sucursal;
import com.proyectofinal.sucursales.domain.service.SucursalService;

public class CreateSucursalUseCase {
    private final SucursalService sucursalService;

    public CreateSucursalUseCase(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    public void execute(Sucursal sucursal) {
        sucursalService.createSucursal(sucursal);
    }
}
