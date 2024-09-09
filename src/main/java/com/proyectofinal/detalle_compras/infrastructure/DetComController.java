package com.proyectofinal.detalle_compras.infrastructure;

import java.io.IOException;
import java.util.List;

import com.proyectofinal.App;
import com.proyectofinal.detalle_compras.application.CreateDetComUseCase;
import com.proyectofinal.detalle_compras.application.DeleteDetComUseCase;
import com.proyectofinal.detalle_compras.application.FindAllDetComUseCase;
import com.proyectofinal.detalle_compras.application.FindByIdDetComUseCase;
import com.proyectofinal.detalle_compras.application.UpdateDetComUseCase;
import com.proyectofinal.detalle_compras.domain.entity.DetCom;
import com.proyectofinal.detalle_compras.domain.service.DetComService;

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

public class DetComController {

    private DetComService detcomService;
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
    private TextField CCompraIdText;

    @FXML
    private TextField CPedidoIdText;

    // Update

    @FXML
    private VBox updateEntityVbox;

    @FXML
    private TextField UCampIdCompra;

    @FXML
    private TextField UCampIdPedido;

    @FXML
    private TextField UNEWCampIdCompra;

    @FXML
    private TextField UNEWCampIdPedido;

    // Delete

    @FXML
    private VBox deleteEntityVbox;

    @FXML
    private TextField DCampIdCompra;

    @FXML
    private TextField DCampIdPedido;

    // Find By Id
    @FXML
    private HBox findByIdEntityVbox;

    @FXML
    private TextField FBCompraId;

    @FXML
    private TextField FBPedidoId;

    @FXML
    private Label campidcompra;

    @FXML
    private Label campidpedido;

    // Find ALL

    @FXML
    private VBox findAllEntityVbox;

    @FXML
    private TableView<DetCom> entitylTableView;

    @FXML
    private TableColumn<DetCom, Integer> colidcompra;

    @FXML
    private TableColumn<DetCom, Integer> colidpedido;

    @FXML
    public void initialize() {
        this.detcomService = new DetComRepository();
        colidcompra.setCellValueFactory(new PropertyValueFactory<>("idcompra"));
        colidpedido.setCellValueFactory(new PropertyValueFactory<>("idpedido"));
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
            findAllDetComs();
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
        DetCom detCom = new DetCom();
        detCom.setIdcompra(Integer.parseInt(CCompraIdText.getText()));
        detCom.setIdpedido(Integer.parseInt(CPedidoIdText.getText()));
        CreateDetComUseCase createDetComUseCase = new CreateDetComUseCase(detcomService);
        createDetComUseCase.execute(detCom);
        ajustarBoxes(600, createEntityVbox, 0);
        CCompraIdText.setText("");
        CPedidoIdText.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdDetComUseCase findDetComUseCase = new FindByIdDetComUseCase(detcomService);
        DetCom detCom = findDetComUseCase.execute(
                Integer.parseInt(UCampIdCompra.getText()),
                Integer.parseInt(UCampIdPedido.getText()));

        detCom.setIdcompra(Integer.parseInt(UNEWCampIdCompra.getText()));
        detCom.setIdpedido(Integer.parseInt(UNEWCampIdPedido.getText()));

        UpdateDetComUseCase updateDetComUseCase = new UpdateDetComUseCase(detcomService);
        updateDetComUseCase.execute(
                detCom,
                Integer.parseInt(UCampIdCompra.getText()),
                Integer.parseInt(UCampIdPedido.getText()));

        ajustarBoxes(600, updateEntityVbox, 0);
        UCampIdCompra.setText("");
        UCampIdPedido.setText("");
        UNEWCampIdCompra.setText("");
        UNEWCampIdPedido.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteDetComUseCase deleteDetComUseCase = new DeleteDetComUseCase(detcomService);
        deleteDetComUseCase.execute(Integer.parseInt(DCampIdCompra.getText()),
                Integer.parseInt(DCampIdPedido.getText()));
        ajustarBoxes(600, deleteEntityVbox, 0);
        DCampIdCompra.setText("");
        DCampIdPedido.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdDetComUseCase findDetComUseCase = new FindByIdDetComUseCase(detcomService);
        DetCom detCom = findDetComUseCase.execute(Integer.parseInt(FBCompraId.getText()),
                Integer.parseInt(FBPedidoId.getText()));
        if (detCom != null) {
            campidcompra.setText(String.valueOf(detCom.getIdcompra()));
            campidpedido.setText(String.valueOf(detCom.getIdpedido()));
        } else {
            campidcompra.setText("Sin datos");
            campidpedido.setText("...😢");
        }
    }

    @FXML
    public void findAllDetComs() {
        FindAllDetComUseCase findAllDetComUseCase = new FindAllDetComUseCase(detcomService);
        List<DetCom> detComs = findAllDetComUseCase.execute();
        ObservableList<DetCom> detComList = FXCollections.observableArrayList(detComs);
        entitylTableView.setItems(detComList);
    }

    @FXML
    public void back() {
        FBCompraId.setText("");
        FBPedidoId.setText("");
        try {
            App.setRoot("detalle_compras");
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
