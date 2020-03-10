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
                m.setMascota_id(rs.getInt("mascota_id"));
                m.setNombre(rs.getString("nombre"));
                m.setRaza(rs.getString("raza"));
                m.setColor(rs.getString("color"));
                m.setEdad(rs.getInt("edad"));
                m.setGenero(rs.getBoolean("genero"));
                m.setPeso(rs.getDouble("peso"));
                m.setUltimaDesparacitacion(rs.getString("ultimaDesparacitacion"));
                m.setUltimaVacunacion(rs.getString("ultimaVacunacion"));
                m.setFechaAdopcion(rs.getString("fechaAdopcion"));
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
                m.setMascota_id(rs.getInt("mascota_id"));
                m.setNombre(rs.getString("nombre"));
                m.setRaza(rs.getString("raza"));
                m.setColor(rs.getString("color"));
                m.setEdad(rs.getInt("edad"));
                m.setGenero(rs.getBoolean("genero"));
                m.setPeso(rs.getDouble("peso"));
                m.setUltimaDesparacitacion(rs.getString("ultimaDesparacitacion"));
                m.setUltimaVacunacion(rs.getString("ultimaVacunacion"));
                m.setFechaAdopcion(rs.getString("fechaAdopcion"));
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
    public int add(Mascota o) {
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        AdoptanteDAO adoptantes = new AdoptanteDAO();
        try {
            con = Connector.connect();
            pst = con.prepareStatement("INSERT INTO Mascota"
                    + " (adoptante_id, nombre,"
                    + " raza, color, edad, genero, peso,"
                    + " ultimaDesparacitacion, ultimaVacunacion,"
                    + " fechaAdopcion) VALUES"
                    + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            int adoptante_id = adoptantes.add(o.getAdoptante());
            pst.setInt(1, adoptante_id);
            pst.setString(2, o.getNombre());
            pst.setString(3, o.getRaza());
            pst.setString(4, o.getColor());
            pst.setInt(5, o.getEdad());
            pst.setBoolean(6, o.getGenero());
            pst.setDouble(7, o.getPeso());
            pst.setString(8, o.getUltimaDesparacitacion());
            pst.setString(9, o.getUltimaVacunacion());
            pst.setString(10, o.getFechaAdopcion());
            pst.executeUpdate();
            pst = con.prepareStatement("SELECT seq FROM sqlite_sequence WHERE name = \"Mascota\";");
            rs = pst.executeQuery();
            int rowid = rs.getInt("seq");
            pst.close();
            con.close();
            return rowid;
        } catch (SQLException ex) {
            Logger.getLogger(MascotaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    @Override
    public int update(Mascota o) {
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        try {
            con = Connector.connect();
            pst = con.prepareStatement("PRAGMA foreign_keys = ON;");
            pst.executeUpdate();
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
            pst.setInt(1, o.getAdoptante().getAdoptante_id());
            pst.setString(2, o.getNombre());
            pst.setString(3, o.getRaza());
            pst.setString(4, o.getColor());
            pst.setInt(5, o.getEdad());
            pst.setBoolean(6, o.getGenero());
            pst.setDouble(7, o.getPeso());
            pst.setString(8, o.getUltimaDesparacitacion());
            pst.setString(9, o.getUltimaVacunacion());
            pst.setString(10, o.getFechaAdopcion());
            pst.setInt(11, o.getMascota_id());
            pst.executeUpdate();
            pst = con.prepareStatement("SELECT seq FROM sqlite_sequence WHERE name = \"Mascota\";");
            rs = pst.executeQuery();
            int rowid = rs.getInt("seq");
            pst.close();
            con.close();
            return rowid;
        } catch (SQLException ex) {
            Logger.getLogger(MascotaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            pst = con.prepareStatement("PRAGMA foreign_keys = ON;");
            pst.executeUpdate();
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
