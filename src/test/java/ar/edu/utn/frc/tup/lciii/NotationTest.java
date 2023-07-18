package ar.edu.utn.frc.tup.lciii;

import ar.edu.utn.frc.tup.lciii.models.Notation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotationTest {
    @Test
    void notationConstructorAndGetters() {
        // Crear una instancia de Notation utilizando el constructor
        Notation notation = new Notation("Attack", "Counterattack");

        // Verificar que los valores se hayan asignado correctamente
        assertEquals("Attack", notation.getAttack());
        assertEquals("Counterattack", notation.getCounterattack());
    }

    @Test
    void notationSettersAndGetters() {
        // Crear una instancia de Notation utilizando el constructor vacío
        Notation notation = new Notation();

        // Utilizar los setters para asignar valores a los atributos
        notation.setAttack("New Attack");
        notation.setCounterattack("New Counterattack");

        // Verificar que los valores se hayan asignado correctamente
        assertEquals("New Attack", notation.getAttack());
        assertEquals("New Counterattack", notation.getCounterattack());
    }
}
