package com.proyectofinal.proveedores.domain.service;

import java.util.List;

import com.proyectofinal.proveedores.domain.entity.Proveedor;

public interface ProveedorService {

    public void createProveedor(Proveedor proveedor);

    public void updateProveedor(Proveedor proveedor);

    public void deleteProveedor(int id);

    public List<Proveedor> findAllProveedores();

    public Proveedor findByIdProveedor(int id);
}
