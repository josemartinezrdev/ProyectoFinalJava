package com.proyectofinal.roles.application;


import com.proyectofinal.roles.domain.Rol;
import com.proyectofinal.roles.domain.RolService;

public class FindRolUseCase {
    private final RolService rolService;

    public FindRolUseCase(RolService rolService) {
        this.rolService = rolService;
    }

    public Rol execute(int id) {
        return rolService.findRolById(id);
    }

}
