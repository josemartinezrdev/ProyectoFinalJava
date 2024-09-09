package com.proyectofinal.detalle_compras.application;

import com.proyectofinal.detalle_compras.domain.entity.DetCom;
import com.proyectofinal.detalle_compras.domain.service.DetComService;

public class FindByIdDetComUseCase {
    private final DetComService detComService;

    public FindByIdDetComUseCase(DetComService detComService) {
        this.detComService = detComService;
    }

    public DetCom execute(int idcompra, int idpedido) {
        return detComService.findDetComById(idcompra, idpedido);
    }

}
