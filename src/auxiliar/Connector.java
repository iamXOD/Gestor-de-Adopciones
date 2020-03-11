/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HAROLD
 */
public class Connector {

    private static Connection connect;

    public static String url = "storage.db";

    public static Connection connect() {
        connect = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection("jdbc:sqlite:" + url);
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

    public static void createSchema() {
        createDireccion();
        createAdoptante();
        createMascota();
        createIndexes();
        createAdopcionView();
    }

    private static boolean createDireccion() {
        PreparedStatement pst;
        try {
            pst = Connector.connect().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS \"Direccion\" (\n"
                    + "	\"direccion_id\"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                    + "	\"callePrincipal\"	TEXT NOT NULL,\n"
                    + "	\"entreCalle\"	TEXT,\n"
                    + "	\"yCalle\"	TEXT,\n"
                    + "	\"no\"	INTEGER NOT NULL,\n"
                    + "	\"localidad\"	TEXT NOT NULL,\n"
                    + "	\"municipio\"	TEXT NOT NULL,\n"
                    + "	\"provincia\"	TEXT NOT NULL\n"
                    + ");");
            pst.executeUpdate();
            pst.close();
            Connector.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return false;
    }

    private static boolean createAdoptante() {
        PreparedStatement pst;
        try {
            pst = Connector.connect().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS \"Adoptante\" (\n"
                    + "	\"adoptante_id\"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                    + "	\"nombre\"	TEXT NOT NULL,\n"
                    + "	\"primerApellido\"	TEXT NOT NULL,\n"
                    + "	\"segundoApellido\"	TEXT NOT NULL,\n"
                    + "	\"ciOPasaporte\"	TEXT NOT NULL UNIQUE,\n"
                    + "	\"genero\"	INTEGER,\n"
                    + "	\"direccion_id\"	INTEGER,\n"
                    + "	FOREIGN KEY(\"direccion_id\") REFERENCES \"Direccion\"(\"direccion_id\")"
                    + " ON UPDATE CASCADE ON DELETE CASCADE\n"
                    + ");");
            pst.executeUpdate();
            pst.close();
            Connector.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    private static boolean createMascota() {
        PreparedStatement pst;
        try {
            pst = Connector.connect().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS \"Mascota\" (\n"
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
                    + "	FOREIGN KEY(\"adoptante_id\") REFERENCES \"Adoptante\"(\"adoptante_id\")"
                    + " ON UPDATE CASCADE ON DELETE CASCADE\n"
                    + ");");
            pst.executeUpdate();
            pst.close();
            Connector.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    private static boolean createIndexes() {
        String[] indexes = {
            "CREATE INDEX IF NOT EXISTS mascota_fechaAdopcion ON Mascota (fechaAdopcion);",
            "CREATE INDEX IF NOT EXISTS mascota_raza ON Mascota (raza);",
            "CREATE INDEX IF NOT EXISTS mascota_adoptante ON Mascota (adoptante_id DESC);",
            "CREATE INDEX IF NOT EXISTS mascota_id ON Mascota (mascota_id DESC);",
            "CREATE INDEX IF NOT EXISTS direccion_no ON Direccion (no);",
            "CREATE INDEX IF NOT EXISTS direccion_id ON Direccion (direccion_id DESC);",
            "CREATE INDEX IF NOT EXISTS adoptante_direccion ON Adoptante (direccion_id DESC);",
            "CREATE INDEX IF NOT EXISTS adoptante_ci ON Adoptante (ciOPasaporte);",
            "CREATE INDEX IF NOT EXISTS adoptante_id ON Adoptante (adoptante_id DESC);"
        };
        Connection con;
        PreparedStatement pst;
        try {
            con = Connector.connect();
            for (String str : indexes) {
                pst = con.prepareStatement(str);
                pst.executeUpdate();
                pst.close();
            }
            Connector.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return false;
    }

    private static boolean createAdopcionView() {
        PreparedStatement pst;
        try {
            pst = Connector.connect().prepareStatement(
                    "CREATE VIEW IF NOT EXISTS Adopcion(ID, Mascota, Adoptante, Fecha)"
                    + " as SELECT Mascota.mascota_id as ID, Mascota.nombre as Mascota,"
                    + " Adoptante.nombre || ' ' || Adoptante.primerApellido || ' ' || Adoptante.segundoApellido as Adoptante,"
                    + " Mascota.fechaAdopcion as Fecha"
                    + " FROM Adoptante INNER JOIN Mascota"
                    + " USING(adoptante_id)"
                    + " ORDER BY Mascota.adoptante_id;");
            pst.executeUpdate();
            pst.close();
            Connector.close();
            return true;
        } catch (SQLException ex) {
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
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
