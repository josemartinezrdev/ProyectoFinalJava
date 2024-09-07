package com.proyectofinal.empresas.application;

import com.proyectofinal.empresas.domain.entity.Empresa;
import com.proyectofinal.empresas.domain.service.EmpresaService;

public class CreateEmpresaUseCase {
    private final EmpresaService empresaService;

    public CreateEmpresaUseCase(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    public void execute(Empresa empresa) {
        empresaService.createEmpresa(empresa);
    }
}
