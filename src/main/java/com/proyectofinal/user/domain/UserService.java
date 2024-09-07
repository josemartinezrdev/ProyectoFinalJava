package com.proyectofinal.user.domain;

import java.util.List;

public interface UserService {
    void createUser(User user);

    void updateUser(User user);

    void deleteUser(int id);

    User findUserById(int id);

    List<User> findAllUser();

    Boolean findUserByName(String name, String password);
}
