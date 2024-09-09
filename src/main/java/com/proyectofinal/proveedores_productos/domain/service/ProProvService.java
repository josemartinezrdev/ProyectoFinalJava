package com.proyectofinal.proveedores_productos.domain.service;

import java.util.List;

import com.proyectofinal.proveedores_productos.domain.entity.ProProv;

public interface ProProvService {
    void createProdBod(ProProv proprov);

    void updateProdBod(ProProv proprov, int idproducto, int idproveedor);

    void deleteProdBod(int idproducto, int idproveedor);

    ProProv findProdBodById(int idproducto, int idproveedor);

    List<ProProv> findAllProdBod();
}
