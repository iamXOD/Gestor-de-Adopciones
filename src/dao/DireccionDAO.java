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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Direccion;

/**
 *
 * @author XOD
 */
public class DireccionDAO implements DAOInterface<Direccion> {

    @Override
    public Direccion get(int ID) {
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        Direccion d = Direccion.EMPTY;
        try {
            con = Connector.connect();
            pst = con.prepareStatement(
                    "SELECT * FROM Direccion WHERE direccion_id = ? ORDER BY direccion_id LIMIT 1");
            pst.setInt(1, ID);
            rs = pst.executeQuery();
            while (rs.next()) {
                d = new Direccion();
                d.setDireccion_id(new SimpleIntegerProperty(rs.getInt("direccion_id")));
                d.setCallePrincipal(new SimpleStringProperty(rs.getString("callePrincipal")));
                d.setEntreCalle(new SimpleStringProperty(rs.getString("entreCalle")));
                d.setyCalle(new SimpleStringProperty(rs.getString("yCalle")));
                d.setNo(new SimpleIntegerProperty(rs.getInt("no")));
                d.setLocalidad(new SimpleStringProperty(rs.getString("localidad")));
                d.setMunicipio(new SimpleStringProperty(rs.getString("municipio")));
                d.setProvincia(new SimpleStringProperty(rs.getString("provincia")));
            }
            pst.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DireccionDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return d;
    }

    @Override
    public ObservableList<Direccion> getAll() {
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        List<Direccion> aux = new ArrayList();
        try {
            con = Connector.connect();
            pst = con.prepareStatement(
                    "SELECT * FROM Direccion ORDER BY direccion_id");
            rs = pst.executeQuery();
            while (rs.next()) {
                Direccion d = new Direccion();
                d.setDireccion_id(new SimpleIntegerProperty(rs.getInt("direccion_id")));
                d.setCallePrincipal(new SimpleStringProperty(rs.getString("callePrincipal")));
                d.setEntreCalle(new SimpleStringProperty(rs.getString("entreCalle")));
                d.setyCalle(new SimpleStringProperty(rs.getString("yCalle")));
                d.setNo(new SimpleIntegerProperty(rs.getInt("no")));
                d.setLocalidad(new SimpleStringProperty(rs.getString("localidad")));
                d.setMunicipio(new SimpleStringProperty(rs.getString("municipio")));
                d.setProvincia(new SimpleStringProperty(rs.getString("provincia")));
                aux.add(d);
            }
            pst.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DireccionDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return FXCollections.observableArrayList(aux);
    }

    @Override
    public boolean add(Direccion o) {
        Connection con;
        PreparedStatement pst;
        try {
            con = Connector.connect();
            pst = con.prepareStatement(
                    "INSERT INTO Direccion"
                    + " (direccion_id, callePrincipal,"
                    + " entreCalle, yCalle, no, localidad,"
                    + " municipio, provincia) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
            pst.setInt(1, o.getDireccion_id().get());
            pst.setString(2, o.getCallePrincipal().get());
            pst.setString(3, o.getEntreCalle().get());
            pst.setString(4, o.getyCalle().get());
            pst.setInt(5, o.getNo().get());
            pst.setString(6, o.getLocalidad().get());
            pst.setString(7, o.getMunicipio().get());
            pst.setString(8, o.getProvincia().get());
            pst.executeUpdate();
            con.close();
            pst.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DireccionDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Direccion o) {
        Connection con;
        PreparedStatement pst;
        try {
            con = Connector.connect();
            pst = con.prepareStatement("UPDATE Direccion SET"
                    + " callePrincipal = ?,"
                    + " entreCalle = ?,"
                    + " yCalle = ?,"
                    + " no = ?,"
                    + " localidad = ?,"
                    + " municipio = ?,"
                    + " provincia = ?"
                    + " WHERE direccion_id = ?");
            pst.setString(1, o.getCallePrincipal().get());
            pst.setString(2, o.getEntreCalle().get());
            pst.setString(3, o.getyCalle().get());
            pst.setInt(4, o.getNo().get());
            pst.setString(5, o.getLocalidad().get());
            pst.setString(6, o.getMunicipio().get());
            pst.setString(7, o.getProvincia().get());
            pst.setInt(8, o.getDireccion_id().get());
            pst.executeUpdate();
            pst.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DireccionDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            pst = con.prepareStatement("DELETE FROM Direccion WHERE direccion_id = ?");
            pst.setInt(1, ID);
            pst.executeUpdate();
            pst.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DireccionDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
