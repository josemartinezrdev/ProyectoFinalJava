package com.proyectofinal.telefonos_empleados.infrastructure;

import java.util.List;

import com.proyectofinal.App;
import com.proyectofinal.telefonos_empleados.application.CreateTelEmpleadoUseCase;
import com.proyectofinal.telefonos_empleados.application.DeleteTelEmpleadoUseCase;
import com.proyectofinal.telefonos_empleados.application.FindAllTelEmpleadoUseCase;
import com.proyectofinal.telefonos_empleados.application.FindByIdTelEmpleadoUseCase;
import com.proyectofinal.telefonos_empleados.application.UpdateTelEmpleadoUseCase;
import com.proyectofinal.telefonos_empleados.domain.entity.TelEmpleado;
import com.proyectofinal.telefonos_empleados.domain.service.TelEmpleadoService;

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

public class TelEmpleadoController {
    private TelEmpleadoService telempleadoService;

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
    private TextField CTelefonoText;
    @FXML
    private TextField CEmpleadoIdText;

    // Update

    @FXML
    private VBox updateEntityVbox;
    @FXML
    private TextField UCampId;
    @FXML
    private TextField UCampTelefono;
    @FXML
    private TextField UCampIdEmpleado;

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
    private Label camptelefono;
    @FXML
    private Label campidempleado;

    // Find All

    @FXML
    private VBox findAllEntityVbox;
    @FXML
    private TableView<TelEmpleado> entitylTableView;
    @FXML
    private TableColumn<TelEmpleado, Integer> colid;
    @FXML
    private TableColumn<TelEmpleado, String> coltelefono;
    @FXML
    private TableColumn<TelEmpleado, Integer> colidempleado;

    @FXML
    public void initialize() {
        this.telempleadoService = new TelEmpleadoRepository();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coltelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colidempleado.setCellValueFactory(new PropertyValueFactory<>("idempleado"));
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
        TelEmpleado telempleado = new TelEmpleado();
        telempleado.setTelefono(CTelefonoText.getText());
        telempleado.setIdempleado(CEmpleadoIdText.getText());
        CreateTelEmpleadoUseCase createTelEmpleadoUseCase = new CreateTelEmpleadoUseCase(telempleadoService);
        createTelEmpleadoUseCase.execute(telempleado);
        ajustarVboxes(600, createEntityVbox, 0);
        CTelefonoText.setText("");
        CEmpleadoIdText.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdTelEmpleadoUseCase findByIdTelEmpleadoUseCase = new FindByIdTelEmpleadoUseCase(telempleadoService);
        TelEmpleado telempleado = findByIdTelEmpleadoUseCase.execute(Integer.parseInt(UCampId.getText()));
        telempleado.setTelefono(UCampTelefono.getText());
        telempleado.setIdempleado(UCampIdEmpleado.getText());
        UpdateTelEmpleadoUseCase updateTelEmpleadoUseCase = new UpdateTelEmpleadoUseCase(telempleadoService);
        updateTelEmpleadoUseCase.execute(telempleado);
        ajustarVboxes(600, updateEntityVbox, 0);
        UCampId.setText("");
        UCampTelefono.setText("");
        UCampIdEmpleado.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteTelEmpleadoUseCase deleteTelEmpleadoUseCase = new DeleteTelEmpleadoUseCase(telempleadoService);
        deleteTelEmpleadoUseCase.execute(Integer.parseInt(DCampId.getText()));
        ajustarVboxes(600, deleteEntityVbox, 0);
        DCampId.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdTelEmpleadoUseCase findByIdTelEmpleadoUseCase = new FindByIdTelEmpleadoUseCase(telempleadoService);
        TelEmpleado telempleado = findByIdTelEmpleadoUseCase.execute(Integer.parseInt(FBCampId.getText()));
        if (telempleado != null) {
            campid.setText(String.valueOf(telempleado.getId()));
            camptelefono.setText(telempleado.getTelefono());
            campidempleado.setText(telempleado.getIdempleado());
        } else {
            campid.setText("Sin Datos");
            camptelefono.setText("...😢");
            campidempleado.setText("...😢");
        }
    }

    public void findAllEntities() {
        ObservableList<TelEmpleado> listaTabla = FXCollections.observableArrayList();
        FindAllTelEmpleadoUseCase findAllTelEmpleadoUseCase = new FindAllTelEmpleadoUseCase(telempleadoService);
        List<TelEmpleado> telefonos_empleados = findAllTelEmpleadoUseCase.execute();
        listaTabla.setAll(telefonos_empleados);
        entitylTableView.setItems(listaTabla);
    }

    @FXML
    public void back() {
        try {
            App.setRoot("telefonos_empleados");
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