package com.proyectofinal.productos_bodegas.domain.service;

import java.util.List;

import com.proyectofinal.productos_bodegas.domain.entity.ProdBod;

public interface ProdBodService {
    void createProdBod(ProdBod prodbod);

    void updateProdBod(ProdBod prodbod, int idproducto, int idbodega);

    void deleteProdBod(int idproducto, int idbodega);

    ProdBod findProdBodById(int idproducto, int idbodega);

    List<ProdBod> findAllProdBod();
}
