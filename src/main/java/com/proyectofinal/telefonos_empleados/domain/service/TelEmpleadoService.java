package com.proyectofinal.telefonos_empleados.domain.service;

import java.util.List;

import com.proyectofinal.telefonos_empleados.domain.entity.TelEmpleado;

public interface TelEmpleadoService {
    public void createProducto(TelEmpleado telempleado);

    public void updateProducto(TelEmpleado telempleado);

    public void deleteProducto(int id);

    public List<TelEmpleado> findAllProductos();

    public TelEmpleado findByIdProducto(int id);
}
