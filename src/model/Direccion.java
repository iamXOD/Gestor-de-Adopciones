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
    
    public int getDireccion_id() {
        return direccion_id.get();
    }
    
    public SimpleIntegerProperty direccion_idProperty() {
        return direccion_id;
    }
    
    public void setDireccion_id(int direccion_id) {
        this.direccion_id = new SimpleIntegerProperty(direccion_id);
    }
    
    public String getCallePrincipal() {
        return callePrincipal.get();
    }
    
    public SimpleStringProperty callePrincipalProperty() {
        return callePrincipal;
    }
    
    public void setCallePrincipal(String callePrincipal) {
        this.callePrincipal = new SimpleStringProperty(callePrincipal);
    }
    
    public String getEntreCalle() {
        return entreCalle.get();
    }
    
    public SimpleStringProperty entreCalleProperty() {
        return entreCalle;
    }
    
    public void setEntreCalle(String entreCalle) {
        this.entreCalle = new SimpleStringProperty(entreCalle);
    }
    
    public String getyCalle() {
        return yCalle.get();
    }
    
    public SimpleStringProperty yCalleProperty() {
        return yCalle;
    }
    
    public void setyCalle(String yCalle) {
        this.yCalle = new SimpleStringProperty(yCalle);
    }
    
    public int getNo() {
        return no.get();
    }
    
    public SimpleIntegerProperty noProperty() {
        return no;
    }
    
    public void setNo(int no) {
        this.no = new SimpleIntegerProperty(no);
    }
    
    public String getLocalidad() {
        return localidad.get();
    }
    
    public SimpleStringProperty localidadProperty() {
        return localidad;
    }
    
    public void setLocalidad(String localidad) {
        this.localidad = new SimpleStringProperty(localidad);
    }
    
    public String getMunicipio() {
        return municipio.get();
    }
    
    public SimpleStringProperty municipioProperty() {
        return municipio;
    }
    
    public void setMunicipio(String municipio) {
        this.municipio = new SimpleStringProperty(municipio);
    }
    
    public String getProvincia() {
        return provincia.get();
    }
    
    public SimpleStringProperty provinciaProperty() {
        return provincia;
    }
    
    public void setProvincia(String provincia) {
        this.provincia = new SimpleStringProperty(provincia);
    }
    
    @Override
    public String toString() {
        return this.callePrincipal.get() + " entre " + this.entreCalle.get() + " y "
                + this.yCalle.get() + ", no. " + this.no.get() + ", "
                + this.localidad.get() + ", " + this.municipio.get() + ", " + this.provincia.get();
    }
}
