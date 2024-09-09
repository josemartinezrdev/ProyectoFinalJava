package com.proyectofinal.detalle_compras.application;

import java.util.List;

import com.proyectofinal.detalle_compras.domain.entity.DetCom;
import com.proyectofinal.detalle_compras.domain.service.DetComService;

public class FindAllDetComUseCase {
    private final DetComService detComService;

    public FindAllDetComUseCase(DetComService detComService) {
        this.detComService = detComService;
    }

    public List<DetCom> execute() {
        return detComService.findAllDetCom();
    }
}
