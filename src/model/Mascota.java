/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author HAROLD
 */
public class Mascota {

    public static Mascota EMPTY = new Mascota();

    private SimpleIntegerProperty mascota_id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty raza;
    private SimpleStringProperty color;
    private SimpleIntegerProperty edad;
    private SimpleBooleanProperty genero;
    private SimpleDoubleProperty peso;
    private SimpleStringProperty ultimaDesparacitacion;
    private SimpleStringProperty ultimaVacunacion;
    private SimpleStringProperty fechaAdopcion;
    private Adoptante adoptante;

    public Mascota(int mascota_id, String nombre, String raza, String color, int edad, boolean genero, double peso, String ultimaDesparacitacion, String ultimaVacunacion, String fechaAdopcion, Adoptante adoptante) {
        this.mascota_id = new SimpleIntegerProperty(mascota_id);
        this.nombre = new SimpleStringProperty(nombre);
        this.raza = new SimpleStringProperty(raza);
        this.color = new SimpleStringProperty(color);
        this.edad = new SimpleIntegerProperty(edad);
        this.genero = new SimpleBooleanProperty(genero);
        this.peso = new SimpleDoubleProperty(peso);
        this.ultimaDesparacitacion = new SimpleStringProperty(ultimaDesparacitacion);
        this.ultimaVacunacion = new SimpleStringProperty(ultimaVacunacion);
        this.fechaAdopcion = new SimpleStringProperty(fechaAdopcion);
        this.adoptante = adoptante;
    }

    public Mascota() {
    }

    public int getMascota_id() {
        return mascota_id.get();
    }

    public SimpleIntegerProperty mascota_idProperty() {
        return mascota_id;
    }

    public void setMascota_id(int mascota_id) {
        this.mascota_id = new SimpleIntegerProperty(mascota_id);
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

    public String getRaza() {
        return raza.get();
    }

    public SimpleStringProperty razaProperty() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = new SimpleStringProperty(raza);
    }

    public String getColor() {
        return color.get();
    }

    public SimpleStringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color = new SimpleStringProperty(color);
    }

    public int getEdad() {
        return edad.get();
    }

    public SimpleIntegerProperty edadProperty() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = new SimpleIntegerProperty(edad);
    }

    public Boolean getGenero() {
        return genero.get();
    }

    public SimpleStringProperty generoProperty() {
        String aux = genero.get() ? "Masculino" : "Femenino";
        return new SimpleStringProperty(aux);
    }

    public void setGenero(boolean genero) {
        this.genero = new SimpleBooleanProperty(genero);
    }

    public Double getPeso() {
        return peso.get();
    }

    public SimpleDoubleProperty pesoProperty() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = new SimpleDoubleProperty(peso);
    }

    public String getUltimaDesparacitacion() {
        return ultimaDesparacitacion.get();
    }

    public SimpleStringProperty ultimaDesparacitacionProperty() {
        return ultimaDesparacitacion;
    }

    public void setUltimaDesparacitacion(String ultimaDesparacitacion) {
        this.ultimaDesparacitacion = new SimpleStringProperty(ultimaDesparacitacion);
    }

    public String getUltimaVacunacion() {
        return ultimaVacunacion.get();
    }

    public SimpleStringProperty ultimaVacunacionProperty() {
        return ultimaVacunacion;
    }

    public void setUltimaVacunacion(String ultimaVacunacion) {
        this.ultimaVacunacion = new SimpleStringProperty(ultimaVacunacion);
    }

    public String getFechaAdopcion() {
        return fechaAdopcion.get();
    }

    public SimpleStringProperty fechaAdopcionProperty() {
        return fechaAdopcion;
    }

    public void setFechaAdopcion(String fechaAdopcion) {
        this.fechaAdopcion = new SimpleStringProperty(fechaAdopcion);
    }

    public Adoptante getAdoptante() {
        return adoptante;
    }

    public SimpleStringProperty adoptanteProperty() {
        return new SimpleStringProperty(adoptante.toString());
    }

    public void setAdoptante(Adoptante adoptante) {
        this.adoptante = adoptante;
    }

    @Override
    public String toString() {
        return this.nombre.get();
    }
}
