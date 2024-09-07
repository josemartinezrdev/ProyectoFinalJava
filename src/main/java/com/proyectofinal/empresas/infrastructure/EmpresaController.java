package com.proyectofinal.empresas.infrastructure;

import java.util.List;

import com.proyectofinal.App;
import com.proyectofinal.empresas.application.CreateEmpresaUseCase;
import com.proyectofinal.empresas.application.DeleteEmpresaUseCase;
import com.proyectofinal.empresas.application.FindAllEmpresaUseCase;
import com.proyectofinal.empresas.application.FindByIdEmpresaUseCase;
import com.proyectofinal.empresas.application.UpdateEmpresaUseCase;
import com.proyectofinal.empresas.domain.entity.Empresa;
import com.proyectofinal.empresas.domain.service.EmpresaService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EmpresaController {

    private EmpresaService empresaService;

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
    private VBox createEntityVbox;
    @FXML
    private TextField CNameText;

    // Update

    @FXML
    private VBox updateEntityVbox;
    @FXML
    private TextField UCampId;
    @FXML
    private TextField UCampNombre;

    // Delete

    @FXML
    private VBox deleteEntityVbox;
    @FXML
    private TextField DCampId;

    // Find By Id

    @FXML
    private HBox findByIdEntityVbox;

    @FXML
    private TextField FBCampId;
    @FXML
    private Label campid;
    @FXML
    private Label campname;

    // Find All

    @FXML
    private VBox findAllEntityVbox;
    @FXML
    private TableView<Empresa> entitylTableView;
    @FXML
    private TableColumn<Empresa, Integer> colid;
    @FXML
    private TableColumn<Empresa, String> colname;

    @FXML
    public void initialize() {
        this.empresaService = new EmpresaRepository();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }

    private void ajustarVboxes(double formAlto, VBox vbox, double vboxAlto) {
        formContainer.setPrefHeight(formAlto);
        vbox.setPrefHeight(vboxAlto);
    }

    private void ajustarHboxes(double formAlto, HBox vbox, double vboxAlto) {
        formContainer.setPrefHeight(formAlto);
        vbox.setPrefHeight(vboxAlto);
    }

    @FXML
    public void eventAction(javafx.event.ActionEvent event) {
        Object source = event.getSource();

        if (source == btnCreate) {
            ajustarVboxes(0, createEntityVbox, 410);
        } else if (source == btnUpdate) {
            ajustarVboxes(0, updateEntityVbox, 410);
        } else if (source == btnDelete) {
            ajustarVboxes(0, deleteEntityVbox, 410);
        } else if (source == btnFindById) {
            ajustarHboxes(0, findByIdEntityVbox, 410);
        } else if (source == btnFindAll) {
            ajustarVboxes(0, findAllEntityVbox, 410);
            entitylTableView.setMaxWidth(500);
            findAllEntities();
        } else if (source == btnQuit) {
            exit();
        }
    }

    @FXML
    public void createEntity() {
        Empresa empresa = new Empresa();
        empresa.setNombre(CNameText.getText());
        CreateEmpresaUseCase createEmpresaUseCase = new CreateEmpresaUseCase(empresaService);
        createEmpresaUseCase.execute(empresa);
        ajustarVboxes(600, createEntityVbox, 0);
        CNameText.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdEmpresaUseCase findByIdEmpresaUseCase = new FindByIdEmpresaUseCase(empresaService);
        Empresa empresa = findByIdEmpresaUseCase.execute(Integer.parseInt(UCampId.getText()));
        empresa.setNombre(UCampNombre.getText());
        UpdateEmpresaUseCase updateEmpresaUseCase = new UpdateEmpresaUseCase(empresaService);
        updateEmpresaUseCase.execute(empresa);
        ajustarVboxes(600, updateEntityVbox, 0);
        UCampId.setText("");
        UCampNombre.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteEmpresaUseCase deleteEmpresaUseCase = new DeleteEmpresaUseCase(empresaService);
        deleteEmpresaUseCase.execute(Integer.parseInt(DCampId.getText()));
        ajustarVboxes(600, deleteEntityVbox, 0);
        DCampId.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdEmpresaUseCase findByIdEmpresaUseCase = new FindByIdEmpresaUseCase(empresaService);
        Empresa empresa = findByIdEmpresaUseCase.execute(Integer.parseInt(FBCampId.getText()));
        if (empresa != null) {
            campid.setText(String.valueOf(empresa.getId()));
            campname.setText(empresa.getNombre());
        } else {
            campid.setText("Sin Datos");
            campname.setText("...😢");
        }
    }

    public void findAllEntities() {
        ObservableList<Empresa> listaTabla = FXCollections.observableArrayList();
        FindAllEmpresaUseCase findAllEmpresaUseCase = new FindAllEmpresaUseCase(empresaService);
        List<Empresa> empresaes = findAllEmpresaUseCase.execute();
        listaTabla.setAll(empresaes);
        entitylTableView.setItems(listaTabla);
    }

    @FXML
    public void back() {
        try {
            App.setRoot("empresas");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exit() {
        try {
            App.setRoot("crud");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
