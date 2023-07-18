package ar.edu.utn.frc.tup.lciii.ModelsTest;


import ar.edu.utn.frc.tup.lciii.models.*;

import ar.edu.utn.frc.tup.lciii.models.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.*;

public class ChessGameTest2 {

    private ChessGame chessGame;
    @Mock
    private Pattern pattern;
    @Mock
    private List<Piece>pieceListInBoard;
    @Mock
    private Board board;

    private Player playerA;
    private Player playerB;
    private Scanner scanner;
    @Mock
    private static String POSITION_INPUT_REGEX;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {

        playerA = new Player("bruno", PieceColor.BLACK,0,false);
        playerB = new Player("nico",PieceColor.WHITE,0,false);
        chessGame = new ChessGame(playerA, playerB,"juego1");
        board=Mockito.mock(Board.class);
        // Crear una lista de piezas con el rey blanco y agregarlo a pieceListInBoard
        pieceListInBoard = new ArrayList<>();
        POSITION_INPUT_REGEX = "[A-Ha-h][1-8]";
        Piece whiteKing = new King(PieceColor.WHITE, new PiecePosition(4, 4));
        pieceListInBoard.add(whiteKing);
        Piece blackKing=new King(PieceColor.BLACK,new PiecePosition(0,4));
        pieceListInBoard.add(blackKing);

        // Establecer la lista de piezas en el tablero de la instancia de ChessGame
        setPieceListInBoard(chessGame, pieceListInBoard);
        // Initialize the pattern field with a mock object
        pattern = Mockito.mock(Pattern.class);

        // Use reflection to set the pattern field in GameTest
        Field patternField = ChessGame.class.getDeclaredField("pattern");
        patternField.setAccessible(true);
        patternField.set(this, pattern);


    }
//    @Test
//    public void testSomeMethod() throws Exception {
//        // Crear un mock de la clase final Pattern
//        Pattern mockPattern = PowerMockito.mock(Pattern.class);
//
//        // Configurar el comportamiento del mock
//        PowerMockito.when(mockPattern.matcher(Mockito.anyString())).thenReturn(Mockito.mock(Matcher.class));
//
//        // Resto de tu código de prueba...
//    }

@Test
    public void testAddNotation() throws Exception {
        // Paso 1: Crear una instancia de ChessGame
        ChessGame chessGame = new ChessGame(playerA,playerB,"juego1");

        // Paso 2: Acceder al método addNotation usando reflexión
        Method addNotationMethod = ChessGame.class.getDeclaredMethod("addNotation", Piece.class, PiecePosition.class, Player.class);
        addNotationMethod.setAccessible(true);

        // Paso 3: Crear objetos Player, Piece y PiecePosition
        Piece selectedPiece = new King(PieceColor.WHITE, new PiecePosition(0, 0));
        PiecePosition newPosition = new PiecePosition(6, 1);


        // Paso 4: Invocar el método addNotation
        addNotationMethod.invoke(chessGame, selectedPiece, newPosition, playerB);

        // Paso 5: Verificar los resultados
        List<Notation> playersMovement = chessGame.getPlayersMovement();
        assertNotNull(playersMovement);
        assertEquals(1, playersMovement.size());

        Notation notation = playersMovement.get(0);
        assertEquals("Kb2", notation.getAttack());
    }

    @Test
    void myKingInCheckTest_PieceCanAttackKing() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method myKingInCheckMethod = ChessGame.class.getDeclaredMethod("myKingInCheck", Piece.class, PiecePosition.class);
        myKingInCheckMethod.setAccessible(true);

        // Crear una instancia de la pieza seleccionada y la nueva posición
        Piece selectedPiece = pieceListInBoard.get(0); // Por ejemplo, la primera pieza de la lista
        PiecePosition newPosition = new PiecePosition(3, 3); // Por ejemplo, una nueva posición válida

