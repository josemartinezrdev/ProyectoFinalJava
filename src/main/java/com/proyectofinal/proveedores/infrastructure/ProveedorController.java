package com.proyectofinal.proveedores.infrastructure;

import java.util.List;

import com.proyectofinal.App;
import com.proyectofinal.proveedores.application.CreateProveedorUseCase;
import com.proyectofinal.proveedores.application.DeleteProveedorUseCase;
import com.proyectofinal.proveedores.application.FindAllProveedorUseCase;
import com.proyectofinal.proveedores.application.FindByIdProveedorUseCase;
import com.proyectofinal.proveedores.application.UpdateProveedorUseCase;
import com.proyectofinal.proveedores.domain.entity.Proveedor;
import com.proyectofinal.proveedores.domain.service.ProveedorService;

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

public class ProveedorController {

    private ProveedorService proveedorService;

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
    @FXML
    private TextField CDireccionIdText; // ++ Nuevo

    // Update

    @FXML
    private VBox updateEntityVbox;
    @FXML
    private TextField UCampId;
    @FXML
    private TextField UCampNombre;
    @FXML
    private TextField UCampIdDireccion; // ++ Nuevo

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
    @FXML
    private Label campiddireccion;

    // Find All

    @FXML
    private VBox findAllEntityVbox;
    @FXML
    private TableView<Proveedor> entitylTableView;
    @FXML
    private TableColumn<Proveedor, Integer> colid;
    @FXML
    private TableColumn<Proveedor, String> colname;
    @FXML
    private TableColumn<Proveedor, Integer> coliddireccion;

    @FXML
    public void initialize() {
        this.proveedorService = new ProveedorRepository();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        coliddireccion.setCellValueFactory(new PropertyValueFactory<>("iddireccion"));
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
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(CNameText.getText());
        proveedor.setIddireccion(Integer.parseInt(CDireccionIdText.getText()));
        CreateProveedorUseCase createProveedorUseCase = new CreateProveedorUseCase(proveedorService);
        createProveedorUseCase.execute(proveedor);
        ajustarVboxes(600, createEntityVbox, 0);
        CNameText.setText("");
        CDireccionIdText.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdProveedorUseCase findByIdProveedorUseCase = new FindByIdProveedorUseCase(proveedorService);
        Proveedor proveedor = findByIdProveedorUseCase.execute(Integer.parseInt(UCampId.getText()));
        proveedor.setNombre(UCampNombre.getText());
        proveedor.setIddireccion(Integer.parseInt(UCampIdDireccion.getText()));
        UpdateProveedorUseCase updateProveedorUseCase = new UpdateProveedorUseCase(proveedorService);
        updateProveedorUseCase.execute(proveedor);
        ajustarVboxes(600, updateEntityVbox, 0);
        UCampId.setText("");
        UCampNombre.setText("");
        UCampIdDireccion.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteProveedorUseCase deleteProveedorUseCase = new DeleteProveedorUseCase(proveedorService);
        deleteProveedorUseCase.execute(Integer.parseInt(DCampId.getText()));
        ajustarVboxes(600, deleteEntityVbox, 0);
        DCampId.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdProveedorUseCase findByIdProveedorUseCase = new FindByIdProveedorUseCase(proveedorService);
        Proveedor proveedor = findByIdProveedorUseCase.execute(Integer.parseInt(FBCampId.getText()));
        if (proveedor != null) {
            campid.setText(String.valueOf(proveedor.getId()));
            campname.setText(proveedor.getNombre());
            campiddireccion.setText(String.valueOf(proveedor.getIddireccion()));
        } else {
            campid.setText("Sin Datos");
            campname.setText("...😢");
            campiddireccion.setText("...😢");
        }
    }

    public void findAllEntities() {
        ObservableList<Proveedor> listaTabla = FXCollections.observableArrayList();
        FindAllProveedorUseCase findAllProveedorUseCase = new FindAllProveedorUseCase(proveedorService);
        List<Proveedor> proveedores = findAllProveedorUseCase.execute();
        listaTabla.setAll(proveedores);
        entitylTableView.setItems(listaTabla);
    }

    @FXML
    public void back() {
        try {
            App.setRoot("proveedores");
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