package com.proyectofinal.telefonos_empleados.application;

import com.proyectofinal.telefonos_empleados.domain.service.TelEmpleadoService;

public class DeleteTelEmpleadoUseCase {
    private final TelEmpleadoService telEmpleadoService;

    public DeleteTelEmpleadoUseCase(TelEmpleadoService telEmpleadoService) {
        this.telEmpleadoService = telEmpleadoService;
    }

    public void execute(int id) {
        telEmpleadoService.deleteProducto(id);
    }
}
