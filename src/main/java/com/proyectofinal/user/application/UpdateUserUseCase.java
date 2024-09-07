package com.proyectofinal.user.application;

import com.proyectofinal.user.domain.User;
import com.proyectofinal.user.domain.UserService;

public class UpdateUserUseCase {
    private final UserService userService;

    public UpdateUserUseCase(UserService userService) {
        this.userService = userService;
    }

    public void execute(User user) {
        userService.updateUser(user);
    }
}
