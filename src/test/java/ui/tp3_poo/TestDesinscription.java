package ui.tp3_poo;

import classes.Participant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDesinscription {
    private Participant participant;

    private boolean existe(String email) {
        try (Connection connection = PoolConnexion.seConnecter();
        Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery("select * from user where email = \"" + email + "\";");
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        }
    }

    @BeforeEach
    public void initialiser() {
        participant = new Participant("toto", "toto@gmail.com");
    }

    @Test
    void testMethodes() throws SQLException {
        Connection connection = PoolConnexion.seConnecter();
        boolean resultat = participant.desinscrire(connection);
        connection.close();
        Assertions.assertTrue(resultat, "La désinscription a echoué, parce que l'utilisateur n'existe pas.");
        System.out.println("La désinscription a réussi.");
        boolean resultat2 = existe(participant.getEmail());
        Assertions.assertFalse(resultat2, "L'utitisateur est toujours présent dans la bd");
        System.out.println("L'utilisateur n'est plus dans la bd");
    }

    @AfterEach
    public void supprimer() throws SQLException {
        Connection connection = PoolConnexion.seConnecter();
        Statement statement = connection.createStatement();
        statement.executeUpdate("delete from user where email = \"toto@gmail.com\";");
        statement.close();
        connection.close();
    }
}
