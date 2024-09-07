package com.proyectofinal.ciudades.infrastructure;

import java.util.List;
import com.proyectofinal.App;
import com.proyectofinal.ciudades.application.CreateCiudadUseCase;
import com.proyectofinal.ciudades.application.DeleteCiudadUseCase;
import com.proyectofinal.ciudades.application.FindAllCiudadUseCase;
import com.proyectofinal.ciudades.application.FindByIdCiudadUseCase;
import com.proyectofinal.ciudades.application.UpdateCiudadUseCase;
import com.proyectofinal.ciudades.domain.entity.Ciudad;
import com.proyectofinal.ciudades.domain.service.CiudadService;

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

public class CiudadController {
    private CiudadService ciudadService;

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
    @FXML
    private TextField CPaisIdText; // ++ Nuevo

    // Update

    @FXML
    private VBox updateEntityVbox;
    @FXML
    private TextField UCampId;
    @FXML
    private TextField UCampNombre;
    @FXML
    private TextField UCampIdPais; // ++ Nuevo

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
    private Label campidpais;

    // Find All

    @FXML
    private VBox findAllEntityVbox;
    @FXML
    private TableView<Ciudad> entitylTableView;
    @FXML
    private TableColumn<Ciudad, Integer> colid;
    @FXML
    private TableColumn<Ciudad, String> colname;
    @FXML
    private TableColumn<Ciudad, Integer> colidpais;

    @FXML
    public void initialize() {
        this.ciudadService = new CiudadRepository();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colidpais.setCellValueFactory(new PropertyValueFactory<>("idpais"));
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
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre(CNameText.getText());
        ciudad.setIdpais(Integer.parseInt(CPaisIdText.getText()));
        CreateCiudadUseCase createCiudadUseCase = new CreateCiudadUseCase(ciudadService);
        createCiudadUseCase.execute(ciudad);
        ajustarVboxes(600, createEntityVbox, 0);
        CNameText.setText("");
        CPaisIdText.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdCiudadUseCase findByIdCiudadUseCase = new FindByIdCiudadUseCase(ciudadService);
        Ciudad ciudad = findByIdCiudadUseCase.execute(Integer.parseInt(UCampId.getText()));
        ciudad.setNombre(UCampNombre.getText());
        ciudad.setIdpais(Integer.parseInt(UCampIdPais.getText()));
        UpdateCiudadUseCase updateCiudadUseCase = new UpdateCiudadUseCase(ciudadService);
        updateCiudadUseCase.execute(ciudad);
        ajustarVboxes(600, updateEntityVbox, 0);
        UCampId.setText("");
        UCampNombre.setText("");
        UCampIdPais.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteCiudadUseCase deleteCiudadUseCase = new DeleteCiudadUseCase(ciudadService);
        deleteCiudadUseCase.execute(Integer.parseInt(DCampId.getText()));
        ajustarVboxes(600, deleteEntityVbox, 0);
        DCampId.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdCiudadUseCase findByIdCiudadUseCase = new FindByIdCiudadUseCase(ciudadService);
        Ciudad ciudad = findByIdCiudadUseCase.execute(Integer.parseInt(FBCampId.getText()));
        if (ciudad != null) {
            campid.setText(String.valueOf(ciudad.getId()));
            campname.setText(ciudad.getNombre());
            campidpais.setText(String.valueOf(ciudad.getIdpais()));
        } else {
            campid.setText("Sin Datos");
            campname.setText("...😢");
            campidpais.setText("...😢");
        }
    }

    public void findAllEntities() {
        ObservableList<Ciudad> listaTabla = FXCollections.observableArrayList();
        FindAllCiudadUseCase findAllCiudadUseCase = new FindAllCiudadUseCase(ciudadService);
        List<Ciudad> ciudades = findAllCiudadUseCase.execute();
        listaTabla.setAll(ciudades);
        entitylTableView.setItems(listaTabla);
    }

    @FXML
    public void back() {
        try {
            App.setRoot("ciudades");
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