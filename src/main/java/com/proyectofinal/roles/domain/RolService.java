package com.proyectofinal.roles.domain;

import java.util.List;

public interface RolService {
    void createRol(Rol rol);

    void updateRol(Rol rol);

    void deleteRol(int id);

    Rol findRolById(int id);

    List<Rol> findAllRol();
}
