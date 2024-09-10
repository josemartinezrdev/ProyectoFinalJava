package com.proyectofinal.telefonos_clientes.infrastructure;

import java.util.List;

import com.proyectofinal.App;
import com.proyectofinal.telefonos_clientes.application.CreateTelClienteUseCase;
import com.proyectofinal.telefonos_clientes.application.DeleteTelClienteUseCase;
import com.proyectofinal.telefonos_clientes.application.FindAllTelClienteUseCase;
import com.proyectofinal.telefonos_clientes.application.FindByIdTelClienteUseCase;
import com.proyectofinal.telefonos_clientes.application.UpdateTelClienteUseCase;
import com.proyectofinal.telefonos_clientes.domain.entity.TelCliente;
import com.proyectofinal.telefonos_clientes.domain.service.TelClienteService;

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

public class TelClienteController {
    private TelClienteService telclienteService;

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
    private TextField CClienteIdText;

    // Update

    @FXML
    private VBox updateEntityVbox;
    @FXML
    private TextField UCampId;
    @FXML
    private TextField UCampTelefono;
    @FXML
    private TextField UCampIdCliente;

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
    private Label campidcliente;

    // Find All

    @FXML
    private VBox findAllEntityVbox;
    @FXML
    private TableView<TelCliente> entitylTableView;
    @FXML
    private TableColumn<TelCliente, Integer> colid;
    @FXML
    private TableColumn<TelCliente, String> coltelefono;
    @FXML
    private TableColumn<TelCliente, Integer> colidcliente;

    @FXML
    public void initialize() {
        this.telclienteService = new TelClienteRepository();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coltelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
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
        TelCliente telcliente = new TelCliente();
        telcliente.setTelefono(CTelefonoText.getText());
        telcliente.setIdcliente(CClienteIdText.getText());
        CreateTelClienteUseCase createTelClienteUseCase = new CreateTelClienteUseCase(telclienteService);
        createTelClienteUseCase.execute(telcliente);
        ajustarVboxes(600, createEntityVbox, 0);
        CTelefonoText.setText("");
        CClienteIdText.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdTelClienteUseCase findByIdTelClienteUseCase = new FindByIdTelClienteUseCase(telclienteService);
        TelCliente telcliente = findByIdTelClienteUseCase.execute(Integer.parseInt(UCampId.getText()));
        telcliente.setTelefono(UCampTelefono.getText());
        telcliente.setIdcliente(UCampIdCliente.getText());
        UpdateTelClienteUseCase updateTelClienteUseCase = new UpdateTelClienteUseCase(telclienteService);
        updateTelClienteUseCase.execute(telcliente);
        ajustarVboxes(600, updateEntityVbox, 0);
        UCampId.setText("");
        UCampTelefono.setText("");
        UCampIdCliente.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteTelClienteUseCase deleteTelClienteUseCase = new DeleteTelClienteUseCase(telclienteService);
        deleteTelClienteUseCase.execute(Integer.parseInt(DCampId.getText()));
        ajustarVboxes(600, deleteEntityVbox, 0);
        DCampId.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdTelClienteUseCase findByIdTelClienteUseCase = new FindByIdTelClienteUseCase(telclienteService);
        TelCliente telcliente = findByIdTelClienteUseCase.execute(Integer.parseInt(FBCampId.getText()));
        if (telcliente != null) {
            campid.setText(String.valueOf(telcliente.getId()));
            camptelefono.setText(telcliente.getTelefono());
            campidcliente.setText(telcliente.getIdcliente());
        } else {
            campid.setText("Sin Datos");
            camptelefono.setText("...😢");
            campidcliente.setText("...😢");
        }
    }

    public void findAllEntities() {
        ObservableList<TelCliente> listaTabla = FXCollections.observableArrayList();
        FindAllTelClienteUseCase findAllTelClienteUseCase = new FindAllTelClienteUseCase(telclienteService);
        List<TelCliente> telefonos_clientes = findAllTelClienteUseCase.execute();
        listaTabla.setAll(telefonos_clientes);
        entitylTableView.setItems(listaTabla);
    }

    @FXML
    public void back() {
        try {
            App.setRoot("telefonos_clientes");
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