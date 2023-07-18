package ar.edu.utn.frc.tup.lciii.ModelsTest;

import ar.edu.utn.frc.tup.lciii.models.PiecePosition;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PiecePositionTest {

    @Test
    public void testConstructorAndGetters() {
        Integer row = 2;
        Integer column = 3;

        PiecePosition position = new PiecePosition(row, column);

        assertEquals(row, position.getRow());
        assertEquals(column, position.getColumn());
    }

    @Test
    public void testSetters() {
        Integer row = 4;
        Integer column = 5;

        PiecePosition position = new PiecePosition();

        position.setRow(row);
        position.setColumn(column);

        assertEquals(row, position.getRow());
        assertEquals(column, position.getColumn());
    }

    @Test
    public void testEquals() {
        Integer row1 = 1;
        Integer column1 = 2;
        PiecePosition position1 = new PiecePosition(row1, column1);

        Integer row2 = 1;
        Integer column2 = 2;
        PiecePosition position2 = new PiecePosition(row2, column2);

        Integer row3 = 3;
        Integer column3 = 4;
        PiecePosition position3 = new PiecePosition(row3, column3);

        // Same row and column
        assertTrue(position1.equals(position2));
        assertTrue(position2.equals(position1));

        // Different row or column
        assertFalse(position1.equals(position3));
        assertFalse(position3.equals(position1));
    }

    @Test
    public void testHashCode() {
        Integer row1 = 2;
        Integer column1 = 3;
        PiecePosition position1 = new PiecePosition(row1, column1);

        Integer row2 = 2;
        Integer column2 = 3;
        PiecePosition position2 = new PiecePosition(row2, column2);

        assertEquals(position1.hashCode(), position2.hashCode());
    }
}