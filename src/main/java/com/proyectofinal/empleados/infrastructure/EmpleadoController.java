package com.proyectofinal.empleados.infrastructure;

import java.util.List;

import com.proyectofinal.App;
import com.proyectofinal.empleados.application.CreateEmpleadoUseCase;
import com.proyectofinal.empleados.application.DeleteEmpleadoUseCase;
import com.proyectofinal.empleados.application.FindAllEmpleadoUseCase;
import com.proyectofinal.empleados.application.FindByIdEmpleadoUseCase;
import com.proyectofinal.empleados.application.UpdateEmpleadoUseCase;
import com.proyectofinal.empleados.domain.entity.Empleado;
import com.proyectofinal.empleados.domain.service.EmpleadoService;

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

public class EmpleadoController {

    private EmpleadoService empleadoService;

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
    private TextField CIDtext;
    @FXML
    private TextField CNameText;
    @FXML
    private TextField CSucursalId;

    // Update

    @FXML
    private HBox updateEntityVbox;
    @FXML
    private TextField UCampId;
    @FXML
    private TextField UCampNewId;
    @FXML
    private TextField UCampNombre;
    @FXML
    private TextField UCampIdSucursal;

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
    private Label campidsucursal;

    // Find All

    @FXML
    private VBox findAllEntityVbox;
    @FXML
    private TableView<Empleado> entitylTableView;
    @FXML
    private TableColumn<Empleado, Integer> colid;
    @FXML
    private TableColumn<Empleado, String> colname;
    @FXML
    private TableColumn<Empleado, Integer> colidsucursal;

    @FXML
    public void initialize() {
        this.empleadoService = new EmpleadoRepository();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colidsucursal.setCellValueFactory(new PropertyValueFactory<>("idsucursal"));
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
            ajustarHboxes(0, updateEntityVbox, 410);
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
        Empleado empleado = new Empleado();
        empleado.setId(CIDtext.getText());
        empleado.setNombre(CNameText.getText());
        empleado.setIdsucursal(Integer.parseInt(CSucursalId.getText()));
        CreateEmpleadoUseCase createEmpleadoUseCase = new CreateEmpleadoUseCase(empleadoService);
        createEmpleadoUseCase.execute(empleado);
        ajustarVboxes(600, createEntityVbox, 0);
        CIDtext.setText("");
        CNameText.setText("");
        CSucursalId.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdEmpleadoUseCase findByIdEmpleadoUseCase = new FindByIdEmpleadoUseCase(empleadoService);
        Empleado empleado = findByIdEmpleadoUseCase.execute(UCampId.getText());

        empleado.setId(UCampNewId.getText());
        empleado.setNombre(UCampNombre.getText());
        empleado.setIdsucursal(Integer.parseInt(UCampIdSucursal.getText()));

        UpdateEmpleadoUseCase updateEmpleadoUseCase = new UpdateEmpleadoUseCase(empleadoService);
        updateEmpleadoUseCase.execute(empleado, UCampId.getText());

        ajustarHboxes(600, updateEntityVbox, 0);
        UCampId.setText("");
        UCampNewId.setText("");
        UCampNombre.setText("");
        UCampIdSucursal.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteEmpleadoUseCase deleteEmpleadoUseCase = new DeleteEmpleadoUseCase(empleadoService);
        deleteEmpleadoUseCase.execute(DCampId.getText());
        ajustarVboxes(600, deleteEntityVbox, 0);
        DCampId.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdEmpleadoUseCase findByIdEmpleadoUseCase = new FindByIdEmpleadoUseCase(empleadoService);
        Empleado empleado = findByIdEmpleadoUseCase.execute(FBCampId.getText());
        if (empleado != null) {
            campid.setText(String.valueOf(empleado.getId()));
            campname.setText(empleado.getNombre());
            campidsucursal.setText(String.valueOf(empleado.getIdsucursal()));
        } else {
            campid.setText("Sin Datos");
            campname.setText("...😢");
            campidsucursal.setText("...😢");
        }
    }

    public void findAllEntities() {
        ObservableList<Empleado> listaTabla = FXCollections.observableArrayList();
        FindAllEmpleadoUseCase findAllEmpleadoUseCase = new FindAllEmpleadoUseCase(empleadoService);
        List<Empleado> empleados = findAllEmpleadoUseCase.execute();
        listaTabla.setAll(empleados);
        entitylTableView.setItems(listaTabla);
    }

    @FXML
    public void back() {
        try {
            App.setRoot("empleados");
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