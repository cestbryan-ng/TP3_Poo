package ui.tp3_poo;

import classes.CapaciteMaxAtteinteException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestEvenementDejaExistantException {

    @Test
    void testMethodes() {
        List<List<Integer>> liste = new ArrayList<>();
        liste.add(0, new ArrayList<>(List.of(1, 2)));
        System.out.println(liste);
    }
}
