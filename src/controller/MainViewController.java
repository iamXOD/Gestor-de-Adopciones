/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AdoptanteDAO;
import dao.MascotaDAO;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private SimpleStringProperty target;
    private SimpleBooleanProperty search;

    //FXML
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

    @FXML
    private void mascota_tab(ActionEvent event) {
        MascotaTableContent(mascotas.getAll());
        mascota_tbl.toFront();
    }

    @FXML
    private void adoptante_tab(ActionEvent event) {
        AdoptanteTableContent(adoptantes.getAll());
        adoptante_tbl.toFront();
    }

    @FXML
    private void search_tab(ActionEvent event) {
        if (!search.get()) {
            search_tfield.toFront();
            search_tfield.requestFocus();
            search.set(true);
        } else {
            search_tfield.toBack();
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

        //Setting Adoptante Table
        adoptantes = new AdoptanteDAO();
        adoptante_id_col.setCellValueFactory(new PropertyValueFactory<>("adoptante_id"));
        adoptante_nombre_col.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        adoptante_primerApellido_col.setCellValueFactory(new PropertyValueFactory<>("primerApellido"));
        adoptante_segundoApellido_col.setCellValueFactory(new PropertyValueFactory<>("segundoApellido"));
        adoptante_ciOPasaporte_col.setCellValueFactory(new PropertyValueFactory<>("ciOPasaporte"));
        adoptante_genero_col.setCellValueFactory(new PropertyValueFactory<>("genero"));
        adoptante_direccion_id_col.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        //Setting Tabs
        target = new SimpleStringProperty("mascota");
        search = new SimpleBooleanProperty(false);
        mascota_tab(new ActionEvent());

    }

}
