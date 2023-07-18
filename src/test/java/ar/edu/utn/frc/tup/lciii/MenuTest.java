package ar.edu.utn.frc.tup.lciii;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    private Menu menu;
    private ByteArrayInputStream inputStream;

    @BeforeEach
    public void setUp() {
        // Creamos un nuevo Menu con un Scanner que lee datos de un InputStream
        // Utilizaremos ByteArrayInputStream para simular la entrada del usuario en las pruebas
        String input = "1\n4\n"; // Simulamos las opciones 1 y 4 seleccionadas por el usuario
        inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);
        menu = new Menu(scanner);
    }

    @Test
    public void testWelcomeMenu() {
        // Configurar la entrada del usuario
        String input = "4\n"; // Opción válida para salir del menú, seguida de una nueva línea
        InputStream originalInputStream = System.in; // Guardar una referencia al flujo de entrada original

        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            Scanner scanner = new Scanner(System.in); // Crear un nuevo scanner para leer la entrada del usuario
            Menu menu = new Menu(scanner);

            // Verificar que el menú de bienvenida se ejecute sin lanzar una excepción
            assertDoesNotThrow(() -> menu.welcomeMenu());
        } catch (RuntimeException e) {
            fail("Se lanzó una excepción inesperada: " + e.getMessage());
        } finally {
            System.setIn(originalInputStream); // Restaurar el flujo de entrada original
        }
    }

    @Test
    public void testGameMenu() {
        // Verificamos que el menú de juego se ejecute sin errores
        assertDoesNotThrow(() -> menu.gameMenu());
    }

    @Test
    public void testStartNewGame() {
        // Verificamos que el método startNewGame se ejecute sin errores
        assertDoesNotThrow(() -> menu.startNewGame());
    }

    @Test
    public void testLoadGame() {
        // Verificamos que el método loadGame se ejecute sin errores
        assertDoesNotThrow(() -> menu.loadGame());
    }
    @Test
    public void testPlayFinishedGame() {
        // Configurar la salida esperada
        String expectedOutput = "Juegos finalizados disponibles:" + System.lineSeparator() +
                "Juego 1: Jugador 1 vs Jugador 2" + System.lineSeparator() +
                "Juego 2: Jugador 3 vs Jugador 4" + System.lineSeparator() +
                "Juego 3: Jugador 5 vs Jugador 6" + System.lineSeparator() +
                "Ingrese el número del juego que desea volver a jugar: ";

        // Configurar la entrada del usuario
        String input = "2" + System.lineSeparator(); // Número del juego seleccionado, seguido de una nueva línea
        InputStream originalInputStream = System.in; // Guardar una referencia al flujo de entrada original

        try {
            // Redireccionar la salida estándar a un ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            // Configurar la entrada del usuario
            ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStream);
            Scanner scanner = new Scanner(inputStream);
            Menu menu = new Menu(scanner);

            // Ejecutar el método playFinishedGame()
            menu.playFinishedGame();

            // Verificar la salida esperada
            assertEquals(expectedOutput, outputStream.toString());
        } finally {
            System.setIn(originalInputStream); // Restaurar el flujo de entrada original
            System.setOut(System.out); // Restaurar la salida estándar original
        }
    }







    @Test
    public void testHelpMenu() {
        // Configurar la salida esperada
        String expectedOutput = "♔♕♖♗♘♙ AYUDA ♚♛♜♝♞♟" + System.lineSeparator() +
                "1. Cómo jugar" + System.lineSeparator() +
                "2. Reglas del juego" + System.lineSeparator() +
                "3. Contactar al administrador" + System.lineSeparator() +
                "0. Volver al menú principal" + System.lineSeparator() +
                "Volviendo al menú principal..." + System.lineSeparator();

        // Configurar la entrada del usuario
        String input = "0\n"; // Opción válida para volver al menú principal, seguida de una nueva línea
        InputStream originalInputStream = System.in; // Guardar una referencia al flujo de entrada original

        try {
            // Redireccionar la salida estándar a un ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            // Configurar la entrada del usuario
            ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStream);
            Scanner scanner = new Scanner(inputStream);
            Menu menu = new Menu(scanner);

            // Ejecutar el método helpMenu()
            menu.helpMenu();

            // Verificar la salida esperada
            assertEquals(expectedOutput, outputStream.toString());
        } finally {
            System.setIn(originalInputStream); // Restaurar el flujo de entrada original
            System.setOut(System.out); // Restaurar la salida estándar original
        }
    }





}