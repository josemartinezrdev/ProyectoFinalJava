package com.proyectofinal.empresas.application;

import com.proyectofinal.empresas.domain.entity.Empresa;
import com.proyectofinal.empresas.domain.service.EmpresaService;

public class UpdateEmpresaUseCase {
    private final EmpresaService empresaService;

    public UpdateEmpresaUseCase(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    public void execute(Empresa empresa) {
        empresaService.updateEmpresa(empresa);
    }
}
