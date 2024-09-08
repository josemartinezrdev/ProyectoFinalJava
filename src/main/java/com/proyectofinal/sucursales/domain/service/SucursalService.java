package com.proyectofinal.sucursales.domain.service;

import java.util.List;

import com.proyectofinal.sucursales.domain.entity.Sucursal;

public interface SucursalService {
    public void createSucursal(Sucursal sucursal);

    public void updateSucursal(Sucursal sucursal);

    public void deleteSucursal(int id);

    public List<Sucursal> findAllSucursales();

    public Sucursal findByIdSucursal(int id);
}
