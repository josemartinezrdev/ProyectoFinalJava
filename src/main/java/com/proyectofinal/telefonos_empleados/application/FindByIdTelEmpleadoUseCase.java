package com.proyectofinal.telefonos_empleados.application;

import com.proyectofinal.telefonos_empleados.domain.entity.TelEmpleado;
import com.proyectofinal.telefonos_empleados.domain.service.TelEmpleadoService;

public class FindByIdTelEmpleadoUseCase {

    private final TelEmpleadoService telEmpleadoService;

    public FindByIdTelEmpleadoUseCase(TelEmpleadoService telEmpleadoService) {
        this.telEmpleadoService = telEmpleadoService;
    }

    public TelEmpleado execute(int id) {
        return telEmpleadoService.findByIdProducto(id);
    }

}
