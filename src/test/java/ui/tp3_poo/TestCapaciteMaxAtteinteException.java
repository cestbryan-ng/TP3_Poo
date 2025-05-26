package ui.tp3_poo;

import classes.CapaciteMaxAtteinteException;
import classes.Concert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCapaciteMaxAtteinteException {
    private Concert concert;

    @BeforeEach
    public void initialiser() {
        concert = new Concert();
    }

    @Test
    void testMethodes() {
        concert.setCapaciteMax(50);
        Assertions.assertThrows(CapaciteMaxAtteinteException.class, () -> {});
        System.out.println("Des places sont encore disponibles");
    }
}
