package com.proyectofinal.tipos_clientes.domain.service;

import java.util.List;

import com.proyectofinal.tipos_clientes.domain.entity.TipCliente;

public interface TipClienteService {

    public void createTipCliente(TipCliente tipcliente);

    public void updateTipCliente(TipCliente tipcliente);

    public void deleteTipCliente(int id);

    public List<TipCliente> findAllTipClientees();

    public TipCliente findByIdTipCliente(int id);
}
