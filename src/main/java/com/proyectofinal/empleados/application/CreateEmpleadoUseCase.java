package com.proyectofinal.empleados.application;

import com.proyectofinal.empleados.domain.entity.Empleado;
import com.proyectofinal.empleados.domain.service.EmpleadoService;

public class CreateEmpleadoUseCase {
    private final EmpleadoService empleadoService;

    public CreateEmpleadoUseCase(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    public void execute(Empleado empleado) {
        empleadoService.createEmpleado(empleado);
    }
}
