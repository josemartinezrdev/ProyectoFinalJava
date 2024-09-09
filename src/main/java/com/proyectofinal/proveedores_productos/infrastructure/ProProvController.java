package com.proyectofinal.proveedores_productos.infrastructure;

import java.io.IOException;
import java.util.List;

import com.proyectofinal.App;
import com.proyectofinal.proveedores_productos.application.CreateProProvUseCase;
import com.proyectofinal.proveedores_productos.application.DeleteProProvUseCase;
import com.proyectofinal.proveedores_productos.application.FindAllProProvUseCase;
import com.proyectofinal.proveedores_productos.application.FindByIdProProvUseCase;
import com.proyectofinal.proveedores_productos.application.UpdateProProvUseCase;
import com.proyectofinal.proveedores_productos.domain.entity.ProProv;
import com.proyectofinal.proveedores_productos.domain.service.ProProvService;

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

public class ProProvController {

    private ProProvService proprovService;
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
    private TextField CProductoIdText;

    @FXML
    private TextField CProveedorIdText;

    // Update

    @FXML
    private VBox updateEntityVbox;

    @FXML
    private TextField UCampIdProducto;

    @FXML
    private TextField UCampIdProveedor;

    @FXML
    private TextField UNEWCampIdProducto;

    @FXML
    private TextField UNEWCampIdProveedor;

    // Delete

    @FXML
    private VBox deleteEntityVbox;

    @FXML
    private TextField DCampIdProducto;

    @FXML
    private TextField DCampIdProveedor;

    // Find By Id
    @FXML
    private HBox findByIdEntityVbox;

    @FXML
    private TextField FBProductoId;

    @FXML
    private TextField FBProveedorId;

    @FXML
    private Label campidproducto;

    @FXML
    private Label campidproveedor;

    // Find ALL

    @FXML
    private VBox findAllEntityVbox;

    @FXML
    private TableView<ProProv> entitylTableView;

    @FXML
    private TableColumn<ProProv, Integer> colidproducto;

    @FXML
    private TableColumn<ProProv, Integer> colidproveedor;

    @FXML
    public void initialize() {
        this.proprovService = new ProProvRepository();
        colidproducto.setCellValueFactory(new PropertyValueFactory<>("idproducto"));
        colidproveedor.setCellValueFactory(new PropertyValueFactory<>("idproveedor"));
    }

    @FXML
    private void eventAction(javafx.event.ActionEvent event) {
        Object soEntityce = event.getSource();

        if (soEntityce == btnCreate) {
            ajustarBoxes(0, createEntityVbox, 410);
        } else if (soEntityce == btnUpdate) {
            ajustarBoxes(0, updateEntityVbox, 410);
        } else if (soEntityce == btnDelete) {
            ajustarBoxes(0, deleteEntityVbox, 410);
        } else if (soEntityce == btnFindById) {
            ajustarHBoxes(0, findByIdEntityVbox, 410);
        } else if (soEntityce == btnFindAll) {
            ajustarBoxes(0, findAllEntityVbox, 410);
            entitylTableView.setMaxWidth(500);
            findAllProProvs();
        } else if (soEntityce == btnQuit) {
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
    public void createEntity() {
        ProProv proProv = new ProProv();
        proProv.setIdproducto(Integer.parseInt(CProductoIdText.getText()));
        proProv.setIdproveedor(Integer.parseInt(CProveedorIdText.getText()));
        CreateProProvUseCase createProProvUseCase = new CreateProProvUseCase(proprovService);
        createProProvUseCase.execute(proProv);
        ajustarBoxes(600, createEntityVbox, 0);
        CProductoIdText.setText("");
        CProveedorIdText.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdProProvUseCase findProProvUseCase = new FindByIdProProvUseCase(proprovService);
        ProProv proProv = findProProvUseCase.execute(
                Integer.parseInt(UCampIdProducto.getText()),
                Integer.parseInt(UCampIdProveedor.getText()));

        proProv.setIdproducto(Integer.parseInt(UNEWCampIdProducto.getText()));
        proProv.setIdproveedor(Integer.parseInt(UNEWCampIdProveedor.getText()));

        UpdateProProvUseCase updateProProvUseCase = new UpdateProProvUseCase(proprovService);
        updateProProvUseCase.execute(
                proProv,
                Integer.parseInt(UCampIdProducto.getText()),
                Integer.parseInt(UCampIdProveedor.getText()));

        ajustarBoxes(600, updateEntityVbox, 0);
        UCampIdProducto.setText("");
        UCampIdProveedor.setText("");
        UNEWCampIdProducto.setText("");
        UNEWCampIdProveedor.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteProProvUseCase deleteProProvUseCase = new DeleteProProvUseCase(proprovService);
        deleteProProvUseCase.execute(Integer.parseInt(DCampIdProducto.getText()),
                Integer.parseInt(DCampIdProveedor.getText()));
        ajustarBoxes(600, deleteEntityVbox, 0);
        DCampIdProducto.setText("");
        DCampIdProveedor.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdProProvUseCase findProProvUseCase = new FindByIdProProvUseCase(proprovService);
        ProProv proProv = findProProvUseCase.execute(Integer.parseInt(FBProductoId.getText()),
                Integer.parseInt(FBProveedorId.getText()));
        if (proProv != null) {
            campidproducto.setText(String.valueOf(proProv.getIdproducto()));
            campidproveedor.setText(String.valueOf(proProv.getIdproveedor()));
        } else {
            campidproducto.setText("Sin datos");
            campidproveedor.setText("...😢");
        }
    }

    @FXML
    public void findAllProProvs() {
        FindAllProProvUseCase findAllProProvUseCase = new FindAllProProvUseCase(proprovService);
        List<ProProv> proProvs = findAllProProvUseCase.execute();
        ObservableList<ProProv> proProvList = FXCollections.observableArrayList(proProvs);
        entitylTableView.setItems(proProvList);
    }

    @FXML
    public void back() {
        FBProductoId.setText("");
        FBProveedorId.setText("");
        try {
            App.setRoot("productos_bodegas");
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
