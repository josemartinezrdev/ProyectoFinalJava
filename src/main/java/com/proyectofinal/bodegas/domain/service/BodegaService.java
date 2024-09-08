package com.proyectofinal.bodegas.domain.service;

import java.util.List;

import com.proyectofinal.bodegas.domain.entity.Bodega;

public interface BodegaService {
    public void createBodega(Bodega bodega);

    public void updateBodega(Bodega bodega);

    public void deleteBodega(int id);

    public List<Bodega> findAllBodegaes();

    public Bodega findByIdBodega(int id);
}
