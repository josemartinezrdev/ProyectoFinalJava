package com.proyectofinal.categorias.infrastructure;

import java.util.List;

import com.proyectofinal.App;
import com.proyectofinal.categorias.application.CreateCategoriaUseCase;
import com.proyectofinal.categorias.application.DeleteCategoriaUseCase;
import com.proyectofinal.categorias.application.FindAllCategoriaUseCase;
import com.proyectofinal.categorias.application.FindByIdCategoriaUseCase;
import com.proyectofinal.categorias.application.UpdateCategoriaUseCase;
import com.proyectofinal.categorias.domain.entity.Categoria;
import com.proyectofinal.categorias.domain.service.CategoriaService;

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

public class CategoriaController {
    private CategoriaService categoriaService;

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
    private TableView<Categoria> entitylTableView;
    @FXML
    private TableColumn<Categoria, Integer> colid;
    @FXML
    private TableColumn<Categoria, String> colname;

    @FXML
    public void initialize() {
        this.categoriaService = new CategoriaRepository();
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
        Categoria categoria = new Categoria();
        categoria.setNombre(CNameText.getText());
        CreateCategoriaUseCase createCategoriaUseCase = new CreateCategoriaUseCase(categoriaService);
        createCategoriaUseCase.execute(categoria);
        ajustarVboxes(600, createEntityVbox, 0);
        CNameText.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdCategoriaUseCase findByIdCategoriaUseCase = new FindByIdCategoriaUseCase(categoriaService);
        Categoria categoria = findByIdCategoriaUseCase.execute(Integer.parseInt(UCampId.getText()));
        categoria.setNombre(UCampNombre.getText());
        UpdateCategoriaUseCase updateCategoriaUseCase = new UpdateCategoriaUseCase(categoriaService);
        updateCategoriaUseCase.execute(categoria);
        ajustarVboxes(600, updateEntityVbox, 0);
        UCampId.setText("");
        UCampNombre.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteCategoriaUseCase deleteCategoriaUseCase = new DeleteCategoriaUseCase(categoriaService);
        deleteCategoriaUseCase.execute(Integer.parseInt(DCampId.getText()));
        ajustarVboxes(600, deleteEntityVbox, 0);
        DCampId.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdCategoriaUseCase findByIdCategoriaUseCase = new FindByIdCategoriaUseCase(categoriaService);
        Categoria categoria = findByIdCategoriaUseCase.execute(Integer.parseInt(FBCampId.getText()));
        if (categoria != null) {
            campid.setText(String.valueOf(categoria.getId()));
            campname.setText(categoria.getNombre());
        } else {
            campid.setText("Sin Datos");
            campname.setText("...😢");
        }
    }

    public void findAllEntities() {
        ObservableList<Categoria> listaTabla = FXCollections.observableArrayList();
        FindAllCategoriaUseCase findAllCategoriaUseCase = new FindAllCategoriaUseCase(categoriaService);
        List<Categoria> categoriaes = findAllCategoriaUseCase.execute();
        listaTabla.setAll(categoriaes);
        entitylTableView.setItems(listaTabla);
    }

    @FXML
    public void back() {
        try {
            App.setRoot("categorias");
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
