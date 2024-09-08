package com.proyectofinal.estados.infrastructure;

import java.util.List;

import com.proyectofinal.App;
import com.proyectofinal.estados.application.CreateEstadoUseCase;
import com.proyectofinal.estados.application.DeleteEstadoUseCase;
import com.proyectofinal.estados.application.FindAllEstadoUseCase;
import com.proyectofinal.estados.application.FindByIdEstadoUseCase;
import com.proyectofinal.estados.application.UpdateEstadoUseCase;
import com.proyectofinal.estados.domain.entity.Estado;
import com.proyectofinal.estados.domain.service.EstadoService;

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

public class EstadoController {

    private EstadoService estadoService;

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
    private TableView<Estado> entitylTableView;
    @FXML
    private TableColumn<Estado, Integer> colid;
    @FXML
    private TableColumn<Estado, String> colname;

    @FXML
    public void initialize() {
        this.estadoService = new EstadoRepository();
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
        Estado estado = new Estado();
        estado.setNombre(CNameText.getText());
        CreateEstadoUseCase createEstadoUseCase = new CreateEstadoUseCase(estadoService);
        createEstadoUseCase.execute(estado);
        ajustarVboxes(600, createEntityVbox, 0);
        CNameText.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdEstadoUseCase findByIdEstadoUseCase = new FindByIdEstadoUseCase(estadoService);
        Estado estado = findByIdEstadoUseCase.execute(Integer.parseInt(UCampId.getText()));
        estado.setNombre(UCampNombre.getText());
        UpdateEstadoUseCase updateEstadoUseCase = new UpdateEstadoUseCase(estadoService);
        updateEstadoUseCase.execute(estado);
        ajustarVboxes(600, updateEntityVbox, 0);
        UCampId.setText("");
        UCampNombre.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteEstadoUseCase deleteEstadoUseCase = new DeleteEstadoUseCase(estadoService);
        deleteEstadoUseCase.execute(Integer.parseInt(DCampId.getText()));
        ajustarVboxes(600, deleteEntityVbox, 0);
        DCampId.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdEstadoUseCase findByIdEstadoUseCase = new FindByIdEstadoUseCase(estadoService);
        Estado estado = findByIdEstadoUseCase.execute(Integer.parseInt(FBCampId.getText()));
        if (estado != null) {
            campid.setText(String.valueOf(estado.getId()));
            campname.setText(estado.getNombre());
        } else {
            campid.setText("Sin Datos");
            campname.setText("...😢");
        }
    }

    public void findAllEntities() {
        ObservableList<Estado> listaTabla = FXCollections.observableArrayList();
        FindAllEstadoUseCase findAllEstadoUseCase = new FindAllEstadoUseCase(estadoService);
        List<Estado> estados = findAllEstadoUseCase.execute();
        listaTabla.setAll(estados);
        entitylTableView.setItems(listaTabla);
    }

    @FXML
    public void back() {
        try {
            App.setRoot("estados");
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
