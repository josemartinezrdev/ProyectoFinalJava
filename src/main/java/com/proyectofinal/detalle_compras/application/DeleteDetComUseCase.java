package com.proyectofinal.detalle_compras.application;

import com.proyectofinal.detalle_compras.domain.service.DetComService;

public class DeleteDetComUseCase {
    private final DetComService detComService;

    public DeleteDetComUseCase(DetComService detComService) {
        this.detComService = detComService;
    }

    public void execute(int idcompra, int idpedido) {
        detComService.deleteDetCom(idcompra, idpedido);
    }

}
