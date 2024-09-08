package com.proyectofinal.bodegas.infrastructure;

import java.util.List;

import com.proyectofinal.App;
import com.proyectofinal.bodegas.application.CreateBodegaUseCase;
import com.proyectofinal.bodegas.application.DeleteBodegaUseCase;
import com.proyectofinal.bodegas.application.FindAllBodegaUseCase;
import com.proyectofinal.bodegas.application.FindByIdBodegaUseCase;
import com.proyectofinal.bodegas.application.UpdateBodegaUseCase;
import com.proyectofinal.bodegas.domain.entity.Bodega;
import com.proyectofinal.bodegas.domain.service.BodegaService;

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

public class BodegaController {
    private BodegaService bodegaService;

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
    private TextField CDireccionIdText;

    // Update

    @FXML
    private VBox updateEntityVbox;
    @FXML
    private TextField UCampId;
    @FXML
    private TextField UCampNombre;
    @FXML
    private TextField UCampIdDireccion;

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
    private TableView<Bodega> entitylTableView;
    @FXML
    private TableColumn<Bodega, Integer> colid;
    @FXML
    private TableColumn<Bodega, String> colname;
    @FXML
    private TableColumn<Bodega, Integer> coliddireccion;

    @FXML
    public void initialize() {
        this.bodegaService = new BodegaRepository();
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
        Bodega bodega = new Bodega();
        bodega.setNombre(CNameText.getText());
        bodega.setIddireccion(Integer.parseInt(CDireccionIdText.getText()));
        CreateBodegaUseCase createBodegaUseCase = new CreateBodegaUseCase(bodegaService);
        createBodegaUseCase.execute(bodega);
        ajustarVboxes(600, createEntityVbox, 0);
        CNameText.setText("");
        CDireccionIdText.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdBodegaUseCase findByIdBodegaUseCase = new FindByIdBodegaUseCase(bodegaService);
        Bodega bodega = findByIdBodegaUseCase.execute(Integer.parseInt(UCampId.getText()));
        bodega.setNombre(UCampNombre.getText());
        bodega.setIddireccion(Integer.parseInt(UCampIdDireccion.getText()));
        UpdateBodegaUseCase updateBodegaUseCase = new UpdateBodegaUseCase(bodegaService);
        updateBodegaUseCase.execute(bodega);
        ajustarVboxes(600, updateEntityVbox, 0);
        UCampId.setText("");
        UCampNombre.setText("");
        UCampIdDireccion.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteBodegaUseCase deleteBodegaUseCase = new DeleteBodegaUseCase(bodegaService);
        deleteBodegaUseCase.execute(Integer.parseInt(DCampId.getText()));
        ajustarVboxes(600, deleteEntityVbox, 0);
        DCampId.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdBodegaUseCase findByIdBodegaUseCase = new FindByIdBodegaUseCase(bodegaService);
        Bodega bodega = findByIdBodegaUseCase.execute(Integer.parseInt(FBCampId.getText()));
        if (bodega != null) {
            campid.setText(String.valueOf(bodega.getId()));
            campname.setText(bodega.getNombre());
            campiddireccion.setText(String.valueOf(bodega.getIddireccion()));
        } else {
            campid.setText("Sin Datos");
            campname.setText("...😢");
            campiddireccion.setText("...😢");
        }
    }

    public void findAllEntities() {
        ObservableList<Bodega> listaTabla = FXCollections.observableArrayList();
        FindAllBodegaUseCase findAllBodegaUseCase = new FindAllBodegaUseCase(bodegaService);
        List<Bodega> bodegas = findAllBodegaUseCase.execute();
        listaTabla.setAll(bodegas);
        entitylTableView.setItems(listaTabla);
    }

    @FXML
    public void back() {
        try {
            App.setRoot("bodegas");
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