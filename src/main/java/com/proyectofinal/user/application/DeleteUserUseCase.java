package com.proyectofinal.user.application;

import com.proyectofinal.user.domain.UserService;

public class DeleteUserUseCase {
    private final UserService userService;

    public DeleteUserUseCase(UserService userService) {
        this.userService = userService;
    }

    public void execute(int id) {
        userService.deleteUser(id);
    }

}
