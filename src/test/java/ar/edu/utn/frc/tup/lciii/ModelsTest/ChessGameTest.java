package ar.edu.utn.frc.tup.lciii.ModelsTest;
import ar.edu.utn.frc.tup.lciii.models.*;
import ar.edu.utn.frc.tup.lciii.models.pieces.King;
import ar.edu.utn.frc.tup.lciii.models.pieces.Pawn;
import ar.edu.utn.frc.tup.lciii.models.pieces.Rook;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChessGameTest {
    @Mock
    private ChessGame chessGame;
    private Player playerA;
    private Player playerB;

    @BeforeEach
    public void setUp() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        playerA = new Player("Player A", PieceColor.WHITE,0,false);
        playerB = new Player("Player B", PieceColor.BLACK,0,false);
        chessGame = new ChessGame(playerA, playerB, "juego1");
        Method initBoardMethod = ChessGame.class.getDeclaredMethod("initBoard");
        initBoardMethod.setAccessible(true);
        initBoardMethod.invoke(chessGame);
    }


    @Test
    public void testIsValidPosition() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ChessGame chessGame = new ChessGame();
        Class<?> chessGameClass = chessGame.getClass();
        Method isValidPositionMethod = chessGameClass.getDeclaredMethod("isValidPosition", String.class);
        isValidPositionMethod.setAccessible(true); // Permitir el acceso al método privado

        // Llamar al método privado utilizando reflexión
        boolean result = (boolean) isValidPositionMethod.invoke(chessGame, "A1");

        assertTrue(result);
    }


    @Test
    public void testGetPieceAtPosition() throws Exception {
        Field field = ChessGame.class.getDeclaredField("pieceListInBoard");
        field.setAccessible(true);

// Crea una lista de piezas para realizar las pruebas
        List<Piece> pieceList = new ArrayList<>();
        pieceList.add(new Pawn(PieceColor.WHITE, new PiecePosition(6, 0)));
        pieceList.add(new Pawn(PieceColor.WHITE, new PiecePosition(6, 1)));
        pieceList.add(new Pawn(PieceColor.WHITE, new PiecePosition(6, 2)));

// Establece la lista de piezas en el campo "pieceListInBoard" de la instancia de ChessGame
        field.set(chessGame, pieceList);

        // Llama al método getPieceAtPosition utilizando reflexión y verifica el resultado
        Method method = ChessGame.class.getDeclaredMethod("getPieceAtPosition", PiecePosition.class);
        method.setAccessible(true);

        // Prueba 1: Posición existente en la lista de piezas
        PiecePosition position1 = new PiecePosition(6, 1);
        Piece result1 = (Piece) method.invoke(chessGame, position1);
        assertEquals(pieceList.get(7), result1);


    }

    @Test
    public void testAddNotation() {
        PiecePosition newPosition = new PiecePosition(2, 4);
        Piece selectedPiece = new Rook(PieceColor.WHITE,newPosition);
        List<Notation> expectedNotations = new ArrayList<>();
        Notation notation=new Notation("Rd4","Qb6");
        expectedNotations.add(notation);
        chessGame.setNotation(notation);


        Assert.assertEquals(expectedNotations, chessGame.getPlayersMovement());


    }

    @Test
    public void testBoxEmptyOrEnemyPiece() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ChessGame chessGame = new ChessGame();
        Class<?> chessGameClass = chessGame.getClass();
        Method boxEmptyOrEnemyPieceMethod = chessGameClass.getDeclaredMethod("boxEmptyOrEnemyPiece", Piece.class, PiecePosition.class);
        boxEmptyOrEnemyPieceMethod.setAccessible(true); // Permitir el acceso al método privado

        // Crear una instancia de Piece y PiecePosition para pasar como argumentos
        Piece selectedPiece = new King();
        selectedPiece.setColor(PieceColor.BLACK);
        PiecePosition newPosition = new PiecePosition(2, 3);

        // Llamar al método privado utilizando reflexión
        boolean result = (boolean) boxEmptyOrEnemyPieceMethod.invoke(chessGame, selectedPiece, newPosition);

        assertTrue(result);
    }


    // Add more test methods for other methods in ChessGame class

}