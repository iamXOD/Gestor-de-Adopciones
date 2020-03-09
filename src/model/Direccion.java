/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.sun.javafx.collections.ElementObservableListDecorator;
import java.util.ArrayList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

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

    private static final String[][] municipios = {{"Consolación del Sur", "Guane", "La Palma", "Los Palacios", "Mantua", "Minas de Matahambre",
        "Pinar del Río", "San Juan y Martínez", "San Luis", "Sandino", "Viñales"}, {"Alquízar", "Artemisa", "Bauta", "Caimito", "Guanajay", "Güira de Melena",
        "Mariel", "San Antonio de los Baños", "Bahía Honda", "Candelaria", "San Cristóbal"}, {"Batabanó", "Bejucal", "Güines", "Jaruco", "Madruga", "Melena del Sur", "Nueva Paz",
        "Quivicán", "San José de las Lajas", "San Nicolás de Bari", "Santa Cruz del Norte"}, {"Arroyo Naranjo", "Boyeros", "Centro Habana", "Cerro", "Cotorro", "Diez de Octubre",
        "Guanabacoa", "Habana del Este", "Habana Vieja", "La Lisa", "Marianao",
        "Playa", "Plaza de la Revolución", "Regla", "San Miguel del Padrón"}, {"Calimete", "Cárdenas", "Ciénaga de Zapata", "Colón", "Jagüey Grande", "Jovellanos",
        "Limonar", "Los Arabos", "Martí", "Matanzas", "Pedro Betancourt", "Perico", "Unión de Reyes"}, {"Abreus", "Aguada de Pasajeros", "Cienfuegos", "Cruces", "Cumanayagua",
        "Palmira", "Rodas", "Santa Isabel de las Lajas"}, {"Caibarien", "Camajuaní", "Cifuentes", "Corralillo", "Encrucijada", "Manicaragua", "Placetas",
        "Quemado de Güines", "Ranchuelo", "Remedios", "Sagua la Grande", "Santa Clara", "Santo Domingo"}, {"Cabaiguán", "Fomento", "Jatibonico", "La Sierpe", "Sancti Spíritus", "Taguasco", "Trinidad", "Yaguajay"}, {"Ciro Redondo", "Baraguá", "Bolivia", "Chambas", "Ciego de Ávila",
        "Florencia", "Majagua", "Morón", "Primero de Enero", "Venezuela"}, {"Camagüey", "Carlos Manuel de Céspedes", "Esmeralda", "Florida", "Guaimaro",
        "Jimagüayú", "Minas", "Najasa", "Nuevitas", "Santa Cruz del Sur",
        "Sibanicú", "Sierra de Cubitas", "Vertientes"}, {"Amancio Rodríguez", "Colombia", "Jesús Menéndez", "Jobabo",
        "Las Tunas", "Majibacoa", "Manatí", "Puerto Padre"}, {"Antilla", "Báguanos", "Banes", "Cacocum", "Calixto García", "Cueto", "Frank País",
        "Gibara", "Holguín", "Mayarí", "Moa", "Rafael Freyre", "Sagua de Tánamo", "Urbano Noris"}, {"Bartolomé Masó", "Bayamo", "Buey Arriba", "Campechuela", "Cauto Cristo", "Guisa",
        "Jiguaní", "Manzanillo", "Media Luna", "Niquero", "Pilón", "Río Cauto", "Yara"}, {"Contramaestre", "Guamá", "Julio Antonio Mella", "Palma Soriano", "San Luis",
        "Santiago de Cuba", "Segundo Frente", "Songo la Maya", "Tercer Frente"}, {"Baracoa", "Caimanera", "El Salvador", "Guantánamo", "Imías", "Maisí",
        "Manuel Tames", "Niceto Pérez", "San Antonio del Sur", "Yateras"}, {"Isla de la Juventud"}};

    private static final String[] provincias = {"Pinar del Rio", "Artemisa", "Mayabeque",
        "La Habana", "Matanzas", "Cienfuegos", "Villa Clara", "Sancti Spiritus",
        "Ciego de Avila", "Camaguey", "Las Tunas", "Holguin", "Granma",
        "Santiago de Cuba", "Guantanamo", "Isla de la Juventud"};

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

    public static String[][] getMunicipios() {
        return municipios;
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

    public static String[] getProvincias() {
        return provincias;
    }

    @Override
    public String toString() {
        return this.callePrincipal.get() + " entre " + this.entreCalle.get() + " y "
                + this.yCalle.get() + ", no. " + this.no.get() + ", "
                + this.localidad.get() + ", " + this.municipio.get() + ", " + this.provincia.get();
    }
}
