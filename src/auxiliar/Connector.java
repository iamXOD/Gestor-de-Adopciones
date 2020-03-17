/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author HAROLD
 */
public class Connector {

    private static Connection connect;

    public static SimpleStringProperty url = new SimpleStringProperty("storage.db");

    private static final String[] SQLStatements = {"CREATE TABLE \"Adoptante\" (\n"
        + "	\"adoptante_id\"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
        + "	\"nombre\"	TEXT NOT NULL,\n"
        + "	\"primerApellido\"	TEXT NOT NULL,\n"
        + "	\"segundoApellido\"	TEXT NOT NULL,\n"
        + "	\"ciOPasaporte\"	TEXT NOT NULL UNIQUE,\n"
        + "	\"genero\"	INTEGER,\n"
        + "	\"callePrincipal\"	TEXT NOT NULL,\n"
        + "	\"entreCalle\"	TEXT,\n"
        + "	\"yCalle\"	TEXT,\n"
        + "	\"no\"	TEXT NOT NULL,\n"
        + "	\"localidad\"	TEXT NOT NULL,\n"
        + "	\"municipio\"	TEXT NOT NULL,\n"
        + "	\"provincia\"	TEXT NOT NULL\n"
        + ")",
        "CREATE TABLE \"Mascota\" (\n"
        + "	\"mascota_id\"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n"
        + "	\"adoptante_id\"	INTEGER,\n"
        + "	\"nombre\"	TEXT NOT NULL,\n"
        + "	\"raza\"	TEXT,\n"
        + "	\"color\"	TEXT,\n"
        + "	\"edad\"	INTEGER,\n"
        + "	\"genero\"	INTEGER,\n"
        + "	\"peso\"	REAL,\n"
        + "	\"ultimaDesparacitacion\"	TEXT,\n"
        + "	\"ultimaVacunacion\"	TEXT,\n"
        + "	\"fechaAdopcion\"	TEXT,\n"
        + "	FOREIGN KEY(\"adoptante_id\") REFERENCES \"Adoptante\"(\"adoptante_id\") ON UPDATE CASCADE ON DELETE CASCADE\n"
        + ")",
        "CREATE INDEX mascota_fechaAdopcion ON Mascota (fechaAdopcion)",
        "CREATE INDEX mascota_raza ON Mascota (raza)",
        "CREATE INDEX mascota_adoptante ON Mascota (adoptante_id DESC)",
        "CREATE INDEX mascota_id ON Mascota (mascota_id DESC)",
        "CREATE INDEX adoptante_ci ON Adoptante (ciOPasaporte)",
        "CREATE INDEX adoptante_id ON Adoptante (adoptante_id DESC)",
        "CREATE VIEW Adopcion(ID, Mascota, Adoptante, Fecha) as"
        + " SELECT Mascota.mascota_id as ID, Mascota.nombre as Mascota,"
        + " Adoptante.nombre || ' ' || Adoptante.primerApellido"
        + " || ' ' || Adoptante.segundoApellido as Adoptante,"
        + " Mascota.fechaAdopcion as Fecha"
        + " FROM Adoptante INNER JOIN Mascota"
        + " USING(adoptante_id)"
        + " ORDER BY Mascota.adoptante_id"};

    public static Connection connect() {
        connect = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection("jdbc:sqlite:" + url.get());
            if (connect != null) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se ha podido conectar a la base de datos: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Class " + Connector.class.getName() + " Not Found");
        }
        return connect;
    }

    public static boolean checkSchema(String url) {
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        ArrayList<String> aux = new ArrayList<>();
        String temp = Connector.url.get();
        try {
            Connector.url.set(url);
            con = Connector.connect();
            pst = con.prepareStatement("SELECT sql FROM sqlite_master;");
            rs = pst.executeQuery();
            while (rs.next()) {
                aux.add(rs.getString("sql"));
            }
            pst.close();
            rs.close();
            con.close();
            if (aux.size() != 12) {
                System.out.println("Rowcount diferent of 12 rows: " + aux.size());
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Connector.url.set(temp);
        }
        if (!aux.containsAll(Arrays.asList(SQLStatements))) {
            System.out.println("Incorrect Schema");
            return false;
        }
        System.out.println("Correct Schema");
        return true;
    }

    public static boolean createSchema() {
        Connection con = Connector.connect();
        PreparedStatement pst;
        try {
            for (String str : SQLStatements) {
                pst = con.prepareStatement(str + ";");
                pst.executeUpdate();
            }
            Connector.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static void close() {
        try {
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
