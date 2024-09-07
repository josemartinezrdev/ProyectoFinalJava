package com.proyectofinal.user.application;

import com.proyectofinal.user.domain.User;
import com.proyectofinal.user.domain.UserService;

public class CreateUserUseCase {
    private final UserService userService;

    public CreateUserUseCase(UserService userService) {
        this.userService = userService;
    }

    public void execute(User user) {
        userService.createUser(user);
    }

}