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
import model.Direccion;

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
                Direccion direccion = new Direccion();
                direccion.setCallePrincipal(rs.getString("callePrincipal"));
                direccion.setEntreCalle(rs.getString("entreCalle"));
                direccion.setyCalle(rs.getString("yCalle"));
                direccion.setNo(rs.getString("no"));
                direccion.setLocalidad(rs.getString("localidad"));
                direccion.setMunicipio(rs.getString("municipio"));
                direccion.setProvincia(rs.getString("provincia"));
                a.setDireccion(direccion);
            }
            pst.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    @Override
    public ObservableList<Adoptante> search(String param) {
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        List aux = new ArrayList<Adoptante>();
        try {
            con = Connector.connect();
            pst = con.prepareStatement("SELECT * FROM Adoptante"
                    + " WHERE (nombre like \"%" + param + "%\""
                    + " OR primerApellido like \"%" + param + "%\""
                    + " OR segundoApellido like \"%" + param + "%\""
                    + " OR ciOPasaporte like \"%" + param + "%\""
                    + " OR genero like \"%" + param + "%\""
                    + " OR callePrincipal like \"%" + param + "%\""
                    + " OR entreCalle like \"%" + param + "%\""
                    + " OR yCalle like \"%" + param + "%\""
                    + " OR no like \"%" + param + "%\""
                    + " OR localidad like \"%" + param + "%\""
                    + " OR municipio like \"%" + param + "%\""
                    + " OR provincia like \"%" + param + "%\")"
                    + " ORDER BY adoptante_id");
            rs = pst.executeQuery();
            while (rs.next()) {
                Adoptante a = new Adoptante();
                a.setAdoptante_id(rs.getInt("adoptante_id"));
                a.setNombre(rs.getString("nombre"));
                a.setPrimerApellido(rs.getString("primerApellido"));
                a.setSegundoApellido(rs.getString("segundoApellido"));
                a.setCiOPasaporte(rs.getString("ciOPasaporte"));
                a.setGenero(rs.getBoolean("genero"));
                Direccion direccion = new Direccion();
                direccion.setCallePrincipal(rs.getString("callePrincipal"));
                direccion.setEntreCalle(rs.getString("entreCalle"));
                direccion.setyCalle(rs.getString("yCalle"));
                direccion.setNo(rs.getString("no"));
                direccion.setLocalidad(rs.getString("localidad"));
                direccion.setMunicipio(rs.getString("municipio"));
                direccion.setProvincia(rs.getString("provincia"));
                a.setDireccion(direccion);
                aux.add(a);
            }
            pst.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FXCollections.observableArrayList(aux);

    }

    @Override
    public ObservableList<Adoptante> getAll() {
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        List aux = new ArrayList<Adoptante>();
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
                Direccion direccion = new Direccion();
                direccion.setCallePrincipal(rs.getString("callePrincipal"));
                direccion.setEntreCalle(rs.getString("entreCalle"));
                direccion.setyCalle(rs.getString("yCalle"));
                direccion.setNo(rs.getString("no"));
                direccion.setLocalidad(rs.getString("localidad"));
                direccion.setMunicipio(rs.getString("municipio"));
                direccion.setProvincia(rs.getString("provincia"));
                a.setDireccion(direccion);
                aux.add(a);
            }
            pst.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FXCollections.observableArrayList(aux);
    }

    @Override
    public int add(Adoptante o) {
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        try {
            con = Connector.connect();
            pst = con.prepareStatement("INSERT OR REPLACE INTO Adoptante"
                    + "(nombre, primerApellido, segundoApellido,"
                    + " ciOPasaporte, genero, callePrincipal, entreCalle, yCalle,"
                    + " no, localidad, municipio, provincia)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, o.getNombre());
            pst.setString(2, o.getPrimerApellido());
            pst.setString(3, o.getSegundoApellido());
            pst.setString(4, o.getCiOPasaporte());
            pst.setBoolean(5, o.getGenero());
            Direccion direccion = o.getDireccion();
            pst.setString(6, direccion.getCallePrincipal());
            pst.setString(7, direccion.getEntreCalle());
            pst.setString(8, direccion.getyCalle());
            pst.setString(9, direccion.getNo());
            pst.setString(10, direccion.getLocalidad());
            pst.setString(11, direccion.getMunicipio());
            pst.setString(12, direccion.getProvincia());
            pst.executeUpdate();
            pst = con.prepareStatement("SELECT seq FROM sqlite_sequence WHERE name = \"Adoptante\";");
            rs = pst.executeQuery();
            int rowid = rs.getInt("seq");
            pst.close();
            con.close();
            return rowid;
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            pst = con.prepareStatement("PRAGMA foreign_keys = ON;");
            pst.executeUpdate();
            pst = con.prepareStatement("UPDATE OR IGNORE Adoptante SET"
                    + " nombre = ?,"
                    + " primerApellido = ?,"
                    + " segundoApellido = ?,"
                    + " ciOPasaporte = ?,"
                    + " genero = ?,"
                    + " callePrincipal = ?,"
                    + "	entreCalle = ?,"
                    + "	yCalle = ?,"
                    + "	no = ?,"
                    + "	localidad = ?,"
                    + "	municipio = ?,"
                    + "	provincia = ?"
                    + " WHERE adoptante_id = ?");
            pst.setString(1, o.getNombre());
            pst.setString(2, o.getPrimerApellido());
            pst.setString(3, o.getSegundoApellido());
            pst.setString(4, o.getCiOPasaporte());
            pst.setBoolean(5, o.getGenero());
            Direccion direccion = o.getDireccion();
            pst.setString(6, direccion.getCallePrincipal());
            pst.setString(7, direccion.getEntreCalle());
            pst.setString(8, direccion.getyCalle());
            pst.setString(9, direccion.getNo());
            pst.setString(10, direccion.getLocalidad());
            pst.setString(11, direccion.getMunicipio());
            pst.setString(12, direccion.getProvincia());
            pst.setInt(13, o.getAdoptante_id());
            pst.executeUpdate();
            pst = con.prepareStatement("SELECT seq FROM sqlite_sequence WHERE name = \"Adoptante\";");
            rs = pst.executeQuery();
            int rowid = rs.getInt("seq");
            pst.close();
            con.close();
            return rowid;
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public boolean delete(int ID) {
        Connection con;
        PreparedStatement pst;
        try {
            con = Connector.connect();
            pst = con.prepareStatement("PRAGMA foreign_keys = ON;");
            pst.executeUpdate();
            pst = con.prepareStatement("DELETE FROM Adoptante WHERE adoptante_id = ?");
            pst.setInt(1, ID);
            pst.executeUpdate();
            pst.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
