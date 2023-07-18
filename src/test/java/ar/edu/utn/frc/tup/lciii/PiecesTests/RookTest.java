package ar.edu.utn.frc.tup.lciii.PiecesTests;
import ar.edu.utn.frc.tup.lciii.models.Piece;
import ar.edu.utn.frc.tup.lciii.models.PieceColor;
import ar.edu.utn.frc.tup.lciii.models.PiecePosition;
import ar.edu.utn.frc.tup.lciii.models.pieces.Pawn;
import ar.edu.utn.frc.tup.lciii.models.pieces.Rook;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    @Test
    public void testMovementValid_SameRow_Valid() {
        // Arrange
        PiecePosition currentPosition = new PiecePosition(3, 3);
        Rook rook = new Rook(PieceColor.WHITE, currentPosition);
        PiecePosition newPosition = new PiecePosition(3, 6);
        List<Piece> piecesListOnBoard = new ArrayList<>();

        // Act
        boolean result = rook.movementValid(newPosition, piecesListOnBoard);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testMovementValid_SameColumn_Valid() {
        // Arrange
        PiecePosition currentPosition = new PiecePosition(3, 3);
        Rook rook = new Rook(PieceColor.WHITE, currentPosition);
        PiecePosition newPosition = new PiecePosition(6, 3);
        List<Piece> piecesListOnBoard = new ArrayList<>();

        // Act
        boolean result = rook.movementValid(newPosition, piecesListOnBoard);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testMovementValid_DifferentRowAndColumn_Invalid() {
        // Arrange
        PiecePosition currentPosition = new PiecePosition(3, 3);
        Rook rook = new Rook(PieceColor.WHITE, currentPosition);
        PiecePosition newPosition = new PiecePosition(5, 6);
        List<Piece> piecesListOnBoard = new ArrayList<>();

        // Act
        boolean result = rook.movementValid(newPosition, piecesListOnBoard);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testCheckObstacle_NoObstacle() {
        // Arrange
        PiecePosition currentPosition = new PiecePosition(3, 3);
        Rook rook = new Rook(PieceColor.WHITE, currentPosition);
        PiecePosition newPosition = new PiecePosition(3, 6);
        List<Piece> piecesListOnBoard = new ArrayList<>();

        // Act
        boolean result = rook.checkObstacle(newPosition, piecesListOnBoard);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testCheckObstacle_ObstacleInPath_Invalid() {
        // Arrange
        PiecePosition currentPosition = new PiecePosition(3, 3);
        Rook rook = new Rook(PieceColor.WHITE, currentPosition);
        PiecePosition newPosition = new PiecePosition(3, 6);
        List<Piece> piecesListOnBoard = new ArrayList<>();
        Piece obstaclePiece = new Pawn(PieceColor.WHITE, new PiecePosition(3, 5));
        piecesListOnBoard.add(obstaclePiece);

        // Act
        boolean result = rook.checkObstacle(newPosition, piecesListOnBoard);

        // Assert
        assertFalse(result);
    }
}
