package com.proyectofinal.telefonos_empleados.application;

import java.util.List;

import com.proyectofinal.telefonos_empleados.domain.entity.TelEmpleado;
import com.proyectofinal.telefonos_empleados.domain.service.TelEmpleadoService;

public class FindAllTelEmpleadoUseCase {

    private final TelEmpleadoService telEmpleadoService;

    public FindAllTelEmpleadoUseCase(TelEmpleadoService telEmpleadoService) {
        this.telEmpleadoService = telEmpleadoService;
    }

    public List<TelEmpleado> execute() {
        return telEmpleadoService.findAllProductos();
    }

}
