/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AdoptanteDAO;
import dao.MascotaDAO;
import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Adoptante;
import model.Mascota;

/**
 * FXML Controller class
 *
 * @author XOD
 */
public class MainViewController implements Initializable {

    //Data
    private MascotaDAO mascotas;
    private AdoptanteDAO adoptantes;
    private Mascota mascotaselected;
    private Adoptante adoptanteselected;
    private SimpleStringProperty target;
    private SimpleBooleanProperty search;

    //FXML
    @FXML
    private Button add_btn, edit_btn, delete_btn, search_btn;

    @FXML
    private TableView<Mascota> mascota_tbl;

    @FXML
    private TableColumn<Mascota, String> mascota_nombre_col,
            mascota_adoptante_col,
            mascota_raza_col,
            mascota_color_col,
            mascota_genero_col,
            mascota_ultimaDesparacitacion_col,
            mascota_ultimaVacunacion_col,
            mascota_fechaAdopcion_col;

    @FXML
    private TableColumn<Mascota, Integer> mascota_id_col, mascota_edad_col;

    @FXML
    private TableColumn<Mascota, Double> mascota_peso_col;

    @FXML
    private TableView<Adoptante> adoptante_tbl;

    @FXML
    private TableColumn<Adoptante, Integer> adoptante_id_col;

    @FXML
    private TableColumn<Adoptante, String> adoptante_nombre_col,
            adoptante_primerApellido_col,
            adoptante_segundoApellido_col,
            adoptante_ciOPasaporte_col,
            adoptante_genero_col,
            adoptante_direccion_id_col;

    @FXML
    private TextField search_tfield;

    //Methods
    private void MascotaTableContent(ObservableList<Mascota> mascotas) {
        //Setting Mascota Table
        mascota_tbl.setItems(mascotas);
    }

    private void AdoptanteTableContent(ObservableList<Adoptante> adoptantes) {
        //Setting Adoptante Table
        adoptante_tbl.setItems(adoptantes);
    }

    public void initData(ObservableList<Mascota> mascotas, ObservableList<Adoptante> adoptantes) {
        MascotaTableContent(mascotas);
        AdoptanteTableContent(adoptantes);
    }

    public void refreshTable() {
        switch (target.get()) {
            case "Mascota":
                MascotaTableContent(mascotas.getAll());
                break;
            case "Adoptante":
                AdoptanteTableContent(adoptantes.getAll());
                break;
        }
    }

    private void disableButtons(boolean disable) {
        edit_btn.setDisable(disable);
        delete_btn.setDisable(disable);
    }

    //Listeners
    private ChangeListener<Mascota> MascotaSelectorListener() {
        return ((observable, oldValue, newValue) -> {
            if (newValue != null) {
                disableButtons(false);
                mascotaselected = newValue;
            } else {
                disableButtons(true);
            }
        });
    }

    private ChangeListener<Adoptante> AdoptanteSelectorListener() {
        return ((observable, oldValue, newValue) -> {
            if (newValue != null) {
                disableButtons(false);
                adoptanteselected = newValue;
            } else {
                disableButtons(true);
            }
        });
    }

    @FXML
    private void mascota_tab(ActionEvent event) {
        if (!target.get().equals("Mascota")) {
            MascotaTableContent(mascotas.getAll());
            target.set("Mascota");
            search_tfield.setText("");
            disableButtons(true);
            search.set(false);
            mascota_tbl.toFront();

        }
    }

    @FXML
    private void adoptante_tab(ActionEvent event) {
        if (!target.get().equals("Adoptante")) {
            AdoptanteTableContent(adoptantes.getAll());
            target.set("Adoptante");
            search_tfield.setText("");
            disableButtons(true);
            search.set(false);
            adoptante_tbl.toFront();
        }
    }

