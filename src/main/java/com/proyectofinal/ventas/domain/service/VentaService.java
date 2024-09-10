package com.proyectofinal.ventas.domain.service;

import java.util.List;

import com.proyectofinal.ventas.domain.entity.Venta;

public interface VentaService {
    public void createVenta(Venta venta);

    public void updateVenta(Venta venta);

    public void deleteVenta(int id);

    public List<Venta> findAllVentas();

    public Venta findByIdVenta(int id);
}
