/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import auxiliar.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Mascota;

/**
 *
 * @author XOD
 */
public class MascotaDAO implements DAOInterface<Mascota> {

    @Override
    public Mascota get(int ID) {
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        Mascota m = Mascota.EMPTY;
        AdoptanteDAO a = new AdoptanteDAO();
        try {
            con = Connector.connect();
            pst = con.prepareStatement("SELECT * FROM Mascota WHERE mascota_id = ? LIMIT 1");
            pst.setInt(1, ID);
            rs = pst.executeQuery();
            while (rs.next()) {
                m = new Mascota();
                m.setMascota_id(new SimpleIntegerProperty(rs.getInt("mascota_id")));
                m.setNombre(new SimpleStringProperty(rs.getString("nombre")));
                m.setRaza(new SimpleStringProperty(rs.getString("raza")));
                m.setColor(new SimpleStringProperty(rs.getString("color")));
                m.setEdad(new SimpleIntegerProperty(rs.getInt("edad")));
                m.setGenero(new SimpleBooleanProperty(rs.getBoolean("genero")));
                m.setPeso(new SimpleDoubleProperty(rs.getDouble("peso")));
                m.setUltimaDesparacitacion(new SimpleStringProperty(rs.getString("ultimaDesparacitacion")));
                m.setUltimaVacunacion(new SimpleStringProperty(rs.getString("ultimaVacunacion")));
                m.setFechaAdopcion(new SimpleStringProperty(rs.getString("fechaAdopcion")));
                m.setAdoptante(a.get(rs.getInt("adoptante_id")));
            }
            pst.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(MascotaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return m;
    }

    @Override
    public ObservableList<Mascota> getAll() {
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        List aux = new ArrayList<Mascota>();
        AdoptanteDAO a = new AdoptanteDAO();
        try {
            con = Connector.connect();
            pst = con.prepareStatement("SELECT * FROM Mascota ORDER BY mascota_id");
            rs = pst.executeQuery();
            while (rs.next()) {
                Mascota m = new Mascota();
                m.setAdoptante(a.get(rs.getInt("adoptante_id")));
                m.setMascota_id(new SimpleIntegerProperty(rs.getInt("mascota_id")));
                m.setNombre(new SimpleStringProperty(rs.getString("nombre")));
                m.setRaza(new SimpleStringProperty(rs.getString("raza")));
                m.setColor(new SimpleStringProperty(rs.getString("color")));
                m.setEdad(new SimpleIntegerProperty(rs.getInt("edad")));
                m.setGenero(new SimpleBooleanProperty(rs.getBoolean("genero")));
                m.setPeso(new SimpleDoubleProperty(rs.getDouble("peso")));
                m.setUltimaDesparacitacion(new SimpleStringProperty(rs.getString("ultimaDesparacitacion")));
                m.setUltimaVacunacion(new SimpleStringProperty(rs.getString("ultimaVacunacion")));
                m.setFechaAdopcion(new SimpleStringProperty(rs.getString("fechaAdopcion")));
                aux.add(m);
            }
            pst.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(MascotaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return FXCollections.observableArrayList(aux);
    }

    @Override
    public boolean add(Mascota o) {
        Connection con;
        PreparedStatement pst;
        try {
            con = Connector.connect();
            pst = con.prepareStatement("INSERT INTO Mascota"
                    + " (mascota_id, adoptante_id, nombre,"
                    + " raza, color, edad, genero, peso,"
                    + " ultimaDesparacitacion, ultimaVacunacion,"
                    + " fechaAdopcion) VALUES"
                    + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setInt(1, o.getMascota_id().get());
            pst.setInt(2, o.getAdoptante().getAdoptante_id().get());
            pst.setString(3, o.getNombre().get());
            pst.setString(4, o.getRaza().get());
            pst.setString(5, o.getColor().get());
            pst.setInt(6, o.getEdad().get());
            pst.setBoolean(7, o.getGenero().get());
            pst.setDouble(8, o.getPeso().get());
            pst.setString(9, o.getUltimaDesparacitacion().get());
            pst.setString(10, o.getUltimaVacunacion().get());
            pst.setString(11, o.getFechaAdopcion().get());
            pst.executeUpdate();
            pst.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MascotaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Mascota o) {
        Connection con;
        PreparedStatement pst;
        try {
            con = Connector.connect();
            pst = con.prepareStatement("UPDATE Mascota SET"
                    + " adoptante_id = ?,"
                    + " nombre = ?,"
                    + " raza = ?,"
                    + " color = ?,"
                    + " edad = ?,"
                    + " genero = ?,"
                    + " peso = ?,"
                    + " ultimaDesparacitacion = ?,"
                    + " ultimaVacunacion = ?,"
                    + " fechaAdopcion = ?"
                    + " WHERE mascota_id = ?");
            pst.setInt(1, o.getAdoptante().getAdoptante_id().get());
            pst.setString(2, o.getNombre().get());
            pst.setString(3, o.getRaza().get());
            pst.setString(4, o.getColor().get());
            pst.setInt(5, o.getEdad().get());
            pst.setBoolean(6, o.getGenero().get());
            pst.setDouble(7, o.getPeso().get());
            pst.setString(8, o.getUltimaDesparacitacion().get());
            pst.setString(9, o.getUltimaVacunacion().get());
            pst.setString(10, o.getFechaAdopcion().get());
            pst.setInt(11, o.getMascota_id().get());
            pst.executeUpdate();
            pst.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MascotaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int ID) {
        Connection con;
        PreparedStatement pst;
        try {
            con = Connector.connect();
            pst = con.prepareStatement("DELETE FROM Mascota WHERE mascota_id = ?");
            pst.setInt(1, ID);
            pst.executeUpdate();
            pst.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MascotaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
