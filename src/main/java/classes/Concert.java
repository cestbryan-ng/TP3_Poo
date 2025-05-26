package classes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ui.tp3_poo.Page1Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Concert extends Evenement {
    private String artiste;
    private String genreMusical;

    public Concert() {
    }

    public Concert(String id, String nom, LocalDateTime date, String lieu, Integer capaciteMax, String artiste, String genreMusical) {
        super(id, nom, date, lieu, capaciteMax);
        this.artiste = artiste;
        this.genreMusical = genreMusical;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public String getGenreMusical() {
        return genreMusical;
    }

    public void setGenreMusical(String genreMusical) {
        this.genreMusical = genreMusical;
    }


    public List<Object> ajouterParticipant(List<List<Object>> liste, Integer indice, Participant participant) {
        List<Object> list =  (List) liste.get(indice).get(3);
        if (list.size() >= this.getCapaciteMax()) {
            throw new CapaciteMaxAtteinteException("Capacité maximale dépassée");
        } else {
            list.add(participant);
        }

        return list;
    }

    public List<Object>  annuler(List<List<Object>> liste, Integer indice, Participant participant) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        List<Object> list =  (List) liste.get(indice).get(3);
        int i = 0;
        for (Object elt : list) {
            Participant participant1 = mapper.convertValue(elt, Participant.class);
            if (participant1.getEmail().equals(participant.getEmail())) {
                list.remove(i);
                return list;
            }
            i++;
        }
        throw new RuntimeException();
    }

    public List<String> afficherDetails() {
        List<String> liste = new ArrayList<>();

        liste.add(this.getNom());
        liste.add(this.getArtiste());
        liste.add(this.getId());
        liste.add(this.getLieu());
        liste.add(this.getCapaciteMax().toString());
        liste.add(this.getDate().toString());
        liste.add(this.getGenreMusical());

        return liste;
    }
}
