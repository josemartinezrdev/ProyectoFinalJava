package com.proyectofinal.empresas.application;

import java.util.List;

import com.proyectofinal.empresas.domain.entity.Empresa;
import com.proyectofinal.empresas.domain.service.EmpresaService;

public class FindAllEmpresaUseCase {
    private final EmpresaService empresaService;

    public FindAllEmpresaUseCase(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    public List<Empresa> execute() {
        return empresaService.findAllEmpresas();
    }
}
