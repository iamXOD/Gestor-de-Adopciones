/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AdoptanteDAO;
import dao.MascotaDAO;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
public class AddMascotaController implements Initializable {

    //Properties
    private boolean editing;
    private SimpleStringProperty error;
    private Queue<String> errors;
    private MascotaDAO mascotas;
    private AdoptanteDAO adoptantes;
    private Mascota mascota;
    private MainViewController parentstage;

    @FXML
    private TextField nombre_tfield;

    @FXML
    private ComboBox<Adoptante> adoptante_id_cmbox;

    @FXML
    private DatePicker fechaAdopcion_dtpicker;

    @FXML
    private DatePicker ultimaDesparacitacion_dtpicker;

    @FXML
    private DatePicker ultimaVacunacion_dtpicker;

    @FXML
    private TextField raza_tfield;

    @FXML
    private TextField color_tfield;

    @FXML
    private TextField edad_tfield;

    @FXML
    private TextField peso_tfield;

    @FXML
    private RadioButton fem_rbut;

    @FXML
    private ToggleGroup genero;

    @FXML
    private RadioButton masc_rbut;

    @FXML
    private Label error_lbl;

    //Methods
    public void initData(Mascota mascota) {
        this.mascota = mascota;
        editing = true;
        fillFields();
    }

    public void setParent(MainViewController c) {
        this.parentstage = c;
    }

    private void initNumsField() {
        edad_tfield.textProperty().addListener(regexchecker("^[0-9]+$"));
        peso_tfield.textProperty().addListener(regexchecker("^[0-9\\.]+$"));
    }

