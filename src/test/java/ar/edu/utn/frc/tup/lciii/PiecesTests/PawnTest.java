package ar.edu.utn.frc.tup.lciii.PiecesTests;

import ar.edu.utn.frc.tup.lciii.models.Piece;
import ar.edu.utn.frc.tup.lciii.models.PieceColor;
import ar.edu.utn.frc.tup.lciii.models.PiecePosition;
import ar.edu.utn.frc.tup.lciii.models.pieces.Pawn;
import ar.edu.utn.frc.tup.lciii.models.pieces.Rook;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {
    @Mock
    private List<Piece> piecesListInBoard;

    @Test
    public void testMovementValid_WhitePawn_MoveForwardOneSquare_Valid() {
        // Arrange
        PiecePosition currentPosition = new PiecePosition(6, 3);
        Pawn pawn = new Pawn(PieceColor.WHITE, currentPosition);
        PiecePosition newPosition = new PiecePosition(5, 3);
        List<Piece> piecesListOnBoard = new ArrayList<>();

        // Act
        boolean result = pawn.movementValid(newPosition, piecesListOnBoard);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testMovementValid_WhitePawn_MoveForwardTwoSquares_Valid() {
        // Arrange
        PiecePosition currentPosition = new PiecePosition(6, 3);
        Pawn pawn = new Pawn(PieceColor.WHITE, currentPosition);
        PiecePosition newPosition = new PiecePosition(4, 3);
        List<Piece> piecesListOnBoard = new ArrayList<>();

        // Act
        boolean result = pawn.movementValid(newPosition, piecesListOnBoard);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testMovementValid_WhitePawn_MoveForwardOneSquareWithCapture_Valid() {
        // Arrange
        PiecePosition currentPosition = new PiecePosition(6, 3);
        Pawn pawn = new Pawn(PieceColor.WHITE, currentPosition);
        PiecePosition newPosition = new PiecePosition(5, 4);
        List<Piece> piecesListOnBoard = new ArrayList<>();
        Piece enemyPiece = new Rook(PieceColor.BLACK, new PiecePosition(5, 4));
        piecesListOnBoard.add(enemyPiece);

        // Act
        boolean result = pawn.movementValid(newPosition, piecesListOnBoard);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testMovementValid_BlackPawn_MoveForwardOneSquare_Valid() {
        // Arrange
        PiecePosition currentPosition = new PiecePosition(1, 3);
        Pawn pawn = new Pawn(PieceColor.BLACK, currentPosition);
        PiecePosition newPosition = new PiecePosition(2, 3);
        List<Piece> piecesListOnBoard = new ArrayList<>();

        // Act
        boolean result = pawn.movementValid(newPosition, piecesListOnBoard);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testMovementValid_BlackPawn_MoveForwardTwoSquares_Valid() {
        // Arrange
        PiecePosition currentPosition = new PiecePosition(1, 3);
        Pawn pawn = new Pawn(PieceColor.BLACK, currentPosition);
        PiecePosition newPosition = new PiecePosition(3, 3);
        List<Piece> piecesListOnBoard = new ArrayList<>();

        // Act
        boolean result = pawn.movementValid(newPosition, piecesListOnBoard);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testMovementValid_BlackPawn_MoveForwardOneSquareWithCapture_Valid() {
        // Arrange
        PiecePosition currentPosition = new PiecePosition(1, 3);
        Pawn pawn = new Pawn(PieceColor.BLACK, currentPosition);
        PiecePosition newPosition = new PiecePosition(2, 4);
        List<Piece> piecesListOnBoard = new ArrayList<>();
        Piece enemyPiece = new Rook(PieceColor.WHITE, new PiecePosition(2, 4));
        piecesListOnBoard.add(enemyPiece);

        // Act
        boolean result = pawn.movementValid(newPosition, piecesListOnBoard);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testMovementValid_InvalidMove_DifferentRowColumn_Invalid() {
        // Arrange
        PiecePosition currentPosition = new PiecePosition(6, 3);
        Pawn pawn = new Pawn(PieceColor.WHITE, currentPosition);
        PiecePosition newPosition = new PiecePosition(4, 5);
        List<Piece> piecesListOnBoard = new ArrayList<>();

        // Act
        boolean result = pawn.movementValid(newPosition, piecesListOnBoard);

        // Assert
        assertFalse(result);
    }


}