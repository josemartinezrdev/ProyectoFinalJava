package com.proyectofinal.sucursales.infrastructure;

import java.util.List;
import com.proyectofinal.App;
import com.proyectofinal.sucursales.application.CreateSucursalUseCase;
import com.proyectofinal.sucursales.application.DeleteSucursalUseCase;
import com.proyectofinal.sucursales.application.FindAllSucursalUseCase;
import com.proyectofinal.sucursales.application.FindByIdSucursalUseCase;
import com.proyectofinal.sucursales.application.UpdateSucursalUseCase;
import com.proyectofinal.sucursales.domain.entity.Sucursal;
import com.proyectofinal.sucursales.domain.service.SucursalService;

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

public class SucursalController {

    private SucursalService sucursalService;

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
    private TextField CNombreText;
    @FXML
    private TextField CDireccionIdText;
    @FXML
    private TextField CEmpresaIdText;

    // Update

    @FXML
    private VBox updateEntityVbox;
    @FXML
    private TextField UCampId;
    @FXML
    private TextField UCampNombre;
    @FXML
    private TextField UCampIdDireccion;
    @FXML
    private TextField UCampIdEmpresa;

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
    private Label campnombre;
    @FXML
    private Label campiddireccion;
    @FXML
    private Label campidempresa;

    // Find All

    @FXML
    private VBox findAllEntityVbox;
    @FXML
    private TableView<Sucursal> entitylTableView;
    @FXML
    private TableColumn<Sucursal, Integer> colid;
    @FXML
    private TableColumn<Sucursal, String> colnombre;
    @FXML
    private TableColumn<Sucursal, Integer> coliddireccion;
    @FXML
    private TableColumn<Sucursal, Integer> colidempresa;

    @FXML
    public void initialize() {
        this.sucursalService = new SucursalRepository();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        coliddireccion.setCellValueFactory(new PropertyValueFactory<>("iddireccion"));
        colidempresa.setCellValueFactory(new PropertyValueFactory<>("idempresa"));
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
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(CNombreText.getText());
        sucursal.setIddireccion(Integer.parseInt(CDireccionIdText.getText()));
        sucursal.setIdempresa(Integer.parseInt(CEmpresaIdText.getText()));

        CreateSucursalUseCase createSucursalUseCase = new CreateSucursalUseCase(sucursalService);
        createSucursalUseCase.execute(sucursal);

        ajustarVboxes(600, createEntityVbox, 0);
        CNombreText.setText("");
        CDireccionIdText.setText("");
        CEmpresaIdText.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdSucursalUseCase findByIdSucursalUseCase = new FindByIdSucursalUseCase(sucursalService);
        Sucursal sucursal = findByIdSucursalUseCase.execute(Integer.parseInt(UCampId.getText()));

        sucursal.setNombre(UCampNombre.getText());
        sucursal.setIddireccion(Integer.parseInt(UCampIdDireccion.getText()));
        sucursal.setIdempresa(Integer.parseInt(UCampIdEmpresa.getText()));

        UpdateSucursalUseCase updateSucursalUseCase = new UpdateSucursalUseCase(sucursalService);
        updateSucursalUseCase.execute(sucursal);

        ajustarVboxes(600, updateEntityVbox, 0);
        UCampId.setText("");
        UCampNombre.setText("");
        UCampIdDireccion.setText("");
        UCampIdEmpresa.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteSucursalUseCase deleteSucursalUseCase = new DeleteSucursalUseCase(sucursalService);
        deleteSucursalUseCase.execute(Integer.parseInt(DCampId.getText()));

        ajustarVboxes(600, deleteEntityVbox, 0);
        DCampId.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdSucursalUseCase findByIdSucursalUseCase = new FindByIdSucursalUseCase(sucursalService);
        Sucursal sucursal = findByIdSucursalUseCase.execute(Integer.parseInt(FBCampId.getText()));
        if (sucursal != null) {
            campid.setText(String.valueOf(sucursal.getId()));
            campnombre.setText(sucursal.getNombre());
            campiddireccion.setText(String.valueOf(sucursal.getIddireccion()));
            campidempresa.setText(String.valueOf(sucursal.getIdempresa()));
        } else {
            campid.setText("Sin Datos");
            campnombre.setText("...😢");
            campiddireccion.setText("...😢");
            campidempresa.setText("...😢");
        }
    }

    public void findAllEntities() {
        ObservableList<Sucursal> listaTabla = FXCollections.observableArrayList();
        FindAllSucursalUseCase findAllSucursalUseCase = new FindAllSucursalUseCase(sucursalService);
        List<Sucursal> sucursales = findAllSucursalUseCase.execute();
        listaTabla.setAll(sucursales);
        entitylTableView.setItems(listaTabla);
    }

    @FXML
    public void back() {
        try {
            App.setRoot("sucursales");
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
