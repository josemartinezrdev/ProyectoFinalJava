package com.proyectofinal.tipos_clientes.infrastructure;

import java.util.List;

import com.proyectofinal.App;
import com.proyectofinal.tipos_clientes.application.CreateTipClienteUseCase;
import com.proyectofinal.tipos_clientes.application.DeleteTipClienteUseCase;
import com.proyectofinal.tipos_clientes.application.FindAllTipClienteUseCase;
import com.proyectofinal.tipos_clientes.application.FindByIdTipClienteUseCase;
import com.proyectofinal.tipos_clientes.application.UpdateTipClienteUseCase;
import com.proyectofinal.tipos_clientes.domain.entity.TipCliente;
import com.proyectofinal.tipos_clientes.domain.service.TipClienteService;

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

public class TipClienteController {

    private TipClienteService tipclienteService;

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
    private TableView<TipCliente> entitylTableView;
    @FXML
    private TableColumn<TipCliente, Integer> colid;
    @FXML
    private TableColumn<TipCliente, String> colname;

    @FXML
    public void initialize() {
        this.tipclienteService = new TipClienteRepository();
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
        TipCliente tipcliente = new TipCliente();
        tipcliente.setNombre(CNameText.getText());
        CreateTipClienteUseCase createTipClienteUseCase = new CreateTipClienteUseCase(tipclienteService);
        createTipClienteUseCase.execute(tipcliente);
        ajustarVboxes(600, createEntityVbox, 0);
        CNameText.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdTipClienteUseCase findByIdTipClienteUseCase = new FindByIdTipClienteUseCase(tipclienteService);
        TipCliente tipcliente = findByIdTipClienteUseCase.execute(Integer.parseInt(UCampId.getText()));
        tipcliente.setNombre(UCampNombre.getText());
        UpdateTipClienteUseCase updateTipClienteUseCase = new UpdateTipClienteUseCase(tipclienteService);
        updateTipClienteUseCase.execute(tipcliente);
        ajustarVboxes(600, updateEntityVbox, 0);
        UCampId.setText("");
        UCampNombre.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteTipClienteUseCase deleteTipClienteUseCase = new DeleteTipClienteUseCase(tipclienteService);
        deleteTipClienteUseCase.execute(Integer.parseInt(DCampId.getText()));
        ajustarVboxes(600, deleteEntityVbox, 0);
        DCampId.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdTipClienteUseCase findByIdTipClienteUseCase = new FindByIdTipClienteUseCase(tipclienteService);
        TipCliente tipcliente = findByIdTipClienteUseCase.execute(Integer.parseInt(FBCampId.getText()));
        if (tipcliente != null) {
            campid.setText(String.valueOf(tipcliente.getId()));
            campname.setText(tipcliente.getNombre());
        } else {
            campid.setText("Sin Datos");
            campname.setText("...😢");
        }
    }

    public void findAllEntities() {
        ObservableList<TipCliente> listaTabla = FXCollections.observableArrayList();
        FindAllTipClienteUseCase findAllTipClienteUseCase = new FindAllTipClienteUseCase(tipclienteService);
        List<TipCliente> tipos_clientes = findAllTipClienteUseCase.execute();
        listaTabla.setAll(tipos_clientes);
        entitylTableView.setItems(listaTabla);
    }

    @FXML
    public void back() {
        try {
            App.setRoot("tipos_clientes");
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
