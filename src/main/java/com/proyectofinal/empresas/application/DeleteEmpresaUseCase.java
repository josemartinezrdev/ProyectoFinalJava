package com.proyectofinal.empresas.application;

import com.proyectofinal.empresas.domain.service.EmpresaService;

public class DeleteEmpresaUseCase {
    private final EmpresaService empresaService;

    public DeleteEmpresaUseCase(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    public void execute(int id) {
        empresaService.deleteEmpresa(id);
    }
}
