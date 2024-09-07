package com.proyectofinal.direcciones.infrastructure;

import java.util.List;
import com.proyectofinal.App;
import com.proyectofinal.direcciones.application.CreateDireccionUseCase;
import com.proyectofinal.direcciones.application.DeleteDireccionUseCase;
import com.proyectofinal.direcciones.application.FindAllDireccionUseCase;
import com.proyectofinal.direcciones.application.FindByIdDireccionUseCase;
import com.proyectofinal.direcciones.application.UpdateDireccionUseCase;
import com.proyectofinal.direcciones.domain.entity.Direccion;
import com.proyectofinal.direcciones.domain.service.DireccionService;

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

public class DireccionController {

    private DireccionService direccionService;

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
    private TextField CCalleText;
    @FXML
    private TextField CCarreraText;
    @FXML
    private TextField CCiudadIdText;

    // Update

    @FXML
    private VBox updateEntityVbox;
    @FXML
    private TextField UCampId;
    @FXML
    private TextField UCampCalle;
    @FXML
    private TextField UCampCarrera;
    @FXML
    private TextField UCampIdCiudad;

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
    private Label campcalle;
    @FXML
    private Label campcarrera;
    @FXML
    private Label campidciudad;

    // Find All

    @FXML
    private VBox findAllEntityVbox;
    @FXML
    private TableView<Direccion> entitylTableView;
    @FXML
    private TableColumn<Direccion, Integer> colid;
    @FXML
    private TableColumn<Direccion, String> colcalle;
    @FXML
    private TableColumn<Direccion, String> colcarrera;
    @FXML
    private TableColumn<Direccion, Integer> colidciudad;

    @FXML
    public void initialize() {
        this.direccionService = new DireccionRepository();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colcalle.setCellValueFactory(new PropertyValueFactory<>("calle"));
        colcarrera.setCellValueFactory(new PropertyValueFactory<>("carrera"));
        colidciudad.setCellValueFactory(new PropertyValueFactory<>("idciudad"));
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
        Direccion direccion = new Direccion();
        direccion.setCalle(CCalleText.getText());
        direccion.setCarrera(CCarreraText.getText());
        direccion.setIdciudad(Integer.parseInt(CCiudadIdText.getText()));

        CreateDireccionUseCase createDireccionUseCase = new CreateDireccionUseCase(direccionService);
        createDireccionUseCase.execute(direccion);

        ajustarVboxes(600, createEntityVbox, 0);
        CCalleText.setText("");
        CCarreraText.setText("");
        CCiudadIdText.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdDireccionUseCase findByIdDireccionUseCase = new FindByIdDireccionUseCase(direccionService);
        Direccion direccion = findByIdDireccionUseCase.execute(Integer.parseInt(UCampId.getText()));

        direccion.setCalle(UCampCalle.getText());
        direccion.setCarrera(UCampCarrera.getText());
        direccion.setIdciudad(Integer.parseInt(UCampIdCiudad.getText()));

        UpdateDireccionUseCase updateDireccionUseCase = new UpdateDireccionUseCase(direccionService);
        updateDireccionUseCase.execute(direccion);

        ajustarVboxes(600, updateEntityVbox, 0);
        UCampId.setText("");
        UCampCalle.setText("");
        UCampCarrera.setText("");
        UCampIdCiudad.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteDireccionUseCase deleteDireccionUseCase = new DeleteDireccionUseCase(direccionService);
        deleteDireccionUseCase.execute(Integer.parseInt(DCampId.getText()));

        ajustarVboxes(600, deleteEntityVbox, 0);
        DCampId.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdDireccionUseCase findByIdDireccionUseCase = new FindByIdDireccionUseCase(direccionService);
        Direccion direccion = findByIdDireccionUseCase.execute(Integer.parseInt(FBCampId.getText()));
        if (direccion != null) {
            campid.setText(String.valueOf(direccion.getId()));
            campcalle.setText(direccion.getCalle());
            campcarrera.setText(direccion.getCarrera());
            campidciudad.setText(String.valueOf(direccion.getIdciudad()));
        } else {
            campid.setText("Sin Datos");
            campcalle.setText("...😢");
            campcarrera.setText("...😢");
            campidciudad.setText("...😢");
        }
    }

    public void findAllEntities() {
        ObservableList<Direccion> listaTabla = FXCollections.observableArrayList();
        FindAllDireccionUseCase findAllDireccionUseCase = new FindAllDireccionUseCase(direccionService);
        List<Direccion> direcciones = findAllDireccionUseCase.execute();
        listaTabla.setAll(direcciones);
        entitylTableView.setItems(listaTabla);
    }

    @FXML
    public void back() {
        try {
            App.setRoot("direcciones");
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
