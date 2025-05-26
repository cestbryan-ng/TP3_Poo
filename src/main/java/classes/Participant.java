package classes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Participant {
    private String id;
    private String nom;
    private String email;

    //  Constructeurs
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

    // Getters & Setters
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

    //  Redefinition de la méthode equals de sorte que mtn deux participants ayant les meme attributs sont egaux
    @Override
    public boolean equals(Object a) {
        if (this == a) return  true;

        return Objects.equals(id, ((Participant) a).getId()) && Objects.equals(nom, ((Participant) a).getNom()) && Objects.equals(email, ((Participant) a).getEmail());
    }
}
