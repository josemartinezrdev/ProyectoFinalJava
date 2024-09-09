package com.proyectofinal.productos_bodegas.infrastructure;

import java.io.IOException;
import java.util.List;

import com.proyectofinal.App;
import com.proyectofinal.productos_bodegas.application.CreateProdBodUseCase;
import com.proyectofinal.productos_bodegas.application.DeleteProdBodUseCase;
import com.proyectofinal.productos_bodegas.application.FindAllProdBodUseCase;
import com.proyectofinal.productos_bodegas.application.FindByIdProdBodUseCase;
import com.proyectofinal.productos_bodegas.application.UpdateProdBodUseCase;
import com.proyectofinal.productos_bodegas.domain.entity.ProdBod;
import com.proyectofinal.productos_bodegas.domain.service.ProdBodService;

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

public class ProdBodController {

    private ProdBodService prodbodService;
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
    private TextField CProductoIdText;

    @FXML
    private TextField CBodegaIdText;

    // Update

    @FXML
    private VBox updateEntityVbox;

    @FXML
    private TextField UCampIdProducto;

    @FXML
    private TextField UCampIdBodega;

    @FXML
    private TextField UNEWCampIdProducto;

    @FXML
    private TextField UNEWCampIdBodega;

    // Find By Id
    @FXML
    private HBox findByIdEntityVbox;

    @FXML
    private TextField FBProductoId;

    @FXML
    private TextField FBBodegaId;

    @FXML
    private Label campidproducto;

    @FXML
    private Label campidbodega;

    // Delete

    @FXML
    private VBox deleteEntityVbox;

    @FXML
    private TextField DCampIdProducto;

    @FXML
    private TextField DCampIdBodega;

    // Find ALL

    @FXML
    private VBox findAllEntityVbox;

    @FXML
    private TableView<ProdBod> entitylTableView;

    @FXML
    private TableColumn<ProdBod, Integer> colidproducto;

    @FXML
    private TableColumn<ProdBod, Integer> colidbodega;

    @FXML
    public void initialize() {
        this.prodbodService = new ProdBodRepository();
        colidproducto.setCellValueFactory(new PropertyValueFactory<>("idproducto"));
        colidbodega.setCellValueFactory(new PropertyValueFactory<>("idbodega"));
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
            findAllProdBods();
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
        ProdBod prodBod = new ProdBod();
        prodBod.setIdproducto(Integer.parseInt(CProductoIdText.getText()));
        prodBod.setIdbodega(Integer.parseInt(CBodegaIdText.getText()));
        CreateProdBodUseCase createProdBodUseCase = new CreateProdBodUseCase(prodbodService);
        createProdBodUseCase.execute(prodBod);
        ajustarBoxes(600, createEntityVbox, 0);
        CProductoIdText.setText("");
        CBodegaIdText.setText("");
    }

    @FXML
    public void updateEntity() {
        FindByIdProdBodUseCase findProdBodUseCase = new FindByIdProdBodUseCase(prodbodService);
        ProdBod prodBod = findProdBodUseCase.execute(
                Integer.parseInt(UCampIdProducto.getText()),
                Integer.parseInt(UCampIdBodega.getText()));

        prodBod.setIdproducto(Integer.parseInt(UNEWCampIdProducto.getText()));
        prodBod.setIdbodega(Integer.parseInt(UNEWCampIdBodega.getText()));

        UpdateProdBodUseCase updateProdBodUseCase = new UpdateProdBodUseCase(prodbodService);
        updateProdBodUseCase.execute(
                prodBod,
                Integer.parseInt(UCampIdProducto.getText()),
                Integer.parseInt(UCampIdBodega.getText()));
                
        ajustarBoxes(600, updateEntityVbox, 0);
        UCampIdProducto.setText("");
        UCampIdBodega.setText("");
        UNEWCampIdProducto.setText("");
        UNEWCampIdBodega.setText("");
    }

    @FXML
    public void deleteEntity() {
        DeleteProdBodUseCase deleteProdBodUseCase = new DeleteProdBodUseCase(prodbodService);
        deleteProdBodUseCase.execute(Integer.parseInt(DCampIdProducto.getText()),
                Integer.parseInt(DCampIdBodega.getText()));
        ajustarBoxes(600, deleteEntityVbox, 0);
        DCampIdProducto.setText("");
        DCampIdBodega.setText("");
    }

    @FXML
    public void findByIdEntity() {
        FindByIdProdBodUseCase findProdBodUseCase = new FindByIdProdBodUseCase(prodbodService);
        ProdBod prodBod = findProdBodUseCase.execute(Integer.parseInt(FBProductoId.getText()),
                Integer.parseInt(FBBodegaId.getText()));
        if (prodBod != null) {
            campidproducto.setText(String.valueOf(prodBod.getIdproducto()));
            campidbodega.setText(String.valueOf(prodBod.getIdbodega()));
        } else {
            campidproducto.setText("Sin datos");
            campidbodega.setText("...😢");
        }
    }

    @FXML
    public void findAllProdBods() {
        FindAllProdBodUseCase findAllProdBodUseCase = new FindAllProdBodUseCase(prodbodService);
        List<ProdBod> prodBods = findAllProdBodUseCase.execute();
        ObservableList<ProdBod> prodBodList = FXCollections.observableArrayList(prodBods);
        entitylTableView.setItems(prodBodList);
    }

    @FXML
    public void back() {
        FBProductoId.setText("");
        FBBodegaId.setText("");
        try {
            App.setRoot("productos_bodegas");
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
