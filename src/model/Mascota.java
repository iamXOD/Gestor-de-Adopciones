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

    public SimpleIntegerProperty getMascota_id() {
        return mascota_id;
    }

    public void setMascota_id(SimpleIntegerProperty mascota_id) {
        this.mascota_id = mascota_id;
    }

    public Mascota() {
    }

    public SimpleStringProperty getNombre() {
        return nombre;
    }

    public void setNombre(SimpleStringProperty nombre) {
        this.nombre = nombre;
    }

    public SimpleStringProperty getRaza() {
        return raza;
    }

    public void setRaza(SimpleStringProperty raza) {
        this.raza = raza;
    }

    public SimpleStringProperty getColor() {
        return color;
    }

    public void setColor(SimpleStringProperty color) {
        this.color = color;
    }

    public SimpleIntegerProperty getEdad() {
        return edad;
    }

    public void setEdad(SimpleIntegerProperty edad) {
        this.edad = edad;
    }

    public SimpleBooleanProperty getGenero() {
        return genero;
    }

    public void setGenero(SimpleBooleanProperty genero) {
        this.genero = genero;
    }

    public SimpleDoubleProperty getPeso() {
        return peso;
    }

    public void setPeso(SimpleDoubleProperty peso) {
        this.peso = peso;
    }

    public SimpleStringProperty getUltimaDesparacitacion() {
        return ultimaDesparacitacion;
    }

    public void setUltimaDesparacitacion(SimpleStringProperty ultimaDesparacitacion) {
        this.ultimaDesparacitacion = ultimaDesparacitacion;
    }

    public SimpleStringProperty getUltimaVacunacion() {
        return ultimaVacunacion;
    }

    public void setUltimaVacunacion(SimpleStringProperty ultimaVacunacion) {
        this.ultimaVacunacion = ultimaVacunacion;
    }

    public SimpleStringProperty getFechaAdopcion() {
        return fechaAdopcion;
    }

    public void setFechaAdopcion(SimpleStringProperty fechaAdopcion) {
        this.fechaAdopcion = fechaAdopcion;
    }

    public Adoptante getAdoptante() {
        return adoptante;
    }

    public void setAdoptante(Adoptante adoptante) {
        this.adoptante = adoptante;
    }

    @Override
    public String toString() {
        return this.nombre.get();
    }
}
