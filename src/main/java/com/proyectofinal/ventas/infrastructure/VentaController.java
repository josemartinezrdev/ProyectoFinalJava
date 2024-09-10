package com.proyectofinal.ventas.infrastructure;

import java.sql.Date;
import java.util.List;
import com.proyectofinal.App;
import com.proyectofinal.ventas.application.CreateVentaUseCase;
import com.proyectofinal.ventas.application.DeleteVentaUseCase;
import com.proyectofinal.ventas.application.FindAllVentaUseCase;
import com.proyectofinal.ventas.application.FindByIdVentaUseCase;
import com.proyectofinal.ventas.application.UpdateVentaUseCase;
import com.proyectofinal.ventas.domain.entity.Venta;
import com.proyectofinal.ventas.domain.service.VentaService;

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

public class VentaController {
    private VentaService ventaService;

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
    private TextField CFechaText;
    @FXML
    private TextField CTotalText;
    @FXML
    private TextField CEmpleadoId;
    @FXML
    private TextField CClienteId;

    // Update

    @FXML
    private VBox updateEntityVbox;
    @FXML
    private TextField UCampId;
    @FXML
    private TextField UFechaText;
    @FXML
    private TextField UTotalText;
    @FXML
    private TextField UEmpleadoId;
    @FXML
    private TextField UClienteId;

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
    private Label campfecha;
    @FXML
    private Label camptotal;
    @FXML
    private Label campidempleado;
    @FXML
    private Label campidcliente;

    // Find All

    @FXML
    private VBox findAllEntityVbox;
    @FXML
    private TableView<Venta> entitylTableView;
    @FXML
    private TableColumn<Venta, Integer> colid;
    @FXML
    private TableColumn<Venta, Date> colfecha;
    @FXML
    private TableColumn<Venta, Double> coltotal;
    @FXML
    private TableColumn<Venta, Integer> colidempleado;
    @FXML
    private TableColumn<Venta, Integer> colidcliente;

    @FXML
    public void initialize() {
        this.ventaService = new VentaRepository();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colfecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        coltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colidempleado.setCellValueFactory(new PropertyValueFactory<>("idempleado"));
        colidcliente.setCellValueFactory(new PropertyValueFactory<>("idcliente"));
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
        Venta venta = new Venta();
        venta.setFecha(Date.valueOf(CFechaText.getText()));
        venta.setTotal(Double.parseDouble(CTotalText.getText()));
        venta.setIdempleado(CEmpleadoId.getText());
        venta.setIdcliente(CClienteId.getText());

        CreateVentaUseCase createVentaUseCase = new CreateVentaUseCase(ventaService);
        createVentaUseCase.execute(venta);

        ajustarVboxes(600, createEntityVbox, 0);
        CFechaText.setText("");
        CTotalText.setText("");
        CEmpleadoId.setText("");
        CClienteId.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdVentaUseCase findByIdVentaUseCase = new FindByIdVentaUseCase(ventaService);
        Venta venta = findByIdVentaUseCase.execute(Integer.parseInt(UCampId.getText()));

        venta.setFecha(Date.valueOf(UFechaText.getText()));
        venta.setTotal(Double.parseDouble(UTotalText.getText()));
        venta.setIdempleado(UEmpleadoId.getText());
        venta.setIdcliente(UClienteId.getText());

        UpdateVentaUseCase updateVentaUseCase = new UpdateVentaUseCase(ventaService);
        updateVentaUseCase.execute(venta);

        ajustarVboxes(600, updateEntityVbox, 0);
        UCampId.setText("");
        UFechaText.setText("");
        UTotalText.setText("");
        UEmpleadoId.setText("");
        UClienteId.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteVentaUseCase deleteVentaUseCase = new DeleteVentaUseCase(ventaService);
        deleteVentaUseCase.execute(Integer.parseInt(DCampId.getText()));

        ajustarVboxes(600, deleteEntityVbox, 0);
        DCampId.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdVentaUseCase findByIdVentaUseCase = new FindByIdVentaUseCase(ventaService);
        Venta venta = findByIdVentaUseCase.execute(Integer.parseInt(FBCampId.getText()));
        if (venta != null) {
            campid.setText(String.valueOf(venta.getId()));
            campfecha.setText(String.valueOf(venta.getFecha()));
            camptotal.setText(String.valueOf(venta.getTotal()));
            campidempleado.setText(String.valueOf(venta.getIdempleado()));
            campidcliente.setText(String.valueOf(venta.getIdcliente()));
        } else {
            campid.setText("Sin Datos");
            campfecha.setText("...😢");
            camptotal.setText("...😢");
            campidempleado.setText("...😢");
            campidcliente.setText("...😢");
        }
    }

    public void findAllEntities() {
        ObservableList<Venta> listaTabla = FXCollections.observableArrayList();
        FindAllVentaUseCase findAllVentaUseCase = new FindAllVentaUseCase(ventaService);
        List<Venta> ventas = findAllVentaUseCase.execute();
        listaTabla.setAll(ventas);
        entitylTableView.setItems(listaTabla);
    }

    @FXML
    public void back() {
        try {
            App.setRoot("ventas");
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
