package com.proyectofinal.empleados.application;

import com.proyectofinal.empleados.domain.entity.Empleado;
import com.proyectofinal.empleados.domain.service.EmpleadoService;

public class UpdateEmpleadoUseCase {
    private final EmpleadoService empleadoService;

    public UpdateEmpleadoUseCase(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    public void execute(Empleado empleado, String id) {
        empleadoService.updateEmpleado(empleado, id);
    }
}
