package com.proyectofinal.roles.application;

import com.proyectofinal.roles.domain.RolService;
import com.proyectofinal.roles.domain.Rol;

public class CreateRolUseCase {
    private final RolService rolService;

    public CreateRolUseCase(RolService rolService) {
        this.rolService = rolService;
    }

    public void execute(Rol rol){
        rolService.createRol(rol);
    }

}
