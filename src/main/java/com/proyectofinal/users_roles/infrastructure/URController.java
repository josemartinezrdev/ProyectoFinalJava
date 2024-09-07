package com.proyectofinal.users_roles.infrastructure;

import java.io.IOException;
import java.util.List;

import com.proyectofinal.App;
import com.proyectofinal.users_roles.application.CreateUser_rolUseCase;
import com.proyectofinal.users_roles.application.DeleteUser_rolUseCase;
import com.proyectofinal.users_roles.application.FindAllUser_rolUseCase;
import com.proyectofinal.users_roles.application.FindUser_rolUseCase;
import com.proyectofinal.users_roles.application.UpdateUser_rolUseCase;
import com.proyectofinal.users_roles.domain.User_rol;
import com.proyectofinal.users_roles.domain.User_rolService;

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

public class URController {

    private User_rolService user_rolService;
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
    private Button btnFindById;

    @FXML
    private Button btnQuit;

    // Create

    @FXML
    private VBox createURVbox;

    @FXML
    private TextField CUserText;

    @FXML
    private TextField CRolText;

    // Update

    @FXML
    private VBox updateURVbox;

    @FXML
    private TextField UUserIdTxt;

    @FXML
    private TextField URolIdTxt;

    @FXML
    private TextField UNEWUserIdTxt;

    @FXML
    private TextField UNEWRolIdTxt;

    // Find By Id
    @FXML
    private HBox findByIdURVbox;

    @FXML
    private TextField FBIDUserText;
    @FXML
    private TextField FBIDRolText;

    @FXML
    private Label campUser;

    @FXML
    private Label campRol;

    // Delete

    @FXML
    private VBox deleteURVbox;

    @FXML
    private TextField DIDUserText;

    @FXML
    private TextField DIDRolText;

    // Find ALL

    @FXML
    private VBox findAllURVbox;

    @FXML
    private TableView<User_rol> URTableView;

    @FXML
    private TableColumn<User_rol, Integer> coliduser;

    @FXML
    private TableColumn<User_rol, Integer> colidrol;

    @FXML
    public void initialize() {
        this.user_rolService = new User_rolRepository();
        coliduser.setCellValueFactory(new PropertyValueFactory<>("role_id"));
        colidrol.setCellValueFactory(new PropertyValueFactory<>("user_id"));
    }

    @FXML
    private void eventAction(javafx.event.ActionEvent event) {
        Object source = event.getSource();

        if (source == btnCreate) {
            ajustarBoxes(0, createURVbox, 410);
        } else if (source == btnUpdate) {
            ajustarBoxes(0, updateURVbox, 410);
        } else if (source == btnDelete) {
            ajustarBoxes(0, deleteURVbox, 410);
        } else if (source == btnFindById) {
            ajustarHBoxes(0, findByIdURVbox, 410);
        } else if (source == btnFindAll) {
            ajustarBoxes(0, findAllURVbox, 410);
            URTableView.setMaxWidth(500);
            findAllUserRoles();
        } else if (source == btnQuit) {
            exit();
        }
    }

    private void ajustarBoxes(double formAlto, VBox vbox, double vboxAlto) {
        formContainer.setPrefHeight(formAlto);
        vbox.setPrefHeight(vboxAlto);
    }

    private void ajustarHBoxes(double formAlto, HBox hbox, double hboxAlto) {
        formContainer.setPrefHeight(formAlto);
        hbox.setPrefHeight(hboxAlto);
    }

    @FXML
    public void createUR() {
        User_rol userRole = new User_rol();
        userRole.setUser_id(Integer.parseInt(CUserText.getText()));
        userRole.setRole_id(Integer.parseInt(CRolText.getText()));
        CreateUser_rolUseCase createUser_rolUseCase = new CreateUser_rolUseCase(user_rolService);
        createUser_rolUseCase.execute(userRole);
        ajustarBoxes(400, createURVbox, 0);
        CUserText.setText("");
        CRolText.setText("");
    }

    @FXML
    public void updateUR() {
        FindUser_rolUseCase findUserRoleUseCase = new FindUser_rolUseCase(user_rolService);
        User_rol userRole = findUserRoleUseCase.execute(Integer.parseInt(UUserIdTxt.getText()),
                Integer.parseInt(URolIdTxt.getText()));
        userRole.setUser_id(Integer.parseInt(UNEWUserIdTxt.getText()));
        userRole.setRole_id(Integer.parseInt(UNEWRolIdTxt.getText()));
        UpdateUser_rolUseCase updateUser_rolUseCase = new UpdateUser_rolUseCase(user_rolService);
        updateUser_rolUseCase.execute(userRole);
        ajustarBoxes(400, updateURVbox, 0);
        UUserIdTxt.setText("");
        URolIdTxt.setText("");
        UNEWUserIdTxt.setText("");
        UNEWRolIdTxt.setText("");
    }

    @FXML
    public void deleteUR() {
        DeleteUser_rolUseCase deleteUserRoleUseCase = new DeleteUser_rolUseCase(user_rolService);
        deleteUserRoleUseCase.execute(Integer.parseInt(DIDUserText.getText()), Integer.parseInt(DIDRolText.getText()));
        ajustarBoxes(400, deleteURVbox, 0);
        DIDUserText.setText("");
        DIDRolText.setText("");
    }

    @FXML
    public void findByIdUR() {
        FindUser_rolUseCase findUserRoleUseCase = new FindUser_rolUseCase(user_rolService);
        User_rol userRole = findUserRoleUseCase.execute(Integer.parseInt(FBIDUserText.getText()),
                Integer.parseInt(FBIDRolText.getText()));
        if (userRole != null) {
            campUser.setText(String.valueOf(userRole.getUser_id()));
            campRol.setText(String.valueOf(userRole.getRole_id()));
        }
    }

    @FXML
    public void findAllUserRoles() {
        FindAllUser_rolUseCase findAllUserRoleUseCase = new FindAllUser_rolUseCase(user_rolService);
        List<User_rol> userRoles = findAllUserRoleUseCase.execute();
        ObservableList<User_rol> userRoleList = FXCollections.observableArrayList(userRoles);
        URTableView.setItems(userRoleList);
    }

    @FXML
    public void back() {
        FBIDUserText.setText("");
        FBIDRolText.setText("");
        try {
            App.setRoot("users_roles");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exit() {
        try {
            App.setRoot("crud");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
