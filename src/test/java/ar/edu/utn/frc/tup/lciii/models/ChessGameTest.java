package ar.edu.utn.frc.tup.lciii.models;
import ar.edu.utn.frc.tup.lciii.models.pieces.*;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ChessGameTest {

    private ChessGame chessGame;
    private Player player;
    private Integer row;

    @Before
    public void setUp() {
        // Inicializa el objeto ChessGame con los datos necesarios para la prueba
        chessGame = new ChessGame();
        player = new Player();
        player.setColor(PieceColor.WHITE);
        row = 0;
    }

    @Test
    public void testInitializePieces() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Obtiene la referencia al método privado 'inicializePieces' mediante reflexión
        Method initializePiecesMethod = ChessGame.class.getDeclaredMethod("inicializePieces", Player.class, Integer.class);
        initializePiecesMethod.setAccessible(true); // Permite el acceso al método privado

        // Invoca el método privado
        initializePiecesMethod.invoke(chessGame, player, row);

        // Verifica que las piezas se hayan inicializado correctamente en pieceListInBoard
        List<Piece> expectedPieces = new ArrayList<>();
        expectedPieces.add(new Queen(PieceColor.WHITE, new PiecePosition(row, 3)));
        expectedPieces.add(new King(PieceColor.WHITE, new PiecePosition(row, 4)));
        expectedPieces.add(new Rook(PieceColor.WHITE, new PiecePosition(row, 0)));
        expectedPieces.add(new Knight(PieceColor.WHITE, new PiecePosition(row, 1)));
        expectedPieces.add(new Bishop(PieceColor.WHITE, new PiecePosition(row, 2)));
        expectedPieces.add(new Bishop(PieceColor.WHITE, new PiecePosition(row, 5)));
        expectedPieces.add(new Knight(PieceColor.WHITE, new PiecePosition(row, 6)));
        expectedPieces.add(new Rook(PieceColor.WHITE, new PiecePosition(row, 7)));

        for (int i = 0; i < 8; i++) {
            if (player.getColor() == PieceColor.BLACK) {
                expectedPieces.add(new Pawn(PieceColor.BLACK, new PiecePosition(row + 1, i)));
            } else {
                expectedPieces.add(new Pawn(PieceColor.WHITE, new PiecePosition(row - 1, i)));
            }
        }

        List<Piece> actualPieces = chessGame.getPieceListInBoard();

        // Realiza la aserción para verificar que las piezas esperadas coincidan con las piezas reales
        assertEquals(expectedPieces.size(), actualPieces.size());

        for (int i = 0; i < expectedPieces.size(); i++) {
            Piece expectedPiece = expectedPieces.get(i);
            Piece actualPiece = actualPieces.get(i);
            assertEquals(expectedPiece.getClass(), actualPiece.getClass());
            assertEquals(expectedPiece.getColor(), actualPiece.getColor());
            assertEquals(expectedPiece.getPosition().getRow(), actualPiece.getPosition().getRow());
            assertEquals(expectedPiece.getPosition().getColumn(), actualPiece.getPosition().getColumn());
        }
    }
}