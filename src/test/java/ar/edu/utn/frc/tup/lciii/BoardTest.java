package ar.edu.utn.frc.tup.lciii;
import ar.edu.utn.frc.tup.lciii.models.Board;
import ar.edu.utn.frc.tup.lciii.models.PieceColor;
import ar.edu.utn.frc.tup.lciii.models.PiecePosition;
import ar.edu.utn.frc.tup.lciii.models.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {


    @Test
    public void testMovementOnBoard_PieceMovedToNewPosition() {
        // Arrange
        Board board = new Board();
        board.initBoard(new ArrayList<>());
        PiecePosition actualPosition = new PiecePosition(1, 0);
        PiecePosition newPosition = new PiecePosition(2, 0);
        Player player=new Player("bruno", PieceColor.BLACK,0,false);

        // Act
        board.movementOnBoard(player, actualPosition, newPosition);

        // Assert
        assertEquals("  PAWN  ", board.getBoard()[2][0]);
        assertEquals("      ", board.getBoard()[1][0]);
    }
}