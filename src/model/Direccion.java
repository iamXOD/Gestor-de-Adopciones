/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author HAROLD
 */
public class Direccion {

    public static Direccion EMPTY = new Direccion();

    private SimpleIntegerProperty direccion_id;
    private SimpleStringProperty callePrincipal;
    private SimpleStringProperty entreCalle;
    private SimpleStringProperty yCalle;
    private SimpleIntegerProperty no;
    private SimpleStringProperty localidad;
    private SimpleStringProperty municipio;
    private SimpleStringProperty provincia;

    public Direccion(int direccion_id, String callePrincipal, String entreCalle, String yCalle, int no, String localidad, String municipio, String provincia) {
        this.direccion_id = new SimpleIntegerProperty(direccion_id);
        this.callePrincipal = new SimpleStringProperty(callePrincipal);
        this.entreCalle = new SimpleStringProperty(entreCalle);
        this.yCalle = new SimpleStringProperty(yCalle);
        this.no = new SimpleIntegerProperty(no);
        this.localidad = new SimpleStringProperty(localidad);
        this.municipio = new SimpleStringProperty(municipio);
        this.provincia = new SimpleStringProperty(provincia);
    }

    public Direccion() {
    }

    public SimpleIntegerProperty getDireccion_id() {
        return direccion_id;
    }

    public void setDireccion_id(SimpleIntegerProperty direccion_id) {
        this.direccion_id = direccion_id;
    }

    public SimpleStringProperty getCallePrincipal() {
        return callePrincipal;
    }

    public void setCallePrincipal(SimpleStringProperty callePrincipal) {
        this.callePrincipal = callePrincipal;
    }

    public SimpleStringProperty getEntreCalle() {
        return entreCalle;
    }

    public void setEntreCalle(SimpleStringProperty entreCalle) {
        this.entreCalle = entreCalle;
    }

    public SimpleStringProperty getyCalle() {
        return yCalle;
    }

    public void setyCalle(SimpleStringProperty yCalle) {
        this.yCalle = yCalle;
    }

    public SimpleIntegerProperty getNo() {
        return no;
    }

    public void setNo(SimpleIntegerProperty no) {
        this.no = no;
    }

    public SimpleStringProperty getLocalidad() {
        return localidad;
    }

    public void setLocalidad(SimpleStringProperty localidad) {
        this.localidad = localidad;
    }

    public SimpleStringProperty getMunicipio() {
        return municipio;
    }

    public void setMunicipio(SimpleStringProperty municipio) {
        this.municipio = municipio;
    }

    public SimpleStringProperty getProvincia() {
        return provincia;
    }

    public void setProvincia(SimpleStringProperty provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return this.callePrincipal.get() + " entre " + this.entreCalle.get() + " y "
                + this.yCalle.get() + ", no. " + this.no.get() + ", "
                + this.localidad.get() + ", " + this.municipio.get() + ", " + this.provincia.get();
    }
}
