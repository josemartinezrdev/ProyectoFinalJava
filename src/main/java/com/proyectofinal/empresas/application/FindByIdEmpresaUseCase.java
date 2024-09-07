package com.proyectofinal.empresas.application;

import com.proyectofinal.empresas.domain.entity.Empresa;
import com.proyectofinal.empresas.domain.service.EmpresaService;

public class FindByIdEmpresaUseCase {
    private final EmpresaService empresaService;

    public FindByIdEmpresaUseCase(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    public Empresa execute(int id) {
        return empresaService.findByIdEmpresa(id);
    }
}
