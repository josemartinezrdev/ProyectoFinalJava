package com.proyectofinal.ciudades.domain.service;

import java.util.List;

import com.proyectofinal.ciudades.domain.entity.Ciudad;

public interface CiudadService {
    public void createCiudad(Ciudad ciudad);

    public void updateCiudad(Ciudad ciudad);

    public void deleteCiudad(int id);

    public List<Ciudad> findAllCiudades();

    public Ciudad findByIdCiudad(int id);
}
