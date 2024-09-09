package com.proyectofinal.compras.infrastructure;

import java.sql.Date;
import java.util.List;
import com.proyectofinal.App;
import com.proyectofinal.compras.application.CreateCompraUseCase;
import com.proyectofinal.compras.application.DeleteCompraUseCase;
import com.proyectofinal.compras.application.FindAllCompraUseCase;
import com.proyectofinal.compras.application.FindByIdCompraUseCase;
import com.proyectofinal.compras.application.UpdateCompraUseCase;
import com.proyectofinal.compras.domain.entity.Compra;
import com.proyectofinal.compras.domain.service.CompraService;

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

public class CompraController {
    private CompraService compraService;

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

    // Update

    @FXML
    private VBox updateEntityVbox;
    @FXML
    private TextField UCampId;
    @FXML
    private TextField UCampFechaText;
    @FXML
    private TextField UCampTotal;
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
    private Label campfecha;
    @FXML
    private Label camptotal;
    @FXML
    private Label campidempleado;

    // Find All

    @FXML
    private VBox findAllEntityVbox;
    @FXML
    private TableView<Compra> entitylTableView;
    @FXML
    private TableColumn<Compra, Integer> colid;
    @FXML
    private TableColumn<Compra, String> colfecha;
    @FXML
    private TableColumn<Compra, Integer> coltotal;
    @FXML
    private TableColumn<Compra, Integer> colidempleado;

    @FXML
    public void initialize() {
        this.compraService = new CompraRepository();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colfecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        coltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
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
        Compra compra = new Compra();
        compra.setFecha(Date.valueOf(CFechaText.getText()));
        compra.setTotal(Double.parseDouble(CTotalText.getText()));
        compra.setIdempleado(CEmpleadoId.getText());

        CreateCompraUseCase createCompraUseCase = new CreateCompraUseCase(compraService);
        createCompraUseCase.execute(compra);

        ajustarVboxes(600, createEntityVbox, 0);
        CFechaText.setText("");
        CTotalText.setText("");
        CEmpleadoId.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdCompraUseCase findByIdCompraUseCase = new FindByIdCompraUseCase(compraService);
        Compra compra = findByIdCompraUseCase.execute(Integer.parseInt(UCampId.getText()));

        compra.setFecha(Date.valueOf(UCampFechaText.getText()));
        compra.setTotal(Double.parseDouble(UCampTotal.getText()));
        compra.setIdempleado(UCampIdEmpleado.getText());

        UpdateCompraUseCase updateCompraUseCase = new UpdateCompraUseCase(compraService);
        updateCompraUseCase.execute(compra);

        ajustarVboxes(600, updateEntityVbox, 0);
        UCampId.setText("");
        UCampFechaText.setText("");
        UCampTotal.setText("");
        UCampIdEmpleado.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteCompraUseCase deleteCompraUseCase = new DeleteCompraUseCase(compraService);
        deleteCompraUseCase.execute(Integer.parseInt(DCampId.getText()));

        ajustarVboxes(600, deleteEntityVbox, 0);
        DCampId.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdCompraUseCase findByIdCompraUseCase = new FindByIdCompraUseCase(compraService);
        Compra compra = findByIdCompraUseCase.execute(Integer.parseInt(FBCampId.getText()));
        if (compra != null) {
            campid.setText(String.valueOf(compra.getId()));
            campfecha.setText(String.valueOf(compra.getFecha()));
            camptotal.setText(String.valueOf(compra.getTotal()));
            campidempleado.setText(String.valueOf(compra.getIdempleado()));
        } else {
            campid.setText("Sin Datos");
            campfecha.setText("...😢");
            camptotal.setText("...😢");
            campidempleado.setText("...😢");
        }
    }

    public void findAllEntities() {
        ObservableList<Compra> listaTabla = FXCollections.observableArrayList();
        FindAllCompraUseCase findAllCompraUseCase = new FindAllCompraUseCase(compraService);
        List<Compra> compras = findAllCompraUseCase.execute();
        listaTabla.setAll(compras);
        entitylTableView.setItems(listaTabla);
    }

    @FXML
    public void back() {
        try {
            App.setRoot("compras");
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
