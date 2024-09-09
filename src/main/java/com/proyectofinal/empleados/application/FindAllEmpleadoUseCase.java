package com.proyectofinal.empleados.application;

import java.util.List;

import com.proyectofinal.empleados.domain.entity.Empleado;
import com.proyectofinal.empleados.domain.service.EmpleadoService;

public class FindAllEmpleadoUseCase {
    private final EmpleadoService empleadoService;

    public FindAllEmpleadoUseCase(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    public List<Empleado> execute() {
        return empleadoService.findAllEmpleados();
    }
}
