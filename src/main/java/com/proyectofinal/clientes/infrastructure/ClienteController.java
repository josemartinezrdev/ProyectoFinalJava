package com.proyectofinal.clientes.infrastructure;

import java.io.IOException;
import java.util.List;

import com.proyectofinal.App;
import com.proyectofinal.clientes.application.CreateClienteUseCase;
import com.proyectofinal.clientes.application.DeleteClienteUseCase;
import com.proyectofinal.clientes.application.FindAllClienteUseCase;
import com.proyectofinal.clientes.application.FindByIdClienteUseCase;
import com.proyectofinal.clientes.application.UpdateClienteUseCase;
import com.proyectofinal.clientes.domain.entity.Cliente;
import com.proyectofinal.clientes.domain.service.ClienteService;

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

public class ClienteController {
    private ClienteService clienteService;

    // INICIO

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

    // CREATE

    @FXML
    private VBox createEntityVbox;
    @FXML
    private TextField CClienteId;
    @FXML
    private TextField UNEWClientId;
    @FXML
    private TextField CNombreText;
    @FXML
    private TextField CApellidoText;
    @FXML
    private TextField CTipoClienteId;
    @FXML
    private TextField CDireccionId;

    // UPDATE

    @FXML
    private VBox updateEntityVbox;
    @FXML
    private TextField UClienteId;
    @FXML
    private TextField UNombreText;
    @FXML
    private TextField UApellidoText;
    @FXML
    private TextField UTipoClienteId;
    @FXML
    private TextField UDireccionId;

    // DELETE

    @FXML
    private VBox deleteEntityVbox;

    @FXML
    private TextField DCampId;

    // FIND BY ID

    @FXML
    private HBox findByIdEntityHbox;
    @FXML
    private TextField FBCampId;
    @FXML
    private Label campid;
    @FXML
    private Label campnombre;
    @FXML
    private Label campapellido;
    @FXML
    private Label campidtipocliente;
    @FXML
    private Label campiddireccion;

    // FIND ALL

    @FXML
    private VBox findAllEntityVbox;
    @FXML
    private TableView<Cliente> entitylTableView;
    @FXML
    TableColumn<Cliente, String> colid;
    @FXML
    TableColumn<Cliente, String> colnombre;
    @FXML
    TableColumn<Cliente, String> colapellido;
    @FXML
    TableColumn<Cliente, Integer> colidtipocliente;
    @FXML
    TableColumn<Cliente, Integer> coliddireccion;

    // CLASS FUN

    public void initialize() {
        this.clienteService = new ClienteRepository();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colapellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colidtipocliente.setCellValueFactory(new PropertyValueFactory<>("idtipocliente"));
        coliddireccion.setCellValueFactory(new PropertyValueFactory<>("iddireccion"));
    }

    // HOME FUN

    public void eventAction(javafx.event.ActionEvent event) {
        Object source = event.getSource();
        if (source == btnCreate) {
            ajustarVboxes(0, createEntityVbox, 410);
        } else if (source == btnUpdate) {
            ajustarVboxes(0, updateEntityVbox, 410);
        } else if (source == btnDelete) {
            ajustarVboxes(0, deleteEntityVbox, 410);
        } else if (source == btnFindById) {
            ajustarHboxes(0, findByIdEntityHbox, 410);
        } else if (source == btnFindAll) {
            ajustarVboxes(0, findAllEntityVbox, 410);
            entitylTableView.setMaxWidth(500);
            findAlldEntities();
        } else if (source == btnQuit) {
            exit();
        }
    }

    @FXML
    public void exit() {
        try {
            App.setRoot("crud");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CRUD FUN's

    @FXML
    public void createEntity() {
        Cliente cliente = new Cliente(
                CClienteId.getText(),
                CNombreText.getText(),
                CApellidoText.getText(),
                Integer.parseInt(CTipoClienteId.getText()),
                Integer.parseInt(CDireccionId.getText()));
        CreateClienteUseCase createClienteUseCase = new CreateClienteUseCase(clienteService);
        createClienteUseCase.execute(cliente);

        ajustarVboxes(600, createEntityVbox, 0);
        CClienteId.setText("");
        CNombreText.setText("");
        CApellidoText.setText("");
        CTipoClienteId.setText("");
        CDireccionId.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdClienteUseCase findByIdClienteUseCase = new FindByIdClienteUseCase(clienteService);
        Cliente cliente = findByIdClienteUseCase.execute(UClienteId.getText());

        cliente.setId(UNEWClientId.getText());
        cliente.setNombre(UNombreText.getText());
        cliente.setApellido(UApellidoText.getText());
        cliente.setIdtipocliente(Integer.parseInt(UTipoClienteId.getText()));
        cliente.setIddireccion(Integer.parseInt(UDireccionId.getText()));

        UpdateClienteUseCase updateClienteUseCase = new UpdateClienteUseCase(clienteService);
        updateClienteUseCase.execute(cliente, UClienteId.getText());

        ajustarVboxes(600, updateEntityVbox, 0);
        UClienteId.setText("");
        UNEWClientId.setText("");
        UNombreText.setText("");
        UApellidoText.setText("");
        UTipoClienteId.setText("");
        UDireccionId.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteClienteUseCase deleteClienteUseCase = new DeleteClienteUseCase(clienteService);
        deleteClienteUseCase.execute(DCampId.getText());

        ajustarVboxes(600, deleteEntityVbox, 0);
        DCampId.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdClienteUseCase findByIdClienteUseCase = new FindByIdClienteUseCase(clienteService);
        Cliente cliente = findByIdClienteUseCase.execute(FBCampId.getText());
        if (cliente != null) {
            campid.setText(cliente.getId());
            campnombre.setText(cliente.getNombre());
            campapellido.setText(cliente.getApellido());
            campidtipocliente.setText(String.valueOf(cliente.getIdtipocliente()));
            campiddireccion.setText(String.valueOf(cliente.getIddireccion()));
        } else {
            campid.setText("Sin datos...");
            campnombre.setText("...😢");
            campapellido.setText("...😢");
            campidtipocliente.setText("...😢");
            campiddireccion.setText("...😢");
        }
    }

    @FXML
    public void findAlldEntities() {
        ObservableList<Cliente> listaTabla = FXCollections.observableArrayList();
        FindAllClienteUseCase findAllClienteUseCase = new FindAllClienteUseCase(clienteService);
        List<Cliente> clientes = findAllClienteUseCase.execute();
        listaTabla.setAll(clientes);
        entitylTableView.setItems(listaTabla);
    }

    // GENERAL FUN

    @FXML
    public void back() throws IOException {
        App.setRoot("clientes");
    }

    @FXML
    public void ajustarVboxes(double formAlto, VBox vbox, double vboxAlto) {
        formContainer.setPrefHeight(formAlto);
        vbox.setPrefHeight(vboxAlto);
    }

    @FXML
    public void ajustarHboxes(double formAlto, HBox hbox, double hboxAlto) {
        formContainer.setPrefHeight(formAlto);
        hbox.setPrefHeight(hboxAlto);
    }

}
