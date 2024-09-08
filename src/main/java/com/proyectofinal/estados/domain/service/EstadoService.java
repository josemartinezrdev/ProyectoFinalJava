package com.proyectofinal.estados.domain.service;

import java.util.List;

import com.proyectofinal.estados.domain.entity.Estado;

public interface EstadoService {
    public void createEstado(Estado estado);

    public void updateEstado(Estado estado);

    public void deleteEstado(int id);

    public List<Estado> findAllEstadoes();

    public Estado findByIdEstado(int id);
}
