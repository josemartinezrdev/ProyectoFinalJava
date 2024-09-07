package com.proyectofinal.users_roles.domain;

import java.util.List;

public interface User_rolService {
    void createUser_rol(User_rol user_rol);

    void updateUser_rol(User_rol user_rol);

    void deleteUser_rol(int user_id, int role_id);

    User_rol findUser_rolById(int user_id, int role_id);

    List<User_rol> findAllUser_rol();
}
