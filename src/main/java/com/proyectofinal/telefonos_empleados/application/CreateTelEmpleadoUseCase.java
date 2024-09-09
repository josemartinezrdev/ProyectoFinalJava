package com.proyectofinal.telefonos_empleados.application;

import com.proyectofinal.telefonos_empleados.domain.entity.TelEmpleado;
import com.proyectofinal.telefonos_empleados.domain.service.TelEmpleadoService;

public class CreateTelEmpleadoUseCase {
    private final TelEmpleadoService telEmpleadoService;

    public CreateTelEmpleadoUseCase(TelEmpleadoService telEmpleadoService) {
        this.telEmpleadoService = telEmpleadoService;
    }

    public void execute(TelEmpleado telEmpleado) {
        telEmpleadoService.createProducto(telEmpleado);
    }
}
