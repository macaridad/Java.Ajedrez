package ar.edu.utn.frc.tup.lciii;
import ar.edu.utn.frc.tup.lciii.models.Piece;
import ar.edu.utn.frc.tup.lciii.models.PieceColor;
import ar.edu.utn.frc.tup.lciii.models.PiecePosition;
import ar.edu.utn.frc.tup.lciii.models.PieceStatus;
import ar.edu.utn.frc.tup.lciii.models.pieces.King;
import ar.edu.utn.frc.tup.lciii.models.pieces.Rook;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    @Test
    void constructor_withColorAndPosition_setsFieldsCorrectly() {
        // Arrange
        PieceColor color = PieceColor.BLACK;
        PiecePosition position = new PiecePosition(2, 3);

        // Act
        Piece piece = new TestPiece(color, position);

        // Assert
        assertEquals(color, piece.getColor());
        assertEquals(position, piece.getPosition());
        assertEquals(PieceStatus.ONBOARD, piece.getStatus());
    }

    @Test
    void movement_withSamePosition_returnsFalse() {
        // Arrange
        PieceColor color = PieceColor.BLACK;
        PiecePosition position = new PiecePosition(2, 3);
        Piece piece = new TestPiece(color, position);

        // Act
        boolean result = piece.movement(position);

        // Assert
        assertFalse(result);
        assertEquals(position, piece.getPosition());
    }

    @Test
    void movement_withDifferentPosition_updatesPositionAndReturnsTrue() {
        // Arrange
        PieceColor color = PieceColor.BLACK;
        PiecePosition initialPosition = new PiecePosition(2, 3);
        PiecePosition newPosition = new PiecePosition(4, 5);
        Piece piece = new TestPiece(color, initialPosition);

        // Act
        boolean result = piece.movement(newPosition);

        // Assert
        assertTrue(result);
        assertEquals(newPosition, piece.getPosition());
    }

    @Test
    void movementValid_withNoPiecesOnBoard_returnsTrue() {
        // Arrange
        PieceColor color = PieceColor.BLACK;
        PiecePosition position = new PiecePosition(2, 3);
        Piece piece = new TestPiece(color, position);
        List<Piece> piecesListOnBoard = new ArrayList<>();

        // Act
        boolean result = ((TestPiece) piece).movementValid(new PiecePosition(4, 5), piecesListOnBoard);

        // Assert
        assertTrue(result);
    }

    @Test
    void movementValid_withPiecesOnBoard_returnsFalse() {
        // Arrange
        PieceColor color = PieceColor.BLACK;
        PiecePosition position = new PiecePosition(2, 3);
        Piece piece = new TestPiece(color, position);
        List<Piece> piecesListOnBoard = new ArrayList<>();
        piecesListOnBoard.add(new TestPiece(PieceColor.WHITE, new PiecePosition(4, 5)));

        // Act
        boolean result = ((TestPiece) piece).movementValid(new PiecePosition(4, 5), piecesListOnBoard);

        // Assert
        assertFalse(result);
    }

    // Test piece implementation for testing the abstract class
    private static class TestPiece extends Piece {
        public TestPiece(PieceColor color, PiecePosition position) {
            super(color, position);
        }

        @Override
        protected boolean movementValid(PiecePosition newPosition, List<Piece> piecesListOnBoard) {
            return piecesListOnBoard.isEmpty();
        }
    }
    @Test
    public void testPossibleMovesList() {
        // Crea una instancia de una subclase concreta de Piece (por ejemplo, Rook)
        Piece piece = new Rook(PieceColor.BLACK, new PiecePosition(0, 0));

        // Crea una lista de piezas y agrega las piezas necesarias
        List<Piece> piecesListOnBoard = new ArrayList<>();
        piecesListOnBoard.add(new Rook(PieceColor.WHITE, new PiecePosition(7, 2)));
        piecesListOnBoard.add(new Rook(PieceColor.BLACK, new PiecePosition(0, 7)));
        // Agrega más piezas según sea necesario para el escenario de prueba

        // Llama al método possibleMovesList
        List<PiecePosition> possibleMovesList = piece.possibleMovesList(piecesListOnBoard);

        // Verifica el resultado esperado
        assertEquals(15, possibleMovesList.size());
        assertTrue(possibleMovesList.contains(new PiecePosition(0, 1)));
        assertTrue(possibleMovesList.contains(new PiecePosition(0, 3)));
        assertTrue(possibleMovesList.contains(new PiecePosition(0, 4)));
        // Agrega más verificaciones según sea necesario para el resultado esperado
    }

}