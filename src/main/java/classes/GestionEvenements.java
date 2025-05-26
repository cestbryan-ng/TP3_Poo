package classes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestionEvenements {
    private static GestionEvenements instance;
    private  Map<String, Evenement> evenementsOrganises;

    // Constructeurs
    private GestionEvenements() {}

    public static GestionEvenements getInstance() {
        if (instance == null) {
            return new GestionEvenements();
        }
        return instance;
    }

    //  Methode pour supprimer un event, on va supprimer l'objet Evenement dans le fichier json qui sert de stockage
    public boolean supprimerEvenement(List<List<Object>> liste ,Evenement evenement, String nom_fichier) {
        //  Création du ObjectMapper pour la sérialisation
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        //  On fait une recherche séquentielle, si l'event est dans la liste on le retire
        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i).get(0).equals("conference")) {
                Conference conference1 = mapper.convertValue(liste.get(i).get(2), Conference.class);
                if (evenement instanceof Concert) {}
                else {
                    Conference conference = (Conference) evenement;
                    if (conference.getNom().equals(conference1.getNom()) && conference.getDate().equals(conference1.getDate()) && conference.getLieu().equals(conference1.getLieu()) && conference.getIntervenants().equals(conference1.getIntervenants()) && conference.getTheme().equals(conference1.getTheme()) && conference.getId().equals(conference1.getId())) {
                        liste.remove(i);        //  On retire l'event de la liste des events
                        //  Puis on sérialise,  on retourne un booleen qu'on utilisera pour l'affichage dans l'interface
                        try {
                            mapper.writeValue(new File(nom_fichier + ".json"), liste);
                            return true;
                        } catch (IOException e) {
                            e.printStackTrace();
                            return false;
                        }
                    }
                }
            } else {
                Concert concert1 = mapper.convertValue(liste.get(i).get(2), Concert.class);
                if (evenement instanceof Conference) {}
                else {
                    Concert concert = (Concert) evenement;
                    if (concert.getNom().equals(concert1.getNom()) && concert.getDate().equals(concert1.getDate()) && concert.getLieu().equals(concert1.getLieu()) && concert.getArtiste().equals(concert1.getArtiste())  && concert.getGenreMusical().equals(concert1.getGenreMusical()) && concert.getId().equals(concert1.getId())) {
                        liste.remove(i);        //  On retire l'event de la liste des events
                        //  Puis on sérialise,  on retourne un booleen qu'on utilisera pour l'affichage dans l'interface
                        try {
                            mapper.writeValue(new File(nom_fichier + ".json"), liste);
                            return true;
                        } catch (IOException e) {
                            e.printStackTrace();
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    //  Methode pour ajouter un event, on va ajouter l'objet Evenement dans le fichier json qui sert de stockage
    public boolean ajouterEvenement(Evenement evenement, String owner, List<List<Object>> liste, String nom_fichier) {
        //  Création du ObjectMapper pour la sérialisation
        List<Object> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        //  On fait une recherche séquentielle, si l'event est dans la liste d'event on va lever notre excpetion personnalisée EventDejaExistantException
        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i).get(0).equals("conference")) {
                Conference conference1 = mapper.convertValue(liste.get(i).get(2), Conference.class);
                if (evenement instanceof Concert) continue;
                else {
                    Conference conference = (Conference) evenement;
                    if (conference.getNom().equals(conference1.getNom()) && conference.getDate().equals(conference1.getDate()) && conference.getLieu().equals(conference1.getLieu()) && conference.getIntervenants().equals(conference1.getIntervenants()) && conference.getTheme().equals(conference1.getTheme())) throw new EvenementDejaExistantException("L'event existe déjà");
                }
            } else {
                Concert concert1 = mapper.convertValue(liste.get(i).get(2), Concert.class);
                if (evenement instanceof Conference) continue;
                else {
                    Concert concert = (Concert) evenement;
                    if (concert.getNom().equals(concert1.getNom()) && concert.getDate().equals(concert1.getDate()) && concert.getLieu().equals(concert1.getLieu()) && concert.getArtiste().equals(concert1.getArtiste())  && concert.getGenreMusical().equals(concert1.getGenreMusical())) throw new EvenementDejaExistantException("L'event existe déjà");
                }
            }
        }

        //  On ajoute l'event dans la liste d'event
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
        liste.add(list);

        //  Puis on sérialise,  on retourne un booleen qu'on utilisera pour l'affichage dans l'interface
        try {
            mapper.writeValue(new File(nom_fichier + ".json"), liste);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Getters & Setters
    public Map<String, Evenement> getEvenementsOrganises() {
        return evenementsOrganises;
    }

    public void setEvenementsOrganises(Map<String, Evenement> evenementsOrganises) {
        this.evenementsOrganises = evenementsOrganises;
    }
}
