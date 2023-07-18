package ar.edu.utn.frc.tup.lciii.PiecesTests;
import ar.edu.utn.frc.tup.lciii.models.Piece;
import ar.edu.utn.frc.tup.lciii.models.PieceColor;
import ar.edu.utn.frc.tup.lciii.models.PiecePosition;
import ar.edu.utn.frc.tup.lciii.models.pieces.King;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class KingTest {

    @Test
    public void testMovementValid_ValidMove_ReturnsTrue() {
        // Arrange
        PiecePosition currentPosition = new PiecePosition(3, 3);
        King king = new King(PieceColor.WHITE, currentPosition);
        PiecePosition newPosition = new PiecePosition(4, 3);
        List<Piece> piecesListOnBoard = new ArrayList<>();

        // Act
        boolean result = king.movementValid(newPosition, piecesListOnBoard);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testMovementValid_InvalidMove_ReturnsFalse() {
        // Arrange
        PiecePosition currentPosition = new PiecePosition(3, 3);
        King king = new King(PieceColor.WHITE, currentPosition);
        PiecePosition newPosition = new PiecePosition(5, 5);
        List<Piece> piecesListOnBoard = new ArrayList<>();

        // Act
        boolean result = king.movementValid(newPosition, piecesListOnBoard);

        // Assert
        assertFalse(result);
    }
    @Test
    public void testPossibleMovesList() {
        // Arrange
        King king = new King(PieceColor.WHITE, new PiecePosition(3, 3));
        List<PiecePosition> expectedMovesList = List.of(
                new PiecePosition(2, 2),
                new PiecePosition(2, 3),
                new PiecePosition(2, 4),
                new PiecePosition(3, 2),
                new PiecePosition(3, 4),
                new PiecePosition(4, 2),
                new PiecePosition(4, 3),
                new PiecePosition(4, 4)
        );

        // Act
        List<PiecePosition> movesList = king.possibleMovesList();

        // Assert
        assertEquals(expectedMovesList, movesList);
    }
}