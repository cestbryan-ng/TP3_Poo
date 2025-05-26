package ui.tp3_poo;

import classes.Concert;
import classes.Conference;
import classes.Participant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestDesinscriptionConference {
    private Participant participant;
    private Conference conference;
    private List<List<Object>> liste = new ArrayList<>();
    private int indice = 1;

    @BeforeEach
    public void initialiser() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        liste = mapper.readValue(
                new File("test_event.json"),
                new TypeReference<List<List<Object>>>() {}
        );

        conference = mapper.convertValue(liste.get(indice).get(2), Conference.class);
        participant = new Participant("batman", "batman@gmail.com");
    }

    @Test
    void testMethodes() throws SQLException {
        Assertions.assertTrue(conference.annuler(liste, indice, participant, "test_event"), "La désinscription a echoué");
        System.out.println("La désinscription test a reussi, vous pouvez voir le resultat dans le json test : test_even.json à la racine du projet");
    }
}
