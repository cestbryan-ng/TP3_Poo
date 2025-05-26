package ui.tp3_poo;

import classes.Conference;
import classes.GestionEvenements;
import classes.Participant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class TestSupprimerConference {
    private List<List<Object>> liste;
    private Conference conference;
    private Participant participant;

    @BeforeEach
    public void initialiser() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        liste = mapper.readValue(
                new File("test_event.json"),
                new TypeReference<List<List<Object>>>() {}
        );

        participant = new Participant("x", "x@gmail.com");
        conference = new Conference("6", "abc", LocalDateTime.parse("2025-06-01T14:20:30"), "lieu", 40, "theme", List.of(participant));
    }

    @Test
    void testMethodes() {
        GestionEvenements gestionEvenements = GestionEvenements.getInstance();
        Assertions.assertTrue(gestionEvenements.supprimerEvenement(liste , conference, "test_event"), "La suppression a echoué");
        System.out.println("La suppression test a reussi, vous pouvez voir le resultat dans le json test : test_even.json à la racine du projet");
    }
}
