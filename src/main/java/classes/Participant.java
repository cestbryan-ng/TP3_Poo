package classes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Participant {
    private String id;
    private String nom;
    private String email;

    public Participant() {
    }

    public Participant(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }

    public Participant(String id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean inscrire(Connection connection, String mot_de_passe) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("insert into user(nom, mot_de_passe, email)\n" +
                    "values (\""+ this.nom +"\", \""+ mot_de_passe +"\", \""+ this.email +"\"); ");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean desinscrire(Connection connection) {
        try(Statement statement = connection.createStatement()) {
            int resultat = statement.executeUpdate("delete from user where email = \""+ email +"\";");
            return resultat > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object a) {
        if (this == a) return  true;

        return Objects.equals(id, ((Participant) a).getId()) && Objects.equals(nom, ((Participant) a).getNom()) && Objects.equals(email, ((Participant) a).getEmail());
    }
}
