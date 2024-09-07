package com.proyectofinal.user.application;

import java.util.List;

import com.proyectofinal.user.domain.User;
import com.proyectofinal.user.domain.UserService;

public class FindAllUserUseCase {
    private final UserService userService;

    public FindAllUserUseCase(UserService userService) {
        this.userService = userService;
    }

    public List<User> execute() {
        return userService.findAllUser();
    }

}
