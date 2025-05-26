package ui.tp3_poo;

import classes.Participant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class TestInscription {
    private Participant participant;

    @BeforeEach
    public void initialiser() {
        participant = new Participant("toto", "toto@gmail.com");
    }

    @Test
    void testMethodes() throws SQLException {
        Connection connection = PoolConnexion.seConnecter();
        boolean resultat = participant.inscrire(connection, "toto");
        connection.close();
        Assertions.assertTrue(resultat, "L'inscription a echoué, parce que cet utilisateur existe déjà.");
        System.out.println("L'inscription a réussi.");
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