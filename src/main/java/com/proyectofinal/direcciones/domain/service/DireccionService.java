package com.proyectofinal.direcciones.domain.service;

import java.util.List;

import com.proyectofinal.direcciones.domain.entity.Direccion;

public interface DireccionService {
    public void createDireccion(Direccion direccion);

    public void updateDireccion(Direccion direccion);

    public void deleteDireccion(int id);

    public List<Direccion> findAllDirecciones();

    public Direccion findByIdDireccion(int id);
}
