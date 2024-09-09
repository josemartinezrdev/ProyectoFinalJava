package com.proyectofinal.empleados.application;

import com.proyectofinal.empleados.domain.entity.Empleado;
import com.proyectofinal.empleados.domain.service.EmpleadoService;

public class FindByIdEmpleadoUseCase {
    private final EmpleadoService empleadoService;

    public FindByIdEmpleadoUseCase(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    public Empleado execute(String id) {
        return empleadoService.findByIdEmpleado(id);
    }
}
