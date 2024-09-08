package com.proyectofinal.pedidos.infrastructure;

import java.util.List;

import com.proyectofinal.App;
import com.proyectofinal.pedidos.application.CreatePedidoUseCase;
import com.proyectofinal.pedidos.application.DeletePedidoUseCase;
import com.proyectofinal.pedidos.application.FindAllPedidoUseCase;
import com.proyectofinal.pedidos.application.FindByIdPedidoUseCase;
import com.proyectofinal.pedidos.application.UpdatePedidoUseCase;
import com.proyectofinal.pedidos.domain.entity.Pedido;
import com.proyectofinal.pedidos.domain.service.PedidoService;

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

public class PedidoController {
    private PedidoService pedidoService;

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
    private TextField CTotalText;
    @FXML
    private TextField CEstadoIdText;

    // Update

    @FXML
    private VBox updateEntityVbox;
    @FXML
    private TextField UCampId;
    @FXML
    private TextField UCampTotal;
    @FXML
    private TextField UCampIdEstado;

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
    private Label camptotal;
    @FXML
    private Label campidestado;

    // Find All

    @FXML
    private VBox findAllEntityVbox;
    @FXML
    private TableView<Pedido> entitylTableView;
    @FXML
    private TableColumn<Pedido, Integer> colid;
    @FXML
    private TableColumn<Pedido, String> coltotal;
    @FXML
    private TableColumn<Pedido, Integer> colidestado;

    @FXML
    public void initialize() {
        this.pedidoService = new PedidoRepository();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colidestado.setCellValueFactory(new PropertyValueFactory<>("idestado"));
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
        Pedido pedido = new Pedido();
        pedido.setTotal(Double.parseDouble(CTotalText.getText()));
        pedido.setIdestado(Integer.parseInt(CEstadoIdText.getText()));
        CreatePedidoUseCase createPedidoUseCase = new CreatePedidoUseCase(pedidoService);
        createPedidoUseCase.execute(pedido);
        ajustarVboxes(600, createEntityVbox, 0);
        CTotalText.setText("");
        CEstadoIdText.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdPedidoUseCase findByIdPedidoUseCase = new FindByIdPedidoUseCase(pedidoService);
        Pedido pedido = findByIdPedidoUseCase.execute(Integer.parseInt(UCampId.getText()));
        pedido.setTotal(Double.parseDouble(UCampTotal.getText()));
        pedido.setIdestado(Integer.parseInt(UCampIdEstado.getText()));
        UpdatePedidoUseCase updatePedidoUseCase = new UpdatePedidoUseCase(pedidoService);
        updatePedidoUseCase.execute(pedido);
        ajustarVboxes(600, updateEntityVbox, 0);
        UCampId.setText("");
        UCampTotal.setText("");
        UCampIdEstado.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeletePedidoUseCase deletePedidoUseCase = new DeletePedidoUseCase(pedidoService);
        deletePedidoUseCase.execute(Integer.parseInt(DCampId.getText()));
        ajustarVboxes(600, deleteEntityVbox, 0);
        DCampId.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdPedidoUseCase findByIdPedidoUseCase = new FindByIdPedidoUseCase(pedidoService);
        Pedido pedido = findByIdPedidoUseCase.execute(Integer.parseInt(FBCampId.getText()));
        if (pedido != null) {
            campid.setText(String.valueOf(pedido.getId()));
            camptotal.setText(String.valueOf(pedido.getTotal()));
            campidestado.setText(String.valueOf(pedido.getIdestado()));
        } else {
            campid.setText("Sin Datos");
            camptotal.setText("...😢");
            campidestado.setText("...😢");
        }
    }

    public void findAllEntities() {
        ObservableList<Pedido> listaTabla = FXCollections.observableArrayList();
        FindAllPedidoUseCase findAllPedidoUseCase = new FindAllPedidoUseCase(pedidoService);
        List<Pedido> pedidos = findAllPedidoUseCase.execute();
        listaTabla.setAll(pedidos);
        entitylTableView.setItems(listaTabla);
    }

    @FXML
    public void back() {
        try {
            App.setRoot("pedidos");
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