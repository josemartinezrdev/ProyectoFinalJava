package com.proyectofinal.roles.infrastructure;

import java.io.IOException;
import java.util.List;

import com.proyectofinal.App;
import com.proyectofinal.roles.domain.RolService;
import com.proyectofinal.roles.application.CreateRolUseCase;
import com.proyectofinal.roles.application.DeleteRolUseCase;
import com.proyectofinal.roles.application.FindAllRolUseCase;
import com.proyectofinal.roles.application.FindRolUseCase;
import com.proyectofinal.roles.application.UpdateRolUseCase;
import com.proyectofinal.roles.domain.Rol;
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

public class RolController {

    private RolService rolService;
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
    private VBox createRolVbox;

    @FXML
    private TextField CNameText;

    // Update

    @FXML
    private VBox updateRolVbox;

    @FXML
    private TextField UNameText;

    @FXML
    private TextField UIDText;

    // Find By Id
    @FXML
    private HBox findByIdRolVbox;

    @FXML
    private TextField FBIDText;

    @FXML
    private Label campid;
    @FXML
    private Label campname;

    // Delete

    @FXML
    private VBox deleteRolVbox;

    @FXML
    private TextField DIDText;

    // Find ALL

    @FXML
    private VBox findAllRolVbox;

    @FXML
    private TableView<Rol> rolTableView;
    @FXML
    private TableColumn<Rol, Integer> idcol;

    @FXML
    private TableColumn<Rol, String> namecol;

    @FXML
    public void initialize() {
        this.rolService = new RolRepository();
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    @FXML
    private void eventAction(javafx.event.ActionEvent event) {
        Object source = event.getSource();

        if (source == btnCreate) {
            ajustarBoxes(0, createRolVbox, 410);
        } else if (source == btnUpdate) {
            ajustarBoxes(0, updateRolVbox, 410);
        } else if (source == btnDelete) {
            ajustarBoxes(0, deleteRolVbox, 410);
        } else if (source == btnFindById) {
            ajustarHBoxes(0, findByIdRolVbox, 410);
        } else if (source == btnFindAll) {
            ajustarBoxes(0, findAllRolVbox, 410);
            rolTableView.setMaxWidth(500);
            findAllRols();
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
    public void createRol() {
        Rol rol = new Rol();
        rol.setName(CNameText.getText());
        CreateRolUseCase createRolUseCase = new CreateRolUseCase(rolService);
        createRolUseCase.execute(rol);
        ajustarBoxes(400, createRolVbox, 0);
        CNameText.setText("");
    }

    @FXML
    public void findByIdRol() {
        FindRolUseCase findRolUseCase = new FindRolUseCase(rolService);
        Rol rol = findRolUseCase.execute(Integer.parseInt(FBIDText.getText()));
        if (rol != null) {
            campid.setText(String.valueOf(rol.getId()));
            campname.setText(rol.getName());
        } else {
            campid.setText("Sin datos");
            campname.setText("...😢");
        }
    }

    @FXML
    public void updateRol() {
        FindRolUseCase findRolUseCase = new FindRolUseCase(rolService);
        Rol rol = findRolUseCase.execute(Integer.parseInt(UIDText.getText()));
        rol.setName(UNameText.getText());
        UpdateRolUseCase updateRolUseCase = new UpdateRolUseCase(rolService);
        updateRolUseCase.execute(rol);
        ajustarBoxes(400, updateRolVbox, 0);
        UIDText.setText("");
        UNameText.setText("");
    }

    @FXML
    public void deleteRol() {
        FindRolUseCase findRolUseCase = new FindRolUseCase(rolService);
        Rol rol = findRolUseCase.execute(Integer.parseInt(DIDText.getText()));
        DeleteRolUseCase deleteRolUseCase = new DeleteRolUseCase(rolService);
        deleteRolUseCase.execute(rol.getId());
        ajustarBoxes(400, deleteRolVbox, 0);
        DIDText.setText("");
    }

    @FXML
    public void findAllRols() {
        ObservableList<Rol> listaTabla = FXCollections.observableArrayList();

        try {
            FindAllRolUseCase findAllRolUseCase = new FindAllRolUseCase(rolService);
            List<Rol> rols = findAllRolUseCase.execute();
            listaTabla.setAll(rols);
            rolTableView.setItems(listaTabla);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void back() {
        FBIDText.setText("");
        try {
            App.setRoot("roles");
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
