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

    public int getAdoptante_id() {
        return adoptante_id.get();
    }

    public SimpleIntegerProperty adoptante_idProperty() {
        return adoptante_id;
    }

    public void setAdoptante_id(int adoptante_id) {
        this.adoptante_id = new SimpleIntegerProperty(adoptante_id);
    }

    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);
    }

    public String getPrimerApellido() {
        return primerApellido.get();
    }

    public SimpleStringProperty primerApellidoProperty() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = new SimpleStringProperty(primerApellido);
    }

    public String getSegundoApellido() {
        return segundoApellido.get();
    }

    public SimpleStringProperty segundoApellidoProperty() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = new SimpleStringProperty(segundoApellido);
    }

    public String getCiOPasaporte() {
        return ciOPasaporte.get();
    }

    public SimpleStringProperty ciOPasaporteProperty() {
        return ciOPasaporte;
    }

    public void setCiOPasaporte(String ciOPasaporte) {
        this.ciOPasaporte = new SimpleStringProperty(ciOPasaporte);
    }

    public boolean getGenero() {
        return genero.get();
    }

    public SimpleStringProperty generoProperty() {
        String aux = genero.get() ? "Masculino" : "Femenino";
        return new SimpleStringProperty(aux);
    }

    public void setGenero(boolean genero) {
        this.genero = new SimpleBooleanProperty(genero);
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public SimpleStringProperty direccionProperty() {
        return new SimpleStringProperty(direccion.toString());
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return nombre.get() + " " + primerApellido.get() + " " + segundoApellido.get();
    }
}
