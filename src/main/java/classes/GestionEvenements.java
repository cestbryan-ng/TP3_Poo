package classes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ui.tp3_poo.Page1Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GestionEvenements {
    private static GestionEvenements instance;
    private  Map<String, Evenement> evenementsOrganises;

    private GestionEvenements() {}

    public static GestionEvenements getInstance() {
        if (instance == null) {
            return new GestionEvenements();
        }
        return instance;
    }

    public Integer supprimerEvenement(List<List<Object>> liste ,Evenement evenement) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        for (int i = 0; i < liste.size(); i++) {
            if (evenement instanceof Concert) {
                Concert concert = (Concert) evenement;
                if (concert.equals(liste.get(i).get(2))) {
                    return i;
                }
            } else {
                Conference conference = (Conference) evenement;
                if (conference.equals(liste.get(i).get(2))) {
                    return i;
                }
            }
        }
        return null;
    }

    public List<Object> ajouterEvenement(Evenement evenement, String owner, List<List<Object>> liste) {
        List<Object> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i).get(0).equals("conference")) {
                Conference conference1 = mapper.convertValue(liste.get(i).get(2), Conference.class);
                if (evenement instanceof Concert) continue;
                else {
                    Conference conference = (Conference) evenement;
                    if (conference.getNom().equals(conference1.getNom()) && conference.getDate().equals(conference1.getDate()) && conference.getLieu().equals(conference1.getLieu()) && conference.getIntervenants().equals(conference1.getIntervenants()) && conference.getTheme().equals(conference1.getTheme())) throw new EvenementDejaExistantException("");
                }
            } else {
                Concert concert1 = mapper.convertValue(liste.get(i).get(2), Concert.class);
                if (evenement instanceof Conference) continue;
                else {
                    Concert concert = (Concert) evenement;
                    if (concert.getNom().equals(concert1.getNom()) && concert.getDate().equals(concert1.getDate()) && concert.getLieu().equals(concert1.getLieu()) && concert.getArtiste().equals(concert1.getArtiste())  && concert.getGenreMusical().equals(concert1.getGenreMusical())) throw new EvenementDejaExistantException("");
                }
            }
        }

        if (evenement instanceof Concert) {
            Concert concert = (Concert) evenement;
            list.add("concert");
            list.add(owner);
            list.add(concert);
            list.add(new ArrayList<>());
        } else {
            Conference conference = (Conference) evenement;
            list.add("conference");
            list.add(owner);
            list.add(conference);
            list.add(new ArrayList<>());
        }
        return list;
    }

    public Map<String, Evenement> getEvenementsOrganises() {
        return evenementsOrganises;
    }

    public void setEvenementsOrganises(Map<String, Evenement> evenementsOrganises) {
        this.evenementsOrganises = evenementsOrganises;
    }
}
