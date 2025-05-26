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

public class TestSupprimerConcert {
    private List<List<Object>> liste;
    private Concert concert;
    private int indice = 3;

    @BeforeEach
    public void initialiser() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        liste = mapper.readValue(
                new File("test_event.json"),
                new TypeReference<List<List<Object>>>() {}
        );

        concert = new Concert("7", "abc", LocalDateTime.parse("2025-06-01T14:20:30"), "lieu", 40, "theme", "jazz");
    }

    @Test
    void testMethodes() {
        GestionEvenements gestionEvenements = GestionEvenements.getInstance();
        Assertions.assertTrue(gestionEvenements.supprimerEvenement(liste , concert, "test_event"), "La suppression a echoué");
        System.out.println("La suppression test a reussi, vous pouvez voir le resultat dans le json test : test_even.json à la racine du projet");
    }
}
