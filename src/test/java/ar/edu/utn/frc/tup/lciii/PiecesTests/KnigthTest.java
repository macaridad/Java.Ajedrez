package ar.edu.utn.frc.tup.lciii.PiecesTests;

import ar.edu.utn.frc.tup.lciii.models.Piece;
import ar.edu.utn.frc.tup.lciii.models.PieceColor;
import ar.edu.utn.frc.tup.lciii.models.PiecePosition;
import ar.edu.utn.frc.tup.lciii.models.pieces.Knight;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KnigthTest {
    @Test
    public void testMovementValid_ValidMove_ReturnsTrue() {
        PiecePosition position = new PiecePosition(4, 4);
        Knight knight = new Knight(PieceColor.WHITE, position);

        PiecePosition newPosition = new PiecePosition(6, 5);
        List<Piece> piecesList = new ArrayList<>();

        assertTrue(knight.movementValid(newPosition, piecesList));
    }

    @Test
    public void testMovementValid_InvalidMove_ReturnsFalse() {
        PiecePosition position = new PiecePosition(4, 4);
        Knight knight = new Knight(PieceColor.WHITE, position);

        PiecePosition newPosition = new PiecePosition(7, 7);
        List<Piece> piecesList = new ArrayList<>();

        assertFalse(knight.movementValid(newPosition, piecesList));
    }


}
