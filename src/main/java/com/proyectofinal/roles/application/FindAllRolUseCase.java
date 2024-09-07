package com.proyectofinal.roles.application;

import java.util.List;

import com.proyectofinal.roles.domain.Rol;
import com.proyectofinal.roles.domain.RolService;


public class FindAllRolUseCase {
    private final RolService rolService;

    public FindAllRolUseCase(RolService rolService) {
        this.rolService = rolService;
    }

    public List<Rol> execute(){
        return rolService.findAllRol();
    }

}
