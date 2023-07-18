package ar.edu.utn.frc.tup.lciii.models;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ChessMatch {
    /**
     * Scanner para capturar las entradas del usuario
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Expresion regular para validar respouestas yes/no
     */
    private static final String YES_NO_REGEX = "[yYnN]";

    /**
     * Este método muestra por pantalla un mensaje de bienvenida al juego de chees/ajedrez
     *
     */
    public void welcomeMessage() {
        System.out.println("Bienvenidos al juego de la ajedrez");
    }


    public void goodbyeMessage() {
        System.out.println("gracias por jugar");
    }

    public Player createNewPlayer() {
        Player player = new Player();
        System.out.println("Ingrese su nombre para empezar a jugar: ");
        player.setNamePlayer(scanner.nextLine());
        return player;
    }

    /**
     * Este metodo propone al jugar abandonar la partida y captura
     * la respuesta de los jugadores.
     *
     * @return true si el jugador quiere seguir jugando, false si NO quiere.
     */
    public boolean continuePlaying() {
        Boolean answer = null;
        do {
            System.out.println("¿Querés continuar la partida? (y/n)");
            String input = scanner.nextLine();
            answer = getYesNoAnswer(input);
        } while (answer == null);
        return answer;
    }
    /**
     * Este metodo controla si el usuario quiere volver a jugar o no.
     * Pide por pantalla los datos requeridos para consultar al usuario si quiere volver a jugar
     * y captura las entradas del usuario.
     * Como resultado retorna la respuesta del usuario como un Boolean
     * @return true si el usuario quiere volver a jugar, false si el usuario NO quiere volver a jugar
     * */
    public boolean wantPlayAgain() {
        Boolean answer = null;
        do {
            System.out.println("¿Querés volver a jugar? (y/n)");
            String input = scanner.nextLine();
            answer = getYesNoAnswer(input);
        } while (answer == null);
        return answer;
    }

    /**
     * Este metodo valida que el parametro de entrada input contenga y, Y, n o N.
     * Si el valor es alguno de esos datos, retorna el valor en forma de Boolean,
     * sino, imprime un error por pantalla y retorna null.
     * @param input el String a validar
     * @return true si input contiene y o Y, false si input contiene n o N, null para lo demas.
     */
    private Boolean getYesNoAnswer(String input) {
        Pattern pattern = Pattern.compile(YES_NO_REGEX);
        Boolean answer = null;
        if (pattern.matcher(input).matches()) {
            if (input.toLowerCase().equals("y")) {
                answer = true;
            } else {
                answer = false;
            }
        } else {
            System.out.println("La opción que ingresaste no es válida.");
        }
        return answer;
    }
}



