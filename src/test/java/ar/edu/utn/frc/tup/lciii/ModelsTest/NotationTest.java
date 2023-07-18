package ar.edu.utn.frc.tup.lciii.ModelsTest;

import ar.edu.utn.frc.tup.lciii.models.Notation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NotationTest {

    @Test
    public void testConstructorAndGetters() {
        String attack = "e4";
        String counterattack = "e5";

        Notation notation = new Notation(attack, counterattack);

        assertEquals(attack, notation.getAttack());
        assertEquals(counterattack, notation.getCounterattack());
    }

    @Test
    public void testSetters() {
        String attack = "d4";
        String counterattack = "d5";

        Notation notation = new Notation();

        notation.setAttack(attack);
        notation.setCounterattack(counterattack);

        assertEquals(attack, notation.getAttack());
        assertEquals(counterattack, notation.getCounterattack());
    }

    @Test
    public void testEquals() {
        String attack1 = "Nf3";
        String counterattack1 = "Nc6";
        Notation notation1 = new Notation(attack1, counterattack1);

        String attack2 = "Nf3";
        String counterattack2 = "Nc6";
        Notation notation2 = new Notation(attack2, counterattack2);

        String attack3 = "e4";
        String counterattack3 = "e5";
        Notation notation3 = new Notation(attack3, counterattack3);

        // Same attack and counterattack
        assertTrue(notation1.equals(notation2));
        assertTrue(notation2.equals(notation1));

        // Different attack or counterattack
        assertFalse(notation1.equals(notation3));
        assertFalse(notation3.equals(notation1));
    }
}
