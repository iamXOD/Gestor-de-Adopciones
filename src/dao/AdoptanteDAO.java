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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Adoptante;

/**
 *
 * @author XOD
 */
public class AdoptanteDAO implements DAOInterface<Adoptante> {

    @Override
    public Adoptante get(int ID) {
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        Adoptante a = Adoptante.EMPTY;
        DireccionDAO d = new DireccionDAO();
        try {
            con = Connector.connect();
            pst = con.prepareStatement("SELECT * FROM Adoptante WHERE adoptante_id = ? LIMIT 1");
            pst.setInt(1, ID);
            rs = pst.executeQuery();
            while (rs.next()) {
                a = new Adoptante();
                a.setAdoptante_id(new SimpleIntegerProperty(rs.getInt("adoptante_id")));
                a.setNombre(new SimpleStringProperty(rs.getString("nombre")));
                a.setPrimerApellido(new SimpleStringProperty(rs.getString("primerApellido")));
                a.setSegundoApellido(new SimpleStringProperty(rs.getString("segundoApellido")));
                a.setCiOPasaporte(new SimpleStringProperty(rs.getString("ciOPasaporte")));
                a.setGenero(new SimpleBooleanProperty(rs.getBoolean("genero")));
                a.setDireccion(d.get(rs.getInt("direccion_id")));
            }
            pst.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return a;
    }

    @Override
    public ObservableList<Adoptante> getAll() {
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        List aux = new ArrayList<Adoptante>();
        DireccionDAO d = new DireccionDAO();
        try {
            con = Connector.connect();
            pst = con.prepareStatement("SELECT * FROM Adoptante ORDER BY adoptante_id");
            rs = pst.executeQuery();
            while (rs.next()) {
                Adoptante a = new Adoptante();
                a.setAdoptante_id(new SimpleIntegerProperty(rs.getInt("adoptante_id")));
                a.setNombre(new SimpleStringProperty(rs.getString("nombre")));
                a.setPrimerApellido(new SimpleStringProperty(rs.getString("primerApellido")));
                a.setSegundoApellido(new SimpleStringProperty(rs.getString("segundoApellido")));
                a.setCiOPasaporte(new SimpleStringProperty(rs.getString("ciOPasaporte")));
                a.setGenero(new SimpleBooleanProperty(rs.getBoolean("genero")));
                a.setDireccion(d.get(rs.getInt("direccion_id")));
                aux.add(a);
            }
            pst.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return FXCollections.observableArrayList(aux);
    }

    @Override
    public boolean add(Adoptante o) {
        Connection con;
        PreparedStatement pst;
        try {
            con = Connector.connect();
            pst = con.prepareStatement("INSERT INTO Adoptante"
                    + " (adoptante_id, nombre, primerApellido,"
                    + " segundoApellido, ciOPasaporte, genero, direccion_id)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            pst.setInt(1, o.getAdoptante_id().get());
            pst.setString(2, o.getNombre().get());
            pst.setString(3, o.getPrimerApellido().get());
            pst.setString(4, o.getSegundoApellido().get());
            pst.setString(5, o.getCiOPasaporte().get());
            pst.setBoolean(6, o.getGenero().get());
            pst.setInt(7, o.getDireccion().getDireccion_id().get());
            pst.executeUpdate();
            pst.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Adoptante o) {
        Connection con;
        PreparedStatement pst;
        try {
            con = Connector.connect();
            pst = con.prepareStatement("UPDATE Adoptante SET"
                    + " nombre = ?,"
                    + " primerApellido = ?,"
                    + " segundoApellido = ?,"
                    + " ciOPasaporte = ?,"
                    + " genero = ?,"
                    + " direccion_id = ?"
                    + " WHERE adoptante_id = ?");
            pst.setString(1, o.getNombre().get());
            pst.setString(2, o.getPrimerApellido().get());
            pst.setString(3, o.getSegundoApellido().get());
            pst.setString(4, o.getCiOPasaporte().get());
            pst.setBoolean(5, o.getGenero().get());
            pst.setInt(6, o.getDireccion().getDireccion_id().get());
            pst.setInt(7, o.getAdoptante_id().get());
            pst.executeUpdate();
            pst.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            pst = con.prepareStatement("DELETE FROM Adoptante WHERE adoptante_id = ?");
            pst.setInt(1, ID);
            pst.executeUpdate();
            pst.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
