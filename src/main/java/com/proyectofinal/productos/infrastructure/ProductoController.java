package com.proyectofinal.productos.infrastructure;

import java.util.List;
import com.proyectofinal.App;
import com.proyectofinal.productos.application.CreateProductoUseCase;
import com.proyectofinal.productos.application.DeleteProductoUseCase;
import com.proyectofinal.productos.application.FindAllProductoUseCase;
import com.proyectofinal.productos.application.FindByIdProductoUseCase;
import com.proyectofinal.productos.application.UpdateProductoUseCase;
import com.proyectofinal.productos.domain.entity.Producto;
import com.proyectofinal.productos.domain.service.ProductoService;

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

public class ProductoController {
    private ProductoService productoService;

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
    private TextField CNombreText;
    @FXML
    private TextField CPrecioText;
    @FXML
    private TextField CCategoriaIdText;

    // Update

    @FXML
    private VBox updateEntityVbox;
    @FXML
    private TextField UCampId;
    @FXML
    private TextField UCampNombre;
    @FXML
    private TextField UCampPrecio;
    @FXML
    private TextField UCampIdCategoria;

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
    private Label campnombre;
    @FXML
    private Label campprecio;
    @FXML
    private Label campidcategoria;

    // Find All

    @FXML
    private VBox findAllEntityVbox;
    @FXML
    private TableView<Producto> entitylTableView;
    @FXML
    private TableColumn<Producto, Integer> colid;
    @FXML
    private TableColumn<Producto, String> colnombre;
    @FXML
    private TableColumn<Producto, Integer> colprecio;
    @FXML
    private TableColumn<Producto, Integer> colidcategoria;

    @FXML
    public void initialize() {
        this.productoService = new ProductoRepository();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colprecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colidcategoria.setCellValueFactory(new PropertyValueFactory<>("idcategoria"));
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
        Producto producto = new Producto();
        producto.setNombre(CNombreText.getText());
        producto.setPrecio(Double.parseDouble(CPrecioText.getText()));
        producto.setIdcategoria(Integer.parseInt(CCategoriaIdText.getText()));

        CreateProductoUseCase createProductoUseCase = new CreateProductoUseCase(productoService);
        createProductoUseCase.execute(producto);

        ajustarVboxes(600, createEntityVbox, 0);
        CNombreText.setText("");
        CPrecioText.setText("");
        CCategoriaIdText.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdProductoUseCase findByIdProductoUseCase = new FindByIdProductoUseCase(productoService);
        Producto producto = findByIdProductoUseCase.execute(Integer.parseInt(UCampId.getText()));

        producto.setNombre(UCampNombre.getText());
        producto.setPrecio(Double.parseDouble(UCampPrecio.getText()));
        producto.setIdcategoria(Integer.parseInt(UCampIdCategoria.getText()));

        UpdateProductoUseCase updateProductoUseCase = new UpdateProductoUseCase(productoService);
        updateProductoUseCase.execute(producto);

        ajustarVboxes(600, updateEntityVbox, 0);
        UCampId.setText("");
        UCampNombre.setText("");
        UCampPrecio.setText("");
        UCampIdCategoria.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteProductoUseCase deleteProductoUseCase = new DeleteProductoUseCase(productoService);
        deleteProductoUseCase.execute(Integer.parseInt(DCampId.getText()));

        ajustarVboxes(600, deleteEntityVbox, 0);
        DCampId.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdProductoUseCase findByIdProductoUseCase = new FindByIdProductoUseCase(productoService);
        Producto producto = findByIdProductoUseCase.execute(Integer.parseInt(FBCampId.getText()));
        if (producto != null) {
            campid.setText(String.valueOf(producto.getId()));
            campnombre.setText(producto.getNombre());
            campprecio.setText(String.valueOf(producto.getPrecio()));
            campidcategoria.setText(String.valueOf(producto.getIdcategoria()));
        } else {
            campid.setText("Sin Datos");
            campnombre.setText("...😢");
            campprecio.setText("...😢");
            campidcategoria.setText("...😢");
        }
    }

    public void findAllEntities() {
        ObservableList<Producto> listaTabla = FXCollections.observableArrayList();
        FindAllProductoUseCase findAllProductoUseCase = new FindAllProductoUseCase(productoService);
        List<Producto> productos = findAllProductoUseCase.execute();
        listaTabla.setAll(productos);
        entitylTableView.setItems(listaTabla);
    }

    @FXML
    public void back() {
        try {
            App.setRoot("productos");
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
