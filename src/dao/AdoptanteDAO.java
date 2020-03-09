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
                a.setAdoptante_id(rs.getInt("adoptante_id"));
                a.setNombre(rs.getString("nombre"));
                a.setPrimerApellido(rs.getString("primerApellido"));
                a.setSegundoApellido(rs.getString("segundoApellido"));
                a.setCiOPasaporte(rs.getString("ciOPasaporte"));
                a.setGenero(rs.getBoolean("genero"));
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
                a.setAdoptante_id(rs.getInt("adoptante_id"));
                a.setNombre(rs.getString("nombre"));
                a.setPrimerApellido(rs.getString("primerApellido"));
                a.setSegundoApellido(rs.getString("segundoApellido"));
                a.setCiOPasaporte(rs.getString("ciOPasaporte"));
                a.setGenero(rs.getBoolean("genero"));
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
    public int add(Adoptante o) {
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        DireccionDAO direcciones = new DireccionDAO();
        try {
            con = Connector.connect();
            pst = con.prepareStatement("INSERT INTO Adoptante"
                    + " (nombre, primerApellido,"
                    + " segundoApellido, ciOPasaporte, genero, direccion_id)"
                    + "VALUES (?, ?, ?, ?, ?, ?)");
            pst.setString(1, o.getNombre());
            pst.setString(2, o.getPrimerApellido());
            pst.setString(3, o.getSegundoApellido());
            pst.setString(4, o.getCiOPasaporte());
            pst.setBoolean(5, o.getGenero());
            int direccion_id = direcciones.add(o.getDireccion());
            pst.setInt(6, direccion_id);
            pst.executeUpdate();
            pst = con.prepareStatement("SELECT seq FROM sqlite_sequence WHERE name = \"Adoptante\";");
            rs = pst.executeQuery();
            int rowid = rs.getInt("seq");
            pst.close();
            con.close();
            return rowid;
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    @Override
    public int update(Adoptante o) {
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
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
            pst.setString(1, o.getNombre());
            pst.setString(2, o.getPrimerApellido());
            pst.setString(3, o.getSegundoApellido());
            pst.setString(4, o.getCiOPasaporte());
            pst.setBoolean(5, o.getGenero());
            pst.setInt(6, o.getDireccion().getDireccion_id());
            pst.setInt(7, o.getAdoptante_id());
            pst.executeUpdate();
            pst = con.prepareStatement("SELECT seq FROM sqlite_sequence WHERE name = \"Adoptante\";");
            rs = pst.executeQuery();
            int rowid = rs.getInt("seq");
            pst.close();
            con.close();
            return rowid;
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return 0;
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
