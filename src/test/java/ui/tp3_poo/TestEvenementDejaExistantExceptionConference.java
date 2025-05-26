package ui.tp3_poo;

import classes.Concert;
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

public class TestEvenementDejaExistantExceptionConference {
    private List<List<Object>> liste;
    private Conference conference;
    private String owner = "robin";
    private int indice = 0;

    @BeforeEach
    public void initialiser() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        liste = mapper.readValue(
                new File("test_event.json"),
                new TypeReference<List<List<Object>>>() {}
        );

        conference = mapper.convertValue(liste.get(indice).get(2), Conference.class);
    }

    @Test
    void testMethodes() {
        GestionEvenements gestionEvenements = GestionEvenements.getInstance();
        Assertions.assertTrue(gestionEvenements.ajouterEvenement(conference, owner, liste ,"test_event"), "La création a echoué");
        System.out.println("La création test a reussi, vous pouvez voir le resultat dans le json test : test_even.json à la racine du projet");
    }
}
