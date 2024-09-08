package com.proyectofinal.detalles_pedidos.domain.service;

import java.util.List;

import com.proyectofinal.detalles_pedidos.domain.entity.DetPed;

public interface DetPedService {
    public void createDetPed(DetPed detped);

    public void updateDetPed(DetPed detped);

    public void deleteDetPed(int id);

    public List<DetPed> findAllDetPedes();

    public DetPed findByIdDetPed(int id);
}