    @FXML
    private void add(ActionEvent event) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/view/add" + target.get() + ".fxml"));
            Parent root = fxml.load();
            Initializable controller;
            switch (target.get()) {
                case "Mascota":
                    controller = fxml.<AddMascotaController>getController();
                    ((AddMascotaController) controller).setParent(this);
                    break;
                case "Adoptante":
                    controller = fxml.<AddAdoptanteController>getController();
                    ((AddAdoptanteController) controller).setParent(this);
                    break;
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.setTitle("Add " + target.get());
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void edit(ActionEvent event) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/view/add" + target.get() + ".fxml"));
            Parent root = fxml.load();
            Initializable controller;
            switch (target.get()) {
                case "Mascota":
                    controller = fxml.<AddMascotaController>getController();
                    ((AddMascotaController) controller).initData(mascotaselected);
                    ((AddMascotaController) controller).setParent(this);

                    break;
                case "Adoptante":
                    controller = fxml.<AddAdoptanteController>getController();
                    ((AddAdoptanteController) controller).initData(adoptanteselected);
                    ((AddAdoptanteController) controller).setParent(this);
                    break;
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit " + target.get());
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void delete(ActionEvent event) {
        switch (target.get()) {
            case "Mascota":
                mascotas.delete(mascotaselected.getMascota_id());
                mascotaselected = null;
                break;
            case "Adoptante":
                adoptantes.delete(adoptanteselected.getAdoptante_id());
                adoptanteselected = null;
                break;
        }
        AdoptanteTableContent(adoptantes.getAll());
        MascotaTableContent(mascotas.getAll());
        disableButtons(true);
    }

    @FXML
    private void search_tab(ActionEvent event) {
        if (!search.get()) {
            search_tfield.setText("");
            search_tfield.toFront();
            search_tfield.requestFocus();
            search.set(true);
        } else {
            search_tfield.toBack();
            search_tfield.setText("");
            search.set(false);
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Setting Actions Buttons
        disableButtons(true);

        //Setting Mascota Table
        mascotas = new MascotaDAO();
        mascota_id_col.setCellValueFactory(new PropertyValueFactory("mascota_id"));
        mascota_edad_col.setCellValueFactory(new PropertyValueFactory("edad"));
        mascota_peso_col.setCellValueFactory(new PropertyValueFactory<>("peso"));
        mascota_nombre_col.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        mascota_adoptante_col.setCellValueFactory(new PropertyValueFactory<>("adoptante"));
        mascota_raza_col.setCellValueFactory(new PropertyValueFactory<>("raza"));
        mascota_color_col.setCellValueFactory(new PropertyValueFactory<>("color"));
        mascota_genero_col.setCellValueFactory(new PropertyValueFactory<>("genero"));
        mascota_ultimaDesparacitacion_col.setCellValueFactory(new PropertyValueFactory<>("ultimaDesparacitacion"));
        mascota_ultimaVacunacion_col.setCellValueFactory(new PropertyValueFactory<>("ultimaVacunacion"));
        mascota_fechaAdopcion_col.setCellValueFactory(new PropertyValueFactory<>("fechaAdopcion"));
        mascota_tbl.getSelectionModel().selectedItemProperty().addListener(MascotaSelectorListener());

        //Setting Adoptante Table
        adoptantes = new AdoptanteDAO();
        adoptante_id_col.setCellValueFactory(new PropertyValueFactory<>("adoptante_id"));
        adoptante_nombre_col.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        adoptante_primerApellido_col.setCellValueFactory(new PropertyValueFactory<>("primerApellido"));
        adoptante_segundoApellido_col.setCellValueFactory(new PropertyValueFactory<>("segundoApellido"));
        adoptante_ciOPasaporte_col.setCellValueFactory(new PropertyValueFactory<>("ciOPasaporte"));
        adoptante_genero_col.setCellValueFactory(new PropertyValueFactory<>("genero"));
        adoptante_direccion_id_col.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        adoptante_tbl.getSelectionModel().selectedItemProperty().addListener(AdoptanteSelectorListener());

        //Setting Tabs
        target = new SimpleStringProperty("Mascota");
        search = new SimpleBooleanProperty(false);
        search_tfield.textProperty().addListener((observable, oldValue, newValue) -> {
            switch (target.get()) {
                case "Mascota":
                    MascotaTableContent(mascotas.search(newValue));
                    break;
                case "Adoptante":
                    AdoptanteTableContent(adoptantes.search(newValue));
                    break;
            }
        });
    }
}
