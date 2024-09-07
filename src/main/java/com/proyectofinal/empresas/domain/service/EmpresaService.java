package com.proyectofinal.empresas.domain.service;

import java.util.List;

import com.proyectofinal.empresas.domain.entity.Empresa;

public interface EmpresaService {
    public void createEmpresa(Empresa empresa);

    public void updateEmpresa(Empresa empresa);

    public void deleteEmpresa(int id);

    public List<Empresa> findAllEmpresas();

    public Empresa findByIdEmpresa(int id);
}
