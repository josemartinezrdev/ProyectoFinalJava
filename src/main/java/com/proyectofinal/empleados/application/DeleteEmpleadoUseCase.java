package com.proyectofinal.empleados.application;

import com.proyectofinal.empleados.domain.service.EmpleadoService;

public class DeleteEmpleadoUseCase {
    private final EmpleadoService empleadoService;

    public DeleteEmpleadoUseCase(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    public void execute(String id) {
        empleadoService.deleteEmpleado(id);
    }

}
