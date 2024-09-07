package com.proyectofinal.user.application;

import com.proyectofinal.user.domain.User;
import com.proyectofinal.user.domain.UserService;

public class FindUserUseCase {
    private final UserService userService;

    public FindUserUseCase(UserService userService) {
        this.userService = userService;
    }

    public User execute(int id) {
        return userService.findUserById(id);
    }

}
