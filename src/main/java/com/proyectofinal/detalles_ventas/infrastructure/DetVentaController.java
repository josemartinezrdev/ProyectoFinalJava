package com.proyectofinal.detalles_ventas.infrastructure;

import java.util.List;

import com.proyectofinal.App;
import com.proyectofinal.detalles_ventas.application.CreateDetVentaUseCase;
import com.proyectofinal.detalles_ventas.application.DeleteDetVentaUseCase;
import com.proyectofinal.detalles_ventas.application.FindAllDetVentaUseCase;
import com.proyectofinal.detalles_ventas.application.FindByIdDetVentaUseCase;
import com.proyectofinal.detalles_ventas.application.UpdateDetVentaUseCase;
import com.proyectofinal.detalles_ventas.domain.entity.DetVenta;
import com.proyectofinal.detalles_ventas.domain.service.DetVentaService;

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

public class DetVentaController {

    private DetVentaService detventaService;

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
    private TextField CVentaId;
    @FXML
    private TextField CProductoId;
    @FXML
    private TextField CTotal;
    @FXML
    private TextField CCantidad;

    // Update

    @FXML
    private VBox updateEntityVbox;
    @FXML
    private TextField UCampId;
    @FXML
    private TextField UVenta;
    @FXML
    private TextField UProductoId;
    @FXML
    private TextField UTotal;
    @FXML
    private TextField UCantidad;

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
    private Label campidventa;
    @FXML
    private Label camptotal;
    @FXML
    private Label campidproducto;
    @FXML
    private Label campcantidad;

    // Find All

    @FXML
    private VBox findAllEntityVbox;
    @FXML
    private TableView<DetVenta> entitylTableView;
    @FXML
    private TableColumn<DetVenta, Integer> colid;
    @FXML
    private TableColumn<DetVenta, String> colidventa;
    @FXML
    private TableColumn<DetVenta, Integer> coltotal;
    @FXML
    private TableColumn<DetVenta, Integer> colidproducto;
    @FXML
    private TableColumn<DetVenta, Integer> colcantidad;

    @FXML
    public void initialize() {
        this.detventaService = new DetVentaRepository();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colidventa.setCellValueFactory(new PropertyValueFactory<>("idventa"));
        coltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colidproducto.setCellValueFactory(new PropertyValueFactory<>("idproducto"));
        colcantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
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
        DetVenta detventa = new DetVenta();
        detventa.setIdventa(Integer.parseInt(CVentaId.getText()));
        detventa.setIdproducto(Integer.parseInt(CProductoId.getText()));
        detventa.setTotal(Integer.parseInt(CTotal.getText()));
        detventa.setCantidad(Integer.parseInt(CCantidad.getText()));

        CreateDetVentaUseCase createDetVentaUseCase = new CreateDetVentaUseCase(detventaService);
        createDetVentaUseCase.execute(detventa);

        ajustarVboxes(600, createEntityVbox, 0);
        CVentaId.setText("");
        CProductoId.setText("");
        CTotal.setText("");
        CCantidad.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdDetVentaUseCase findByIdDetVentaUseCase = new FindByIdDetVentaUseCase(detventaService);
        DetVenta detventa = findByIdDetVentaUseCase.execute(Integer.parseInt(UCampId.getText()));

        detventa.setIdventa(Integer.parseInt(UVenta.getText()));
        detventa.setIdproducto(Integer.parseInt(UProductoId.getText()));
        detventa.setTotal(Integer.parseInt(UTotal.getText()));
        detventa.setCantidad(Integer.parseInt(UCantidad.getText()));

        UpdateDetVentaUseCase updateDetVentaUseCase = new UpdateDetVentaUseCase(detventaService);
        updateDetVentaUseCase.execute(detventa);

        ajustarVboxes(600, updateEntityVbox, 0);
        UCampId.setText("");
        UVenta.setText("");
        UProductoId.setText("");
        UTotal.setText("");
        UCantidad.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteDetVentaUseCase deleteDetVentaUseCase = new DeleteDetVentaUseCase(detventaService);
        deleteDetVentaUseCase.execute(Integer.parseInt(DCampId.getText()));

        ajustarVboxes(600, deleteEntityVbox, 0);
        DCampId.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdDetVentaUseCase findByIdDetVentaUseCase = new FindByIdDetVentaUseCase(detventaService);
        DetVenta detventa = findByIdDetVentaUseCase.execute(Integer.parseInt(FBCampId.getText()));
        if (detventa != null) {
            campid.setText(String.valueOf(detventa.getId()));
            campidventa.setText(String.valueOf(detventa.getIdventa()));
            camptotal.setText(String.valueOf(detventa.getTotal()));
            campidproducto.setText(String.valueOf(detventa.getIdproducto()));
            campcantidad.setText(String.valueOf(detventa.getCantidad()));
        } else {
            campid.setText("Sin Datos");
            campidventa.setText("...😢");
            camptotal.setText("...😢");
            campidproducto.setText("...😢");
            campcantidad.setText("...😢");
        }
    }

    public void findAllEntities() {
        ObservableList<DetVenta> listaTabla = FXCollections.observableArrayList();
        FindAllDetVentaUseCase findAllDetVentaUseCase = new FindAllDetVentaUseCase(detventaService);
        List<DetVenta> detalles_ventas = findAllDetVentaUseCase.execute();
        listaTabla.setAll(detalles_ventas);
        entitylTableView.setItems(listaTabla);
    }

    @FXML
    public void back() {
        try {
            App.setRoot("detalles_ventas");
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