        // Modificar pieceListInBoard para que haya una pieza que pueda atacar al rey
        Piece attackingPiece = pieceListInBoard.get(1); // Por ejemplo, la segunda pieza de la lista
        attackingPiece.setPosition(new PiecePosition(3, 3)); // Establecer la posición de la pieza atacante igual a la nueva posición

        // Ejecutar el método myKingInCheck()
        boolean result = (boolean) myKingInCheckMethod.invoke(chessGame, selectedPiece, newPosition);

        // Verificar que el resultado sea el esperado
        assertTrue(result);
    }



    @Test
    void myKingInCheckTest_PieceCannotAttackKing() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method myKingInCheckMethod = ChessGame.class.getDeclaredMethod("myKingInCheck", Piece.class, PiecePosition.class);
        myKingInCheckMethod.setAccessible(true);

        // Crear una instancia de la pieza seleccionada y la nueva posición
        Piece selectedPiece = pieceListInBoard.get(0); // Por ejemplo, la primera pieza de la lista
        PiecePosition newPosition = new PiecePosition(3, 3); // Por ejemplo, una nueva posición válida

        // Modificar pieceListInBoard para que no haya ninguna pieza que pueda atacar al rey

        // Ejecutar el método myKingInCheck()
        boolean result = (boolean) myKingInCheckMethod.invoke(chessGame, selectedPiece, newPosition);

        // Verificar que el resultado sea el esperado
        assertFalse(result);
    }

    @Test
    void enemyKingInCheckTest_PieceCanAttackKing() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method myKingInCheckMethod = ChessGame.class.getDeclaredMethod("enemyKingInCheck", Piece.class, PiecePosition.class);
        myKingInCheckMethod.setAccessible(true);

        // Crear una instancia de la pieza seleccionada y la nueva posición
        Piece selectedPiece = pieceListInBoard.get(0); // Por ejemplo, la primera pieza de la lista
        PiecePosition newPosition = new PiecePosition(3, 3); // Por ejemplo, una nueva posición válida

        // Modificar pieceListInBoard para que haya una pieza que pueda atacar al rey
        Piece attackingPiece = pieceListInBoard.get(1); // Por ejemplo, la segunda pieza de la lista
        attackingPiece.setPosition(new PiecePosition(3, 3)); // Establecer la posición de la pieza atacante igual a la nueva posición

        // Ejecutar el método myKingInCheck()
        boolean result = (boolean) myKingInCheckMethod.invoke(chessGame, selectedPiece, newPosition);

        // Verificar que el resultado sea el esperado
        assertTrue(result);
    }



    @Test
    void enemyKingInCheckTest_PieceCannotAttackKing() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method myKingInCheckMethod = ChessGame.class.getDeclaredMethod("enemyKingInCheck", Piece.class, PiecePosition.class);
        myKingInCheckMethod.setAccessible(true);

        // Crear una instancia de la pieza seleccionada y la nueva posición
        Piece selectedPiece = pieceListInBoard.get(0); // Por ejemplo, la primera pieza de la lista
        PiecePosition newPosition = new PiecePosition(3, 3); // Por ejemplo, una nueva posición válida

        // Modificar pieceListInBoard para que no haya ninguna pieza que pueda atacar al rey

        // Ejecutar el método myKingInCheck()
        boolean result = (boolean) myKingInCheckMethod.invoke(chessGame, selectedPiece, newPosition);

        // Verificar que el resultado sea el esperado
        assertFalse(result);
    }


    // Método de utilidad para establecer la lista de piezas en el tablero de ChessGame mediante reflexión

    @Test
    public void getBoardTest() throws Exception {
        // Crear un objeto mock de la clase Board utilizando PowerMock
        Board boardMock = Mockito.mock(Board.class);

        // Acceder al campo 'board' en la instancia de ChessGame y establecer el objeto mock
        Field boardField = ChessGame.class.getDeclaredField("board");
        boardField.setAccessible(true);
        boardField.set(chessGame, boardMock);

        // Simular el comportamiento del método drawBoard() en el objeto mock
        Mockito.doNothing().when(boardMock).drawBoard();

        // Llamar al método getBoard()
        chessGame.getBoard();

        // Verificar que se haya llamado al método drawBoard() en el objeto mock

        boardMock.drawBoard();
    }

    @Test
    public void captureTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {


        // Crear una instancia de Piece para simular la pieza a capturar
        Piece piece = new Pawn(PieceColor.BLACK,new PiecePosition(1,2));

        // Agregar la pieza a la lista de piezas en el tablero
        List<Piece> pieceListInBoard = new ArrayList<>();
        pieceListInBoard.add(piece);

        // Establecer la lista de piezas en el tablero utilizando reflexión
        Field pieceListInBoardField = ChessGame.class.getDeclaredField("pieceListInBoard");
        pieceListInBoardField.setAccessible(true);
        pieceListInBoardField.set(chessGame, pieceListInBoard);

        // Acceder al método privado utilizando reflexión
        Method captureMethod = ChessGame.class.getDeclaredMethod("capture", Piece.class);
        captureMethod.setAccessible(true);

        // Llamar al método capture()
        captureMethod.invoke(chessGame, piece);

        // Verificar que el estado de la pieza haya cambiado a CAPTURED
        assertEquals(PieceStatus.CAPTURED, piece.getStatus());

        // Verificar que la pieza haya sido removida de la lista de piezas en el tablero
        assertFalse(pieceListInBoard.contains(piece));
    }

    @Test
    public void isKingPieceTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {


        // Crear una lista de piezas en el tablero
        List<Piece> pieceListInBoard = new ArrayList<>();

        // Crear una instancia de King para simular una pieza de rey
        King kingPiece = new King(PieceColor.BLACK,new PiecePosition());
        kingPiece.setColor(PieceColor.WHITE);

        // Agregar la pieza de rey a la lista de piezas en el tablero
        pieceListInBoard.add(kingPiece);

        // Establecer la lista de piezas en el tablero utilizando reflexión
        Field pieceListInBoardField = ChessGame.class.getDeclaredField("pieceListInBoard");
        pieceListInBoardField.setAccessible(true);
        pieceListInBoardField.set(chessGame, pieceListInBoard);

        // Acceder al método privado utilizando reflexión
        Method isKingPieceMethod = ChessGame.class.getDeclaredMethod("isKingPiece", PieceColor.class);
        isKingPieceMethod.setAccessible(true);

        // Llamar al método isKingPiece() con el color de la pieza
        boolean result = (boolean) isKingPieceMethod.invoke(chessGame, PieceColor.WHITE);

        // Verificar que el resultado sea verdadero
        assertTrue(result);
    }


    @Test
    public void isValidPositionTest()throws Exception{
        // Configurar el mock de Pattern utilizando reflexión
        Method isValidPositionTestMethod = ChessGame.class.getDeclaredMethod("isValidPosition", String.class);
        isValidPositionTestMethod.setAccessible(true);

        Matcher matcherMock = Mockito.mock(Matcher.class);
        Mockito.when(pattern.matcher(Mockito.anyString())).thenReturn(matcherMock);
        Mockito.when(matcherMock.matches()).thenReturn(true);

        // Llamar al método isValidPosition utilizando reflexión
        boolean isValid = (boolean) isValidPositionTestMethod.invoke(chessGame, "a4");

        // Verificar que el resultado sea true
        assertTrue(isValid);
    }
    @Test
    public void isInvalidPositionTest()throws Exception{
        // Configurar el mock de Pattern utilizando reflexión
        Method isValidPositionTestMethod=ChessGame.class.getDeclaredMethod("isValidPosition", String.class);
        isValidPositionTestMethod.setAccessible(true);

        Matcher matcherMock = Mockito.mock(Matcher.class);
        Mockito.when(pattern.matcher(Mockito.anyString())).thenReturn(matcherMock);
        Mockito.when(matcherMock.matches()).thenReturn(true);

        // Llamar al método isValidPosition utilizando reflexión
        boolean isValid = (boolean) isValidPositionTestMethod.invoke(chessGame, "J9");
        // Verificar que el resultado sea true
        assertFalse(isValid);
    }
    @Test
    public void getMyKingTest()throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //utilizo reflection para entrar al metodo privado
        Method getMyKingMethod = ChessGame.class.getDeclaredMethod("getMyKing", PieceColor.class);
        getMyKingMethod.setAccessible(true); // Permitir acceso al método privado
        //invocacion del metodo privado
        Piece myKing = (Piece) getMyKingMethod.invoke(chessGame, playerB.getColor());
        pieceListInBoard.add(myKing);
        // Verificar que el rey enemigo sea el esperado
        System.out.println(pieceListInBoard);
        assertNotNull(pieceListInBoard);
        assertNotNull(myKing);
        assertEquals(PieceColor.WHITE, myKing.getColor());
        assertTrue(myKing instanceof King);
    }

    @Test
    public void getEnemyKingTest()throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //utilizo reflection para entrar al metodo privado
        Method getMyKingMethod = ChessGame.class.getDeclaredMethod("getEnemyKing", PieceColor.class);
        getMyKingMethod.setAccessible(true); // Permitir acceso al método privado
        //invocacion del metodo privado
        Piece myKing = (Piece) getMyKingMethod.invoke(chessGame, playerA.getColor());
        pieceListInBoard.add(myKing);
        // Verificar que el rey enemigo sea el esperado
        System.out.println(pieceListInBoard);
        assertNotNull(pieceListInBoard);
        assertNotNull(myKing);
        assertEquals(PieceColor.WHITE, myKing.getColor());
        assertTrue(myKing instanceof King);
    }

    @Test
    void getPositionTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Mock de la entrada del usuario
        String input = "A1";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Crear una instancia de ChessGame
        ChessGame chessGame = new ChessGame(playerA, playerB,"juego1");

        // Obtener la referencia al método privado getPosition()
        Method getPositionMethod = ChessGame.class.getDeclaredMethod("getPosition");
        getPositionMethod.setAccessible(true);

        // Invocar el método getPosition()
        PiecePosition result = (PiecePosition) getPositionMethod.invoke(chessGame);

        // Verificar que la posición obtenida sea la esperada
        assertEquals(7, result.getRow());
        assertEquals(0, result.getColumn());

        // Restaurar la entrada estándar del sistema
        System.setIn(System.in);
    }

    @Test
    void getPlayersMovementTest() {
        // Obtener la lista de movimientos de los jugadores
        List<Notation> result = chessGame.getPlayersMovement();
        // Verificar que la lista esté vacía al inicio
        assertEquals(new ArrayList<Notation>(), result);
    }


    @Test
    public void boxEmptyEnemyPiece() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {

        Method boxEmptyOrEnemyPieceMethod=ChessGame.class.getDeclaredMethod("boxEmptyOrEnemyPiece",Piece.class,PiecePosition.class);
        boxEmptyOrEnemyPieceMethod.setAccessible(true);

        // Crear una instancia de Pawn blanco en la posición (2, 3)
        Piece whitePawn = new Pawn(PieceColor.WHITE, new PiecePosition(2, 3));

        // Agregar la pieza a la lista pieceListInBoard
        pieceListInBoard.add(whitePawn);

        // Establecer la lista de piezas en el tablero de la instancia de ChessGame
        setPieceListInBoard(chessGame, pieceListInBoard);

        // Crear una posición que esté vacía (por ejemplo, (3, 3))
        PiecePosition emptyPosition = new PiecePosition(3, 3);

        // Invocar el método boxEmptyOrEnemyPiece con la pieza seleccionada y la posición vacía
        boolean isEmptyOrEnemyPiece = (boolean) boxEmptyOrEnemyPieceMethod.invoke(chessGame, whitePawn, emptyPosition);

        // Verificar que el resultado sea verdadero, ya que la posición está vacía
        assertTrue(isEmptyOrEnemyPiece);

        // Crear una posición donde haya una pieza enemiga (por ejemplo, (4, 4))
        PiecePosition enemyPosition = new PiecePosition(4, 4);

        // Invocar el método boxEmptyOrEnemyPiece con la pieza seleccionada y la posición con una pieza enemiga
        boolean isEnemyPiece = (boolean) boxEmptyOrEnemyPieceMethod.invoke(chessGame, whitePawn, enemyPosition);

        // Verificar que el resultado sea verdadero, ya que la posición contiene una pieza enemiga
        assertFalse(isEnemyPiece);
    }

    @Test
    public void getPieceAtPositionTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        Method getPieceAtPositionTestMethod=ChessGame.class.getDeclaredMethod("getPieceAtPosition",PiecePosition.class);
        getPieceAtPositionTestMethod.setAccessible(true);
        // Crear una lista de piezas en el tablero
        List<Piece> pieceListInBoard = new ArrayList<>();
        pieceListInBoard.add(new Pawn(PieceColor.WHITE, new PiecePosition(2, 3)));
        pieceListInBoard.add(new Bishop(PieceColor.BLACK, new PiecePosition(4, 4)));

        // Establecer la lista de piezas en el tablero de la instancia de ChessGame
        setPieceListInBoard(chessGame, pieceListInBoard);

        // Crear una posición donde hay una pieza (blanca) en (2, 3)
        PiecePosition piecePosition = new PiecePosition(2, 3);

        // Invocar el método getPieceAtPosition con la posición deseada
        Piece pieceAtPosition = (Piece) getPieceAtPositionTestMethod.invoke(chessGame, piecePosition);
        // Verificar que la pieza devuelta no sea nula
        assertNotNull(pieceAtPosition);

        // Verificar que la pieza devuelta tenga el color blanco
        assertEquals(PieceColor.WHITE, pieceAtPosition.getColor());

        // Crear una posición donde no hay una pieza en el tablero
        PiecePosition emptyPosition = new PiecePosition(5, 5);

        // Invocar el método getPieceAtPosition con la posición vacía
        Piece emptyPiece = (Piece) getPieceAtPositionTestMethod.invoke(chessGame, emptyPosition);

        // Verificar que la pieza devuelta sea nula
        assertNull(emptyPiece);
    }

    private void setPieceListInBoard(ChessGame chessGame, List<Piece> pieceListInBoard)throws NoSuchFieldException, IllegalAccessException {
        // Obtener el campo privado pieceListInBoard utilizando reflexión
        Field pieceListField = ChessGame.class.getDeclaredField("pieceListInBoard");
        pieceListField.setAccessible(true); // Permitir acceso al campo privado

        // Establecer la lista de piezas en el tablero
        pieceListField.set(chessGame, pieceListInBoard);
    }


    @Test
    public void getMapTest()throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        Method getMapTestMethod=ChessGame.class.getDeclaredMethod("getMap");
        getMapTestMethod.setAccessible(true);
        // Invocar el método getMap para obtener el resultado
        Map<String, Integer> mapping = (Map<String, Integer>) getMapTestMethod.invoke(chessGame);
        // Verificar que el mapeo generado sea correcto
        assertEquals(16, mapping.size());
        assertEquals(0, (int) mapping.get("A"));
        assertEquals(1, (int) mapping.get("B"));
        assertEquals(2, (int) mapping.get("C"));
        assertEquals(3, (int) mapping.get("D"));
        assertEquals(4, (int) mapping.get("E"));
        assertEquals(5, (int) mapping.get("F"));
        assertEquals(6, (int) mapping.get("G"));
        assertEquals(7, (int) mapping.get("H"));

    }

}

