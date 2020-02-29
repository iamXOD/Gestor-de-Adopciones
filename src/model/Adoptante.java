/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author HAROLD
 */
public class Adoptante {

    public static Adoptante EMPTY = new Adoptante();

    private SimpleIntegerProperty adoptante_id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty primerApellido;
    private SimpleStringProperty segundoApellido;
    private SimpleStringProperty ciOPasaporte;
    private SimpleBooleanProperty genero;
    private Direccion direccion;

    public Adoptante(int adoptante_id, String nombre, String primerApellido, String segundoApellido, String CI_or_Passport, boolean sexo, Direccion direccion) {
        this.adoptante_id = new SimpleIntegerProperty(adoptante_id);
        this.nombre = new SimpleStringProperty(nombre);
        this.primerApellido = new SimpleStringProperty(primerApellido);
        this.segundoApellido = new SimpleStringProperty(segundoApellido);
        this.ciOPasaporte = new SimpleStringProperty(CI_or_Passport);
        this.genero = new SimpleBooleanProperty(sexo);
        this.direccion = direccion;
    }

    public Adoptante() {
    }

    public SimpleIntegerProperty getAdoptante_id() {
        return adoptante_id;
    }

    public void setAdoptante_id(SimpleIntegerProperty adoptante_id) {
        this.adoptante_id = adoptante_id;
    }

    public SimpleStringProperty getNombre() {
        return nombre;
    }

    public void setNombre(SimpleStringProperty nombre) {
        this.nombre = nombre;
    }

    public SimpleStringProperty getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(SimpleStringProperty primerApellido) {
        this.primerApellido = primerApellido;
    }

    public SimpleStringProperty getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(SimpleStringProperty segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public SimpleStringProperty getCiOPasaporte() {
        return ciOPasaporte;
    }

    public void setCiOPasaporte(SimpleStringProperty ciOPasaporte) {
        this.ciOPasaporte = ciOPasaporte;
    }

    public SimpleBooleanProperty getGenero() {
        return genero;
    }

    public void setGenero(SimpleBooleanProperty genero) {
        this.genero = genero;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return nombre.get() + " " + primerApellido.get() + " " + segundoApellido.get();
    }
}
