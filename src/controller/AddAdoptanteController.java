/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AdoptanteDAO;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Adoptante;
import model.Direccion;

/**
 * FXML Controller class
 *
 * @author XOD
 */
public class AddAdoptanteController implements Initializable {

    //Properties
    private AdoptanteDAO adoptantes;
    private Adoptante adoptante;
    private Direccion direccion;
    private boolean editing;
    private SimpleStringProperty error;
    private Queue<String> errors;
    private Initializable parentstage;

    @FXML
    private TextField nombre_tfield, primerApellido_tfield,
            segundoApellido_tfield, ciOPasaporte_tfield;

    @FXML
    private ToggleGroup genero;

    @FXML
    private RadioButton femenino_rbut, masculino_rbut;

    @FXML
    private TextField callePrincipal_tfield, entreCalle_tfield,
            yCalle_tfield, no_tfield, localidad_tfield;

    @FXML
    private ComboBox<String> municipio_cmbox, provincia_cmbox;

    @FXML
    private Label error_lbl;

    //Methods
    public void initData(Adoptante adoptante) {
        editing = true;
        this.adoptante = adoptante;
        fillFields();
    }

    public void setParent(Initializable c) {
        this.parentstage = c;
    }

    private void fillFields() {
        nombre_tfield.setText(adoptante.getNombre());
        primerApellido_tfield.setText(adoptante.getPrimerApellido());
        segundoApellido_tfield.setText(adoptante.getSegundoApellido());
        ciOPasaporte_tfield.setText(adoptante.getCiOPasaporte());
        genero.selectToggle(adoptante.getGenero() ? masculino_rbut : femenino_rbut);
        direccion = adoptante.getDireccion();
        callePrincipal_tfield.setText(direccion.getCallePrincipal());
        entreCalle_tfield.setText(direccion.getEntreCalle());
        yCalle_tfield.setText(direccion.getyCalle());
        no_tfield.setText(direccion.getNo());
        localidad_tfield.setText(direccion.getLocalidad());
        provincia_cmbox.getSelectionModel().select(direccion.getProvincia());
        municipio_cmbox.getSelectionModel().select(direccion.getMunicipio());
    }

    private boolean errormanager() {
        String letras = "[a-zA-Zñáéíóú_\\-]";
        boolean ready = true;
        errors.clear();
        if (nombre_tfield.getText().equals("")) {
            errors.add("Debe rellenar el nombre");
            ready = false;
        }
        if (nombre_tfield.getText().matches(".*[^a-zA-Zñáéíóú_\\- ].*")) {
            errors.add("El nombre solo debe tener letras");
            ready = false;
        }
        if (!nombre_tfield.getText().matches("^" + letras + "{3,}( " + letras + "{3,})?$")) {
            errors.add("Entre 1 o 2 nombres de mas de 2 caracteres");
            ready = false;
        }
        if (primerApellido_tfield.getText().equals("")) {
            errors.add("Debe rellenar el primer apellido");
            ready = false;
        }
        if (!primerApellido_tfield.getText().matches("^" + letras + "{2,}$")) {
            errors.add("El primer appellido debe tener letras solamente");
            ready = false;
        }
        if (segundoApellido_tfield.getText().equals("")) {
            errors.add("Debe rellenar el segundo apellido");
        }
        if (!segundoApellido_tfield.getText().matches("^" + letras + "{2,}$")) {
            errors.add("El segundo apellido debe tener letras solamente");
            ready = false;
        }
        if (ciOPasaporte_tfield.getText().equals("")) {
            errors.add("Debe rellenar el Carnet de Identidad");
            ready = false;
        }
        if (!ciOPasaporte_tfield.getText().matches("^[0-9]+$")) {
            errors.add("El Carnet de Identidad debe contener solo numeros");
            ready = false;
        }
        if (ciOPasaporte_tfield.getText().length() != 11) {
            errors.add("El Carnet de Identidad debe tener 11 digitos");
            ready = false;
        }
        if (genero.getSelectedToggle() == null) {
            errors.add("Debe seleccionar un genero");
            ready = false;
        }
        if (callePrincipal_tfield.getText().equals("")) {
            errors.add("Debe rellenar la Calle Principal");
            ready = false;
        }
        if (!no_tfield.getText().matches("^[0-9]+$")) {
            errors.add("Debe rellenar el numero correctamente");
            ready = false;
        }
        if (localidad_tfield.getText().equals("")) {
            errors.add("Debe rellenar la localidad");
            ready = false;
        }
        return ready;

    }

    private void initNumsField() {
        ciOPasaporte_tfield.textProperty().addListener(numChange());
    }

    private void InitCmboxs() {
        provincia_cmbox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            setMunicipios(newValue.intValue());
        });
        provincia_cmbox.setItems(FXCollections.observableArrayList(Arrays.asList(Direccion.getProvincias())));
        provincia_cmbox.getSelectionModel().selectFirst();
    }

    private void setMunicipios(int index) {
        municipio_cmbox.setItems(FXCollections.observableArrayList(Arrays.asList(Direccion.getMunicipios()[index])));
        municipio_cmbox.getSelectionModel().selectFirst();
    }

    //Listeners
    private ChangeListener<String> numChange() {
        return (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (newValue.matches("^[0-9]+$") || newValue.matches("")) {
                error.set("");
            } else {
                error.set("CI solo debe contener numeros");
            }
        };
    }

    @FXML
    void save(ActionEvent event) {
        if (errormanager()) {
            if (!editing) {
                adoptante = new Adoptante();
                direccion = new Direccion();
            }
            adoptante.setNombre(nombre_tfield.getText());
            adoptante.setPrimerApellido(primerApellido_tfield.getText());
            adoptante.setSegundoApellido(segundoApellido_tfield.getText());
            adoptante.setCiOPasaporte(ciOPasaporte_tfield.getText());
            adoptante.setGenero(genero.getSelectedToggle().equals(masculino_rbut));

            direccion.setCallePrincipal(callePrincipal_tfield.getText());
            direccion.setEntreCalle(entreCalle_tfield.getText());
            direccion.setyCalle(yCalle_tfield.getText());
            direccion.setNo(no_tfield.getText());
            direccion.setLocalidad(localidad_tfield.getText());
            direccion.setMunicipio(municipio_cmbox.getValue());
            direccion.setProvincia(provincia_cmbox.getValue());
            adoptante.setDireccion(direccion);
            int rowid;
            if (editing) {
                rowid = adoptantes.update(adoptante);
            } else {
                rowid = adoptantes.add(adoptante);
            }
            adoptante.setAdoptante_id(rowid);
            if (parentstage instanceof MainViewController) {
                ((MainViewController) this.parentstage).refreshTable();
            } else if (parentstage instanceof AddMascotaController) {
                ((AddMascotaController) parentstage).fillCMBox(adoptante);
            }

            this.quit(new ActionEvent());
        } else {
            error.set(errors.peek());
        }
    }

    @FXML
    void quit(ActionEvent event) {
        Stage stage = (Stage) nombre_tfield.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        editing = false;
        error = new SimpleStringProperty();
        error_lbl.textProperty().bind(error);
        errors = new LinkedList<>();
        adoptantes = new AdoptanteDAO();
        InitCmboxs();
        initNumsField();

    }

}
