package ar.edu.utn.frc.tup.lciii.PiecesTests;

import ar.edu.utn.frc.tup.lciii.models.Piece;
import ar.edu.utn.frc.tup.lciii.models.PieceColor;
import ar.edu.utn.frc.tup.lciii.models.PiecePosition;
import ar.edu.utn.frc.tup.lciii.models.pieces.Bishop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class BishopTest {
    @Mock
    private PiecePosition position;
    private Bishop bishopTest;

    /**
     * Inicializa los objetos mock necesarios y crear una instancia de la clase bajo prueba
     * antes de cada prueba individual. Esto garantiza que cada prueba comience
     * con un estado limpio y controlado.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bishopTest = new Bishop(PieceColor.WHITE, position);
    }

    /**
     * esta prueba verifica que el método movementValid() de la clase Bishop
     * funcione correctamente al validar una posición válida para el movimiento
     * de la pieza.
     */
    @Test
    public void testMovementValid_ValidPosition() {
        PiecePosition newPosition = new PiecePosition(3, 3);
        List<Piece> piecesListOnBoard = new ArrayList<>();

        Mockito.when(position.getRow()).thenReturn(1);
        Mockito.when(position.getColumn()).thenReturn(1);

        assertTrue(bishopTest.movementValid(newPosition, piecesListOnBoard));
    }

    /**
     * esta prueba verifica que el método movementValid() de la clase Bishop
     * funcione correctamente al identificar y rechazar un movimiento inválido
     * cuando hay un obstáculo en la posición de destino.
     */
    @Test
    public void testMovementValid_InvalidPosition_Obstacle() {
        PiecePosition newPosition = new PiecePosition(3, 3);
        List<Piece> piecesListOnBoard = new ArrayList<>();

        Piece obstaclePiece = Mockito.mock(Piece.class); // Creamos un mock de Piece en lugar de una instancia real
        Mockito.when(obstaclePiece.getPosition()).thenReturn(new PiecePosition(2, 2)); // Configuramos el mock para devolver la posición del obstáculo
        piecesListOnBoard.add(obstaclePiece);

        PiecePosition currentPosition = Mockito.mock(PiecePosition.class);
        Mockito.when(currentPosition.getRow()).thenReturn(1);
        Mockito.when(currentPosition.getColumn()).thenReturn(1);

        Bishop bishopTest = new Bishop(PieceColor.WHITE, currentPosition); // Creamos una instancia de la clase Bishop

        assertFalse(bishopTest.movementValid(newPosition, piecesListOnBoard));
    }

    /**
     * esta prueba verifica que el método movementValid() de la clase Bishop funcione correctamente al
     * identificar y rechazar un movimiento inválido cuando la posición de destino no está en una
     * dirección diagonal desde la posición actual de la pieza.
     */
    @Test
    public void testMovementValid_InvalidPosition_NotDiagonal() {
        PiecePosition newPosition = new PiecePosition(3, 4);
        List<Piece> piecesListOnBoard = new ArrayList<>();

        Mockito.when(position.getRow()).thenReturn(1);
        Mockito.when(position.getColumn()).thenReturn(1);

        assertFalse(bishopTest.movementValid(newPosition, piecesListOnBoard));
    }



}

