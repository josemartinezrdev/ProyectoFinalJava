package com.proyectofinal.detalles_pedidos.infrastructure;

import java.util.List;

import com.proyectofinal.App;
import com.proyectofinal.detalles_pedidos.application.CreateDetPedUseCase;
import com.proyectofinal.detalles_pedidos.application.DeleteDetPedUseCase;
import com.proyectofinal.detalles_pedidos.application.FindAllDetPedUseCase;
import com.proyectofinal.detalles_pedidos.application.FindByIdDetPedUseCase;
import com.proyectofinal.detalles_pedidos.application.UpdateDetPedUseCase;
import com.proyectofinal.detalles_pedidos.domain.entity.DetPed;
import com.proyectofinal.detalles_pedidos.domain.service.DetPedService;

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

public class DetPedController {

    private DetPedService detpedService;

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
    private TextField CProductoIdtext;
    @FXML
    private TextField CPedidoIdText;
    @FXML
    private TextField CProveedorIdText;

    // Update

    @FXML
    private VBox updateEntityVbox;
    @FXML
    private TextField UCampId;
    @FXML
    private TextField UCampIdProducto;
    @FXML
    private TextField UCampIdPedido;
    @FXML
    private TextField UCampIdProveedor;

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
    private Label campidproducto;
    @FXML
    private Label campidpedido;
    @FXML
    private Label campidproveedor;

    // Find All

    @FXML
    private VBox findAllEntityVbox;
    @FXML
    private TableView<DetPed> entitylTableView;
    @FXML
    private TableColumn<DetPed, Integer> colid;
    @FXML
    private TableColumn<DetPed, String> colidproducto;
    @FXML
    private TableColumn<DetPed, Integer> colidpedido;
    @FXML
    private TableColumn<DetPed, Integer> colidproveedor;

    @FXML
    public void initialize() {
        this.detpedService = new DetPedRepository();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colidproducto.setCellValueFactory(new PropertyValueFactory<>("idproducto"));
        colidpedido.setCellValueFactory(new PropertyValueFactory<>("idpedido"));
        colidproveedor.setCellValueFactory(new PropertyValueFactory<>("idproveedor"));
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
        DetPed detped = new DetPed();
        detped.setIdproducto(Integer.parseInt(CProductoIdtext.getText()));
        detped.setIdpedido(Integer.parseInt(CPedidoIdText.getText()));
        detped.setIdproveedor(Integer.parseInt(CProveedorIdText.getText()));

        CreateDetPedUseCase createDetPedUseCase = new CreateDetPedUseCase(detpedService);
        createDetPedUseCase.execute(detped);

        ajustarVboxes(600, createEntityVbox, 0);
        CProductoIdtext.setText("");
        CPedidoIdText.setText("");
        CProveedorIdText.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdDetPedUseCase findByIdDetPedUseCase = new FindByIdDetPedUseCase(detpedService);
        DetPed detped = findByIdDetPedUseCase.execute(Integer.parseInt(UCampId.getText()));

        detped.setIdproducto(Integer.parseInt(UCampIdProducto.getText()));
        detped.setIdpedido(Integer.parseInt(UCampIdPedido.getText()));
        detped.setIdproveedor(Integer.parseInt(UCampIdProveedor.getText()));

        UpdateDetPedUseCase updateDetPedUseCase = new UpdateDetPedUseCase(detpedService);
        updateDetPedUseCase.execute(detped);

        ajustarVboxes(600, updateEntityVbox, 0);
        UCampId.setText("");
        UCampIdProducto.setText("");
        UCampIdPedido.setText("");
        UCampIdProveedor.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteDetPedUseCase deleteDetPedUseCase = new DeleteDetPedUseCase(detpedService);
        deleteDetPedUseCase.execute(Integer.parseInt(DCampId.getText()));

        ajustarVboxes(600, deleteEntityVbox, 0);
        DCampId.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdDetPedUseCase findByIdDetPedUseCase = new FindByIdDetPedUseCase(detpedService);
        DetPed detped = findByIdDetPedUseCase.execute(Integer.parseInt(FBCampId.getText()));
        if (detped != null) {
            campid.setText(String.valueOf(detped.getId()));
            campidproducto.setText(String.valueOf(detped.getIdproducto()));
            campidpedido.setText(String.valueOf(detped.getIdpedido()));
            campidproveedor.setText(String.valueOf(detped.getIdproveedor()));
        } else {
            campid.setText("Sin Datos");
            campidproducto.setText("...😢");
            campidpedido.setText("...😢");
            campidproveedor.setText("...😢");
        }
    }

    public void findAllEntities() {
        ObservableList<DetPed> listaTabla = FXCollections.observableArrayList();
        FindAllDetPedUseCase findAllDetPedUseCase = new FindAllDetPedUseCase(detpedService);
        List<DetPed> detalles_pedidos = findAllDetPedUseCase.execute();
        listaTabla.setAll(detalles_pedidos);
        entitylTableView.setItems(listaTabla);
    }

    @FXML
    public void back() {
        try {
            App.setRoot("detalles_pedidos");
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
