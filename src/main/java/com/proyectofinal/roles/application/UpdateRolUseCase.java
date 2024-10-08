package com.proyectofinal.roles.application;

import com.proyectofinal.roles.domain.Rol;
import com.proyectofinal.roles.domain.RolService;

public class UpdateRolUseCase {
    private final RolService rolService;

    public UpdateRolUseCase(RolService rolService) {
        this.rolService = rolService;
    }

    public void execute(Rol rol) {
        rolService.updateRol(rol);
    }

}
