package ui.tp3_poo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface PoolConnexion {
    String adresse_bd = "jdbc:mysql://localhost:3306/event";
    String nom_util = "Jean_Roland";
    String mot_de_passe = "Papasenegal0";

    static Connection seConnecter() throws SQLException {
        return DriverManager.getConnection(adresse_bd, nom_util, mot_de_passe);
    }
}