    private void InitCmboxs() {
        adoptante_id_cmbox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.getAdoptante_id() == 0) {
                addAdoptante();
            }
        });
    }

    private void initDatePickers() {
        fechaAdopcion_dtpicker.setValue(LocalDate.now());
        ultimaDesparacitacion_dtpicker.setValue(LocalDate.now());
        ultimaVacunacion_dtpicker.setValue(LocalDate.now());
    }

    public void fillCMBox(Adoptante adoptante) {
        fillCMBox();
        adoptante_id_cmbox.getSelectionModel().select(adoptante);
    }

    public void fillCMBox() {
        adoptante_id_cmbox.setItems(adoptantes.getAll());
        Adoptante empty = new Adoptante();
        empty.setAdoptante_id(0);
        empty.setNombre("Adoptante");
        empty.setPrimerApellido("Nuevo");
        empty.setSegundoApellido("");
        adoptante_id_cmbox.getItems().add(0, empty);
    }

    private void fillFields() {
        nombre_tfield.setText(mascota.getNombre());
        adoptante_id_cmbox.getSelectionModel().select(mascota.getAdoptante());
        fechaAdopcion_dtpicker.setValue(LocalDate.parse(mascota.getFechaAdopcion()));
        ultimaDesparacitacion_dtpicker.setValue(LocalDate.parse(mascota.getUltimaDesparacitacion()));
        ultimaVacunacion_dtpicker.setValue(LocalDate.parse(mascota.getUltimaVacunacion()));
        raza_tfield.setText(mascota.getRaza());
        color_tfield.setText(mascota.getColor());
        edad_tfield.setText(Integer.toString(mascota.getEdad()));
        peso_tfield.setText(Double.toString(mascota.getPeso()));
        genero.selectToggle(mascota.getGenero() ? masc_rbut : fem_rbut);
    }

    private boolean errormanager() {
        String letras = "^[a-zA-Zñáéíóú_\\- ]+$";
        String date = "[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}";
        boolean ready = true;
        errors.clear();
        if (nombre_tfield.getText().equals("")) {
            errors.add("Debe rellenar el nombre");
            ready = false;
        }
        if (!nombre_tfield.getText().matches(letras)) {
            errors.add("El nombre sólo debe tener letras");
            ready = false;
        }
        if (fechaAdopcion_dtpicker.getValue() == null) {
            errors.add("Debe rellenar la fecha de adopción");
            ready = false;
        }
        if (!fechaAdopcion_dtpicker.getValue().toString().matches(date)) {
            errors.add("Entre una fecha de adopción correcta");
            ready = false;
        }
        if (ultimaDesparacitacion_dtpicker.getValue() == null) {
            errors.add("Debe rellenar la fecha de la última desparacitación");
            ready = false;
        }
        if (!ultimaDesparacitacion_dtpicker.getValue().toString().matches(date)) {
            errors.add("Entre una fecha de desparacitación correcta");
            ready = false;
        }
        if (ultimaVacunacion_dtpicker.getValue() == null) {
            errors.add("Debe rellenar la fecha de la última vacunación");
            ready = false;
        }
        if (!ultimaVacunacion_dtpicker.getValue().toString().matches(date)) {
            errors.add("Entre una fecha de vacunación correcta");
            ready = false;
        }
        if (raza_tfield.getText().equals("")) {
            errors.add("Debe rellenar la raza");
        }
        if (!raza_tfield.getText().matches(letras)) {
            errors.add("La raza debe tener letras solamente");
            ready = false;
        }
        if (color_tfield.getText().equals("")) {
            errors.add("Debe rellenar el color");
            ready = false;
        }
        if (edad_tfield.getText().equals("")) {
            errors.add("Debe rellenar la edad");
            ready = false;
        }
        if (!edad_tfield.getText().matches("^[0-9]+$")) {
            errors.add("La edad debe contener sólo números");
            ready = false;
        }
        if (peso_tfield.getText().equals("")) {
            errors.add("Debe rellenar el peso");
            ready = false;
        }
        if (peso_tfield.getText().matches("^.*[^0-9\\.].*$")) {
            errors.add("El peso debe contener sólo números");
            ready = false;
        }
        if (!peso_tfield.getText().matches("^[0-9]+(.[0-9]+)?$")) {
            errors.add("Entre un peso correcto");
            ready = false;
        }
        if (genero.getSelectedToggle() == null) {
            errors.add("Debe seleccionar un genero");
            ready = false;
        }
        return ready;
    }

    private void addAdoptante() {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/view/addAdoptante.fxml"));
            Parent root = fxml.load();
            AddAdoptanteController controller = fxml.<AddAdoptanteController>getController();
            controller.setParent(this);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.setTitle("Add Adoptante");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ChangeListener<String> regexchecker(String regex) {
        return (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (newValue.matches(regex) || newValue.matches("")) {
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
                mascota = new Mascota();
            }
            mascota.setNombre(nombre_tfield.getText());
            mascota.setAdoptante(adoptante_id_cmbox.getValue());
            mascota.setFechaAdopcion(fechaAdopcion_dtpicker.getValue().toString());
            mascota.setUltimaDesparacitacion(ultimaDesparacitacion_dtpicker.getValue().toString());
            mascota.setUltimaVacunacion(ultimaVacunacion_dtpicker.getValue().toString());
            mascota.setRaza(raza_tfield.getText());
            mascota.setColor(color_tfield.getText());
            mascota.setEdad(Integer.parseInt(edad_tfield.getText()));
            mascota.setPeso(Double.parseDouble(peso_tfield.getText()));
            mascota.setGenero(genero.getSelectedToggle().equals(masc_rbut));
            if (editing) {
                mascotas.update(mascota);
            } else {
                mascotas.add(mascota);
            }
            this.parentstage.refreshTable();
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
        mascotas = new MascotaDAO();
        adoptantes = new AdoptanteDAO();
        InitCmboxs();
        ObservableList<Adoptante> aux = adoptantes.getAll();
        if (!aux.isEmpty()) {
            fillCMBox(aux.get(0));
        } else {
            fillCMBox();
        }
        initNumsField();
        initDatePickers();
    }
}
