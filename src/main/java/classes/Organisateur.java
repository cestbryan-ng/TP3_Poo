package classes;

import java.util.Map;

public class Organisateur extends Participant{
    private Map<String, Evenement> evenementsOrganises;

    public Organisateur() {
    }

    public Organisateur(String id, String nom, String email) {
        super(id, nom, email);
    }

    public Map<String, Evenement> getEvenements() {
        return evenementsOrganises;
    }

    public void setEvenements(Map<String, Evenement> evenements) {
        this.evenementsOrganises = evenements;
    }
}
