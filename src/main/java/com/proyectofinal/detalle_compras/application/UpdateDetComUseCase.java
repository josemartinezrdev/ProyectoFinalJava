package com.proyectofinal.detalle_compras.application;

import com.proyectofinal.detalle_compras.domain.entity.DetCom;
import com.proyectofinal.detalle_compras.domain.service.DetComService;

public class UpdateDetComUseCase {
    private final DetComService detComService;

    public UpdateDetComUseCase(DetComService detComService) {
        this.detComService = detComService;
    }

    public void execute(DetCom detCom, int idcompra, int idpedido) {
        detComService.updateDetCom(detCom, idcompra, idpedido);
    }
}
