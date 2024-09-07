package com.proyectofinal.paises.infrastructure;

import java.util.List;

import com.proyectofinal.App;
import com.proyectofinal.paises.application.CreatePaisUseCase;
import com.proyectofinal.paises.application.DeletePaisUseCase;
import com.proyectofinal.paises.application.FindAllPaisUseCase;
import com.proyectofinal.paises.application.FindByIdPaisUseCase;
import com.proyectofinal.paises.application.UpdatePaisUseCase;
import com.proyectofinal.paises.domain.entity.Pais;
import com.proyectofinal.paises.domain.service.PaisService;

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

public class PaisController {

    private PaisService paisService;

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
    private TableView<Pais> entitylTableView;
    @FXML
    private TableColumn<Pais, Integer> colid;
    @FXML
    private TableColumn<Pais, String> colname;

    @FXML
    public void initialize() {
        this.paisService = new PaisRepository();
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
        Pais pais = new Pais();
        pais.setNombre(CNameText.getText());
        CreatePaisUseCase createPaisUseCase = new CreatePaisUseCase(paisService);
        createPaisUseCase.execute(pais);
        ajustarVboxes(600, createEntityVbox, 0);
        CNameText.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdPaisUseCase findByIdPaisUseCase = new FindByIdPaisUseCase(paisService);
        Pais pais = findByIdPaisUseCase.execute(Integer.parseInt(UCampId.getText()));
        pais.setNombre(UCampNombre.getText());
        UpdatePaisUseCase updatePaisUseCase = new UpdatePaisUseCase(paisService);
        updatePaisUseCase.execute(pais);
        ajustarVboxes(600, updateEntityVbox, 0);
        UCampId.setText("");
        UCampNombre.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeletePaisUseCase deletePaisUseCase = new DeletePaisUseCase(paisService);
        deletePaisUseCase.execute(Integer.parseInt(DCampId.getText()));
        ajustarVboxes(600, deleteEntityVbox, 0);
        DCampId.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdPaisUseCase findByIdPaisUseCase = new FindByIdPaisUseCase(paisService);
        Pais pais = findByIdPaisUseCase.execute(Integer.parseInt(FBCampId.getText()));
        if (pais != null) {
            campid.setText(String.valueOf(pais.getId()));
            campname.setText(pais.getNombre());
        } else {
            campid.setText("Sin Datos");
            campname.setText("...😢");
        }
    }

    public void findAllEntities() {
        ObservableList<Pais> listaTabla = FXCollections.observableArrayList();
        FindAllPaisUseCase findAllPaisUseCase = new FindAllPaisUseCase(paisService);
        List<Pais> paises = findAllPaisUseCase.execute();
        listaTabla.setAll(paises);
        entitylTableView.setItems(listaTabla);
    }

    @FXML
    public void back() {
        try {
            App.setRoot("paises");
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
