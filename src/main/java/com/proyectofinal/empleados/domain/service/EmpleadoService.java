package com.proyectofinal.empleados.domain.service;

import java.util.List;

import com.proyectofinal.empleados.domain.entity.Empleado;

public interface EmpleadoService {
    public void createEmpleado(Empleado empleado);

    public void updateEmpleado(Empleado empleado, String id);

    public void deleteEmpleado(String id);

    public List<Empleado> findAllEmpleados();

    public Empleado findByIdEmpleado(String id);
}
