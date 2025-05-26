package classes;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Evenement {
    private String id;
    private String nom;
    private LocalDateTime date;
    private String lieu;
    private Integer capaciteMax;

    public Evenement() {
    }

    public Evenement(String id, String nom, LocalDateTime date, String lieu, Integer capaciteMax) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.capaciteMax = capaciteMax;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Integer getCapaciteMax() {
        return capaciteMax;
    }

    public void setCapaciteMax(Integer capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    abstract public List<Object> ajouterParticipant(List<List<Object>> liste, Integer indice, Participant participant);

    abstract public List<Object> annuler(List<List<Object>> liste, Integer indice, Participant participant);

    abstract public List<String> afficherDetails();
}
