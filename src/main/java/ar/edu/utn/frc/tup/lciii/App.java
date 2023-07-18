package ar.edu.utn.frc.tup.lciii;

import ar.edu.utn.frc.tup.lciii.controllers.ChessGameController;
import ar.edu.utn.frc.tup.lciii.controllers.PlayerController;
import ar.edu.utn.frc.tup.lciii.models.ChessGame;
import ar.edu.utn.frc.tup.lciii.models.ChessMatch;
import ar.edu.utn.frc.tup.lciii.models.PieceColor;
import ar.edu.utn.frc.tup.lciii.models.Player;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static ar.edu.utn.frc.tup.lciii.Menu.*;

/**
 * Hello to TPI Chess
 *
 */
@Component
@SpringBootApplication
public class App implements CommandLineRunner
{
    /**
     * This is the main program||
     *
     */
    @Autowired
    private PlayerController playerController;
    @Autowired
    private ChessGameController chessGameController;
    @Autowired
    private Scanner scan;

    private static ChessMatch chessMatch = new ChessMatch();
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        Integer option=welcomeMenu();
        Scanner scanner=new Scanner(System.in);
        if(option==1){
            System.out.println("Ingrese el nombre de la partida");
            String gameName=scanner.nextLine();
            System.out.print("Ingresar el nombre del primer jugador: ");
            String namePlayerWhite = scanner.nextLine();
            Player playerWhite = playerController.createPlayer(new Player(namePlayerWhite, PieceColor.WHITE, 0, false));

            System.out.print("Ingresar el nombre del segundo jugador: ");
            String namePlayerBlack = scanner.nextLine();
            Player playerBlack = playerController.createPlayer(new Player(namePlayerBlack, PieceColor.BLACK, 0, false));

            System.out.println("Jugadores creados correctamente:");
            System.out.println("Jugador 1: " + playerWhite.getNamePlayer());
            System.out.println("Jugador 2: " + playerBlack.getNamePlayer());

            Boolean playAgain = true;
            Boolean playerWhiteInGame = true;

            // Bucle principal-match
            do {
                ChessGame chessGame = new ChessGame(playerBlack,playerWhite,gameName);
                Boolean keepPlaying = true;
                Boolean save = false;
                System.out.println("Estamos listos para la guerra!!!");
                // Bucle del juego-games
                do {
                    if(save){
                        System.out.println("¿Desea guardar su partida? (Y/N)");
                        String saveResult = scanner.nextLine();
                        if(saveResult.equalsIgnoreCase("Y")) {
                            chessGameController.saveChessGame(chessGame, playerWhiteInGame);
                        }
                    }
                    if (playerWhiteInGame) {
                        System.out.println("TURNO DEL BLANCO");
                        keepPlaying = chessGame.getPlayerMovement(playerWhite,playerBlack);
                        chessGame.getBoard();
                        playerWhiteInGame = false;
                        save = true;
                    } else {
                        System.out.println("TURNO DEL NEGRO");
                        keepPlaying = chessGame.getPlayerMovement(playerBlack,playerWhite);
                        chessGame.getBoard();
                        playerWhiteInGame = true;
                    }
                } while (!chessGame.isFinish() && keepPlaying);
                chessGame.calculateScores();
                chessMatch.goodbyeMessage();
                playAgain = chessMatch.wantPlayAgain();
            } while (playAgain);
        }else if(option==2){
            System.out.println("Seleccione el numero de partida que desea Jugar");

            List<ChessGame> chessGameList=chessGameController.getAllChessGame();
            for (ChessGame chessGame : chessGameList) {
                System.out.println(chessGame.getId()+"-"+chessGame.getGameName()+"-"+chessGame.getGameDate());
            }
            Long selector=scanner.nextLong();
            Boolean playAgain = true;

            // Bucle principal-match
            do {
                ChessGame chessGame = chessGameController.getGameById(selector);;
                Boolean keepPlaying = true;
                Boolean playerWhiteInGame = true;


                System.out.println("Estamos listos para la guerra!!!");
                // Bucle del juego-games
                do {
                    if (playerWhiteInGame) {
                        System.out.println("TURNO DEL BLANCO");
                        keepPlaying = chessGame.getPlayerMovement(playerController.getPlayerById(chessGame.getPlayerA().getId()),playerController.getPlayerById(chessGame.getPlayerB().getId()));
                        chessGame.getBoard();
                        playerWhiteInGame = false;
                    } else {
                        System.out.println("TURNO DEL NEGRO");
                        keepPlaying = chessGame.getPlayerMovement(playerController.getPlayerById(chessGame.getPlayerB().getId()),playerController.getPlayerById(chessGame.getPlayerA().getId()));
                        chessGame.getBoard();
                        playerWhiteInGame = true;
                    }
                    System.out.println("¿Desea guardar su partida? (Y/N)");
                    String saveResult = scanner.nextLine();
                    if(saveResult.equals("Y"))
                    {
                        chessGameController.saveChessGame(chessGame,playerWhiteInGame);
                    }
                } while (!chessGame.isFinish() && keepPlaying);
                chessGame.calculateScores();
                chessMatch.goodbyeMessage();
                playAgain = chessMatch.wantPlayAgain();
            } while (playAgain);



        }

    }
    public Integer welcomeMenu(){
        Integer option;

        System.out.println("1. Iniciar un nuevo Juego");
        System.out.println("2. Cargar Partida");
        System.out.println("3. Ayuda");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
        option = scan.nextInt();
        return option;
    }
}