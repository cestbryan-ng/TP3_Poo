package classes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.scene.control.Alert;
import ui.tp3_poo.Page1Controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Conference extends Evenement {
    private String theme;
    private List<Participant> intervenants;

    public Conference() {
    }

    public Conference(String id, String nom, LocalDateTime date, String lieu, Integer capaciteMax, String theme, List<Participant> intervenants) {
        super(id, nom, date, lieu, capaciteMax);
        this.theme = theme;
        this.intervenants = intervenants;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public List<Participant> getIntervenants() {
        return intervenants;
    }

    public void setIntervenants(List<Participant> intervenants) {
        this.intervenants = intervenants;
    }


    public boolean ajouterParticipant(List<List<Object>> liste, Integer indice, Participant participant, String nom_fichier) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<Object> list =  (List) liste.get(indice).get(3);

        if (list.size() >= this.getCapaciteMax()) {
            throw new CapaciteMaxAtteinteException("Capacité maximale dépassée");
        } else {
            list.add(participant);
            liste.get(indice).set(3, list);

            try {
                mapper.writeValue(new File(nom_fichier + ".json"), liste);
                return true;
            } catch (IOException e) {
                return false;
            }
        }
    }

    public boolean annuler(List<List<Object>> liste, Integer indice, Participant participant, String nom_fichier) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        List<Object> list =  (List) liste.get(indice).get(3);
        int i = 0;
        for (Object elt : list) {
            Participant participant1 = mapper.convertValue(elt, Participant.class);
            if (participant1.getEmail().equals(participant.getEmail())) {
                list.remove(i);
                liste.get(indice).set(3, list);
                try {
                    mapper.writeValue(new File(nom_fichier + ".json"), liste);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            i++;
        }
        throw new RuntimeException();
    }

    public List<String> afficherDetails() {
        List<String> liste = new ArrayList<>();

        liste.add(this.getNom());
        liste.add(this.getTheme());
        liste.add(this.getId());
        liste.add(this.getLieu());
        liste.add(this.getCapaciteMax().toString());
        liste.add(this.getDate().toString());
        List<String> intervenants = this.getIntervenants().stream().map(e -> e.getNom()).toList();
        liste.add(intervenants.toString());

        return liste;
    }
}
