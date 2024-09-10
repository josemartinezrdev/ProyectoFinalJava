package com.proyectofinal.detalles_ventas.domain.service;

import java.util.List;

import com.proyectofinal.detalles_ventas.domain.entity.DetVenta;

public interface DetVentaService {
    public void createVenta(DetVenta detVenta);

    public void updateVenta(DetVenta detVenta);

    public void deleteVenta(int id);

    public List<DetVenta> findAllVentas();

    public DetVenta findByIdVenta(int id);
}
