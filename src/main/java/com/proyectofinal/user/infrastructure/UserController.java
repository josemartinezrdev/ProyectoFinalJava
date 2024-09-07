package com.proyectofinal.user.infrastructure;

import java.io.IOException;
import java.util.List;

import com.proyectofinal.App;
import com.proyectofinal.user.application.CreateUserUseCase;
import com.proyectofinal.user.application.DeleteUserUseCase;
import com.proyectofinal.user.application.FindAllUserUseCase;
import com.proyectofinal.user.application.FindUserUseCase;
import com.proyectofinal.user.application.UpdateUserUseCase;
import com.proyectofinal.user.domain.User;
import com.proyectofinal.user.domain.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserController {

    private UserService userService;
    // CRUD ENTITY

    @FXML
    private VBox formContainer;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnFindAll;

    @FXML
    private Button btnFind1;

    @FXML
    private Button btnQuit;

    // Create

    @FXML
    private VBox createUserVbox;

    @FXML
    private TextField CNameText;

    @FXML
    private TextField CPassText;

    // Update

    @FXML
    private VBox updateUserVbox;

    @FXML
    private TextField UNameText;

    @FXML
    private TextField UPassText;

    @FXML
    private TextField UIDText;

    // Find By Id
    @FXML
    private HBox findByIdUserVbox;

    @FXML
    private TextField FBIDText;

    @FXML
    private Label campid;
    @FXML
    private Label campname;
    @FXML
    private Label camppass;
    @FXML
    private Label campenabled;

    // Delete

    @FXML
    private VBox deleteUserVbox;

    @FXML
    private TextField DIDText;

    // Find ALL

    @FXML
    private VBox findAllUserVbox;

    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableColumn<User, Integer> idcol;
    @FXML
    private TableColumn<User, Boolean> enablecol;

    @FXML
    private TableColumn<User, String> namecol;
    @FXML
    private TableColumn<User, String> passcol;

    @FXML
    public void initialize() {
        this.userService = new UserRepository();
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        enablecol.setCellValueFactory(new PropertyValueFactory<>("enabled"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("username"));
        passcol.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    @FXML
    private void eventAction(javafx.event.ActionEvent event) {
        Object source = event.getSource();

        if (source == btnCreate) {
            ajustarBoxes(0, createUserVbox, 410);
        } else if (source == btnUpdate) {
            ajustarBoxes(0, updateUserVbox, 410);
        } else if (source == btnDelete) {
            ajustarBoxes(0, deleteUserVbox, 410);
        } else if (source == btnFind1) {
            ajustarHBoxes(0, findByIdUserVbox, 410);
        } else if (source == btnFindAll) {
            ajustarBoxes(0, findAllUserVbox, 410);
            userTableView.setMaxWidth(500);
            findAllUsers();
        } else if (source == btnQuit) {
            exit();
        }
    }

    private void ajustarBoxes(double formAlto, VBox vbox, double vboxAlto) {
        formContainer.setPrefHeight(formAlto);
        vbox.setPrefHeight(vboxAlto);
    }

    private void ajustarHBoxes(double formAlto, HBox vbox, double vboxAlto) {
        formContainer.setPrefHeight(formAlto);
        vbox.setPrefHeight(vboxAlto);
    }

    @FXML
    public void createUser() {
        User user = new User();
        user.setUsername(CNameText.getText());
        user.setPassword(CPassText.getText());
        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userService);
        createUserUseCase.execute(user);
        ajustarBoxes(400, createUserVbox, 0);
        CNameText.setText("");
        CPassText.setText("");
    }

    @FXML
    public void findByIdUser() {
        FindUserUseCase findUserUseCase = new FindUserUseCase(userService);
        User user = findUserUseCase.execute(Integer.parseInt(FBIDText.getText()));
        if (user != null) {
            campid.setText(String.valueOf(user.getId()));
            campname.setText(user.getUsername());
            camppass.setText(user.getPassword());
            campenabled.setText(String.valueOf(user.getEnabled()));
        } else {
            campid.setText("Sin datos");
            campname.setText("...😢");
            camppass.setText("...😢");
            campenabled.setText("...😢");
        }
    }

    @FXML
    public void updateUser() {
        FindUserUseCase findUserUseCase = new FindUserUseCase(userService);
        User user = findUserUseCase.execute(Integer.parseInt(UIDText.getText()));
        user.setUsername(UNameText.getText());
        user.setPassword(UPassText.getText());
        UpdateUserUseCase updateUserUseCase = new UpdateUserUseCase(userService);
        updateUserUseCase.execute(user);
        ajustarBoxes(400, updateUserVbox, 0);
        UIDText.setText("");
        UNameText.setText("");
        UPassText.setText("");
    }

    @FXML
    public void deleteUser() {
        FindUserUseCase findUserUseCase = new FindUserUseCase(userService);
        User user = findUserUseCase.execute(Integer.parseInt(DIDText.getText()));
        DeleteUserUseCase deleteUserUseCase = new DeleteUserUseCase(userService);
        deleteUserUseCase.execute(user.getId());
        ajustarBoxes(400, deleteUserVbox, 0);
        DIDText.setText("");
    }

    @FXML
    public void findAllUsers() {
        ObservableList<User> listaTabla = FXCollections.observableArrayList();

        try {
            FindAllUserUseCase findAllUserUseCase = new FindAllUserUseCase(userService);
            List<User> users = findAllUserUseCase.execute();
            listaTabla.setAll(users);
            userTableView.setItems(listaTabla);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void back() {
        FBIDText.setText("");
        try {
            App.setRoot("users");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exit() {
        try {
            App.setRoot("crud");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
