package com.proyectofinal;

import java.io.IOException;

import com.proyectofinal.user.application.CreateUserUseCase;
import com.proyectofinal.user.application.FindUserByNameUseCase;
import com.proyectofinal.user.domain.User;
import com.proyectofinal.user.domain.UserService;
import com.proyectofinal.user.infrastructure.UserRepository;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class HomeController {

    // Home

    @FXML
    private VBox formContainer;

    // Register

    @FXML
    private VBox createUserVbox;

    @FXML
    private TextField CNameText;
    @FXML
    private TextField CPassText;

    // Log In

    @FXML
    private VBox loginVbox;

    @FXML
    private TextField campname;
    @FXML
    private TextField camppass;
    @FXML
    private Label txtinfo;

    private UserService userService;
    private FindUserByNameUseCase findUserByNameUseCase;

    public void initialize() {
        this.userService = new UserRepository();
        this.findUserByNameUseCase = new FindUserByNameUseCase(userService);
    }

    @FXML
    public void menuCreateUser() {
        ajustarBoxes(formContainer, 0, createUserVbox, 410);
    }

    @FXML
    public void createUser() {
        User user = new User();
        user.setUsername(CNameText.getText());
        user.setPassword(CPassText.getText());
        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userService);
        createUserUseCase.execute(user);
        ajustarBoxes(formContainer, 700, createUserVbox, 0);
        CNameText.setText("");
        CPassText.setText("");
    }

    @FXML
    public void findUserByName() {
        ajustarBoxes(formContainer, 0, loginVbox, 410);

        if (campname.getText() == null || campname.getText().trim().isEmpty() ||
                camppass.getText() == null || camppass.getText().trim().isEmpty()) {
            txtinfo.setText("Por favor, ingrese usuario y contraseña.");
            return;
        }
        Boolean isAdmin = findUserByNameUseCase.execute(campname.getText(), camppass.getText());

        if (Boolean.TRUE.equals(isAdmin)) {
            txtinfo.setText("");
            try {
                App.setRoot("crud");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (Boolean.FALSE.equals(isAdmin)) {
            txtinfo.setText("");
            System.out.println("Llamar ventas");
        } else {
            txtinfo.setText("Credenciales Incorrectas");
        }
    }

    private void ajustarBoxes(VBox vboxparent, double formAlto, VBox vbox, double vboxAlto) {
        vboxparent.setPrefHeight(formAlto);
        vbox.setPrefHeight(vboxAlto);
    }

    @FXML
    private void exit() {
        System.exit(0);
    }

}
