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

    //  Methode pour ajouter un participant, on va sérialiser l'objet participant dans le fichier json qui sert de stockage
    public boolean ajouterParticipant(List<List<Object>> liste, Integer indice, Participant participant, String nom_fichier) {
        // Création du ObjectMapper pour la sérialisation
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Liste des participants actuels
        List<Object> list =  (List) liste.get(indice).get(3);

        if (list.size() >= this.getCapaciteMax()) {     // On a depassé la capacité donc on leve notre exception personnalisée
            throw new CapaciteMaxAtteinteException("Capacité maximale dépassée");
        } else {
            list.add(participant);      //  on l'ajoute dans la liste des participants
            liste.get(indice).set(3, list);

            //  Sérialisation de la liste, on retourne un booleen qu'on utilisera pour l'affichage dans l'interface
            try {
                mapper.writeValue(new File(nom_fichier + ".json"), liste);
                return true;
            } catch (IOException e) {
                return false;
            }
        }
    }

    //  Methode pour supprimer un participant, on va supprimer l'objet participant dans le fichier json qui sert de stockage
    public boolean annuler(List<List<Object>> liste, Integer indice, Participant participant, String nom_fichier) {
        // Création du ObjectMapper pour la sérialisation
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        // Liste des participants actuels
        List<Object> list =  (List) liste.get(indice).get(3);
        int i = 0;
        for (Object elt : list) {
            Participant participant1 = mapper.convertValue(elt, Participant.class);
            if (participant1.getEmail().equals(participant.getEmail())) {   // On se sert de l'email ici comme identifiant
                list.remove(i);     //  On retire  le participant de liste des participants actuels
                liste.get(indice).set(3, list);
                //  Puis on sérialise la liste, on retourne un booleen qu'on utilisera pour l'affichage dans l'interface
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
        //  Comme on a plus pour mon cas je leve une exception pour l'interface, on pourra tout aussi faire un return false;
        throw new RuntimeException();
    }

    //  Methode pour envoir tous les attributs de l'objet en une liste, qu'on va utiliser pour l'affichage dans l'interface
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
