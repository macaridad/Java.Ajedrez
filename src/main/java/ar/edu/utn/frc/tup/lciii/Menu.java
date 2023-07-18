package ar.edu.utn.frc.tup.lciii;
import static ar.edu.utn.frc.tup.lciii.App.*;

import java.io.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import ar.edu.utn.frc.tup.lciii.models.ChessMatch;
import ar.edu.utn.frc.tup.lciii.models.Player;
import lombok.Data;

@Data
public class Menu {
     private static ChessMatch chessMatch = new ChessMatch();
     private static Player player;
     private static  Archivo archivo = new Archivo();
     private static App app;
     private Scanner scanner;
    public Menu(Scanner scanner) {
        this.scanner=scanner;
    }
    public void welcomeMenu() {
        int option;
        do {
            try {
                System.out.println("♔♕♖♗♘♙ Bienvenidos al Menú del Juego de Ajedrez ♚♛♜♝♞♟");
                System.out.println("1. Juego");
                System.out.println("2. Jugadores");
                System.out.println("3. Ayuda");
                System.out.println("4. Salir");
                System.out.print("Seleccione una opción: ");
                option = scanner.nextInt();

                switch(option) {
                    case 1:
                        gameMenu();
                        break;
                    case 2:
                        playersMenu();
                        break;
                    case 3:
                        helpMenu();
                        break;
                    case 4:
                        System.out.println("¡Hasta la próxima, esperamos que lo hayas disfrutado!");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                }
            } catch (NoSuchElementException e) {
                System.err.println("Error: No se pudo leer la entrada del usuario. " + e.getMessage());
                // Opcionalmente, puedes lanzar una nueva excepción personalizada para propagar el error
                throw new RuntimeException("Error en la lectura de la entrada del usuario", e);
            }

            System.out.println();
        } while(option != 4);
    }

            public  void  gameMenu() {
              
               boolean exit = false;
                while(!exit) {
                    System.out.println(" ♔♕♖♗♘♙ Menú de Juego ♚♛♜♝♞♟");
                    System.out.println("1. Iniciar un juego nuevo");
                    System.out.println("2. Cargar un juego en curso");
                    System.out.println("3. Jugar un juego finalizado");
                    System.out.println("4. Volver al menú principal");
                    System.out.print("Seleccione una opción: ");
                    int option = scanner.nextInt();
                    scanner.nextLine();

                    switch(option) {
                        case 1:
                            startNewGame();
                            break;
                        case 2:
                            loadGame();
                            break;
                        case 3:
                            playFinishedGame();
                            break;
                        case 4:
                            System.out.println("Volviendo al menú principal...");
                            exit = true;
                            break;
                        default:
                            System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    }

                    System.out.println();
                }

            }

            public  void startNewGame() {

            }

            public  void loadGame() {
                System.out.println("Cargando juegos en curso...");
                System.out.println("Juego 1: Jugador 1 vs Jugador 2");
                System.out.println("Juego 2: Jugador 3 vs Jugador 4");
                System.out.println("Juego 3: Jugador 5 vs Jugador 6");
            }

            public  void playFinishedGame() {

                System.out.println("Juegos finalizados disponibles:");
                System.out.println("Juego 1: Jugador 1 vs Jugador 2");
                System.out.println("Juego 2: Jugador 3 vs Jugador 4");
                System.out.println("Juego 3: Jugador 5 vs Jugador 6");
                System.out.print("Ingrese el número del juego que desea volver a jugar: ");}

            public  void playersMenu() {

                boolean exit = false;

                while (!exit) {
                    System.out.println(" ♔♕♖♗♘♙JUGADORES ♚♛♜♝♞♟");
                    System.out.println("1. Crear nuevo jugador");
                    System.out.println("2. Lista de todos los jugadores");
                    System.out.println("3. Puntuaciones de los jugadores");
                    System.out.println("4. Actualizar jugador");
                    System.out.println("5. Eliminar jugador");
                    System.out.println("0. Volver al menú principal");

                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            Player player = chessMatch.createNewPlayer();
                            break;
                        case 2:
                            break;
                        case 3:
                            //listPlayersScore();
                            break;
                        case 4:
                            //updatePlayer();
                            break;
                        case 5:
                            //deletePlayer();
                            break;
                        case 0:
                            System.out.println("Volviendo al menú principal...");
                            exit = true;
                            break;
                        default:
                            System.out.println("Opción no válida. Intente de nuevo.");
                    }
                }
            }


            public  void helpMenu() {

                boolean exit = false;

                while (!exit) {
                    System.out.println("♔♕♖♗♘♙ AYUDA ♚♛♜♝♞♟");
                    System.out.println("1. Cómo jugar");
                    System.out.println("2. Reglas del juego");
                    System.out.println("3. Contactar al administrador");
                    System.out.println("0. Volver al menú principal");

                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            String t = archivo.read("comoSeJuega.txt");
                            System.out.println(t);


                            break;
                        case 2:
                            String l = archivo.read("reglasDelAjedrez.txt");
                            System.out.println(l);
                            break;
                        case 3:
                            System.out.println("Para contactarse con un administrador ingrese su correo electrónico");
                            scanner.nextLine();
                            System.out.println("Ingrese el mensaje/comentario sugerencia que quiere enviar: ");
                            scanner.nextLine();
                            break;
                        case 0:
                            System.out.println("Volviendo al menú principal...");
                            exit = true;
                            break;
                        default:
                            System.out.println("Opción no válida. Intente de nuevo.");
                    }
                }

            }

}
