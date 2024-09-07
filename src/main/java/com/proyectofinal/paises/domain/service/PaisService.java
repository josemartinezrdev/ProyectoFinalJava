package com.proyectofinal.paises.domain.service;

import java.util.List;

import com.proyectofinal.paises.domain.entity.Pais;

public interface PaisService {

    public void createPais(Pais pais);

    public void updatePais(Pais pais);

    public void deletePais(int id);

    public List<Pais> findAllPaises();

    public Pais findByIdPais(int id);

}
