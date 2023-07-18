package ar.edu.utn.frc.tup.lciii.PiecesTests;
import ar.edu.utn.frc.tup.lciii.*;
import ar.edu.utn.frc.tup.lciii.models.PieceColor;
import ar.edu.utn.frc.tup.lciii.models.PiecePosition;
import ar.edu.utn.frc.tup.lciii.models.pieces.Knight;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KnightTest {

    @Test
    public void testMovementValid_ValidMove_ReturnsTrue() {
        // Arrange
        PiecePosition currentPosition = new PiecePosition(3, 3);
        Knight knight = new Knight(PieceColor.WHITE, currentPosition);
        PiecePosition newPosition1 = new PiecePosition(1, 2);
        PiecePosition newPosition2 = new PiecePosition(2, 1);

        // Act
        boolean result1 = knight.movementValid(newPosition1, new ArrayList<>());
        boolean result2 = knight.movementValid(newPosition2, new ArrayList<>());

        // Assert
        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void testMovementValid_InvalidMove_ReturnsFalse() {
        // Arrange
        PiecePosition currentPosition = new PiecePosition(3, 3);
        Knight knight = new Knight(PieceColor.WHITE, currentPosition);
        PiecePosition newPosition1 = new PiecePosition(4, 4);


        // Act
        boolean result1 = knight.movementValid(newPosition1, new ArrayList<>());


        // Assert
        assertFalse(result1);

    }
}