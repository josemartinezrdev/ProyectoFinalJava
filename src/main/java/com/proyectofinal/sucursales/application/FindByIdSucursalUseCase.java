package com.proyectofinal.sucursales.application;

import com.proyectofinal.sucursales.domain.entity.Sucursal;
import com.proyectofinal.sucursales.domain.service.SucursalService;

public class FindByIdSucursalUseCase {
    private final SucursalService sucursalService;

    public FindByIdSucursalUseCase(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    public Sucursal execute(int id) {
        return sucursalService.findByIdSucursal(id);
    }
}
