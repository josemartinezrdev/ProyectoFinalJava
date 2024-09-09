package com.proyectofinal.detalle_compras.domain.service;

import java.util.List;

import com.proyectofinal.detalle_compras.domain.entity.DetCom;

public interface DetComService {
    public void createDetCom(DetCom detcom);

    public void updateDetCom(DetCom detcom, int idcompra, int idpedido);

    public void deleteDetCom(int idcompra, int idpedido);

    public DetCom findDetComById(int idcompra, int idpedido);

    public List<DetCom> findAllDetCom();
}
