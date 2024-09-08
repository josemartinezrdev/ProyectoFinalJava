package com.proyectofinal.sucursales.application;

import java.util.List;

import com.proyectofinal.sucursales.domain.entity.Sucursal;
import com.proyectofinal.sucursales.domain.service.SucursalService;

public class FindAllSucursalUseCase {
    private final SucursalService sucursalService;

    public FindAllSucursalUseCase(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    public List<Sucursal> execute() {
        return sucursalService.findAllSucursales();
    }
}
