package com.proyectofinal.detalle_compras.application;

import com.proyectofinal.detalle_compras.domain.entity.DetCom;
import com.proyectofinal.detalle_compras.domain.service.DetComService;

public class CreateDetComUseCase {

    private final DetComService detComService;

    public CreateDetComUseCase(DetComService detComService) {
        this.detComService = detComService;
    }

    public void execute(DetCom detCom) {
        detComService.createDetCom(detCom);
    }

}
