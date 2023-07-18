package ar.edu.utn.frc.tup.lciii.models;

import ar.edu.utn.frc.tup.lciii.models.pieces.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChessGame {
    private Long id;
    private  String POSITION_INPUT_REGEX = "[A-Ha-h][1-8]";
    private  Scanner scanner = new Scanner(System.in);
    private Player playerA;
    private String gameName;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")//formateo de fechas
    private LocalDateTime gameDate;
    private Player playerB;
    private  Board board;
    private List<Notation> playersMovement;
    private List<Piece> pieceListInBoard;
    private Notation notation;

    public ChessGame(Player playerBlack, Player playerWhite,String gameName) {
        this.gameName=gameName;
        this.gameDate=LocalDateTime.now();
        this.playerA = playerBlack;
        this.playerB = playerWhite;
        this.board = new Board();
        this.playersMovement = new ArrayList<>();
        this.pieceListInBoard = new ArrayList<>();
        inicializePieces(playerBlack, 0);
        inicializePieces(playerWhite,7);
        this.notation = null;
        this.board.initBoard(pieceListInBoard);
    }

    public List<Notation> getPlayersMovement() {
        return playersMovement;
    }

    private Boolean isValidPosition(String input) {
        Pattern pattern = Pattern.compile(POSITION_INPUT_REGEX);
        return pattern.matcher(input).matches();
    }
    private void inicializePieces(Player player, Integer row) {
        Piece playerQueen = new Queen(player.getColor(), new PiecePosition(row, 3));
        Piece playerKing = new King(player.getColor(), new PiecePosition(row, 4));
        Piece playerRookLeft = new Rook(player.getColor(), new PiecePosition(row, 0));
        Piece playerKnightLeft = new Knight(player.getColor(), new PiecePosition(row, 1));
        Piece playerBishopLeft = new Bishop(player.getColor(), new PiecePosition(row, 2));
        Piece playerBishopRight = new Bishop(player.getColor(), new PiecePosition(row, 5));
        Piece playerKnightRight = new Knight(player.getColor(), new PiecePosition(row, 6));
        Piece playerRookRight = new Rook(player.getColor(), new PiecePosition(row, 7));
        pieceListInBoard.addAll(List.of(playerQueen, playerKing, playerRookLeft, playerKnightLeft, playerBishopLeft,
                playerBishopRight, playerKnightRight, playerRookRight));

        for (int i = 0; i < 8; i++) {
            if (player.getColor()== PieceColor.BLACK) {
                Piece playerPawn = new Pawn(player.getColor(), new PiecePosition(row+1, i));
                pieceListInBoard.add(playerPawn);
            } else {
                Piece playerPawn = new Pawn(player.getColor(), new PiecePosition(row-1, i));
                pieceListInBoard.add(playerPawn);
            }
        }
    }
    public void getBoard() {
        board.drawBoard();
    }

    public Boolean getPlayerMovement(Player currentPlayer, Player enemyPlayer) {

        do {
            do {
                System.out.println("Seleccione la pieza que desea mover");
                PiecePosition positionToSearch=getPosition();
                Piece selectedPiece=getPieceAtPosition(positionToSearch);
                if(selectedPiece!=null && selectedPiece.getColor()==currentPlayer.getColor()){
                    PiecePosition newPosition=getPosition();

                    if(boxEmptyOrEnemyPiece(selectedPiece,newPosition)){

                        if (selectedPiece.movementValid(newPosition, pieceListInBoard)) {

                            if (!myKingInCheck(selectedPiece, newPosition)) {
                                if (enemyKingInCheck(selectedPiece, newPosition)) {
                                    System.out.println("EL REY " + getEnemyKing(selectedPiece.color).getColor() + " ESTÁ EN JAQUE!!!");
                                    enemyPlayer.setKingInCheck(true);

                                } else {
                                    enemyPlayer.setKingInCheck(false);
                                }

                                if (getPieceAtPosition(newPosition)!= null && getPieceAtPosition(newPosition).getColor() != selectedPiece.getColor()) {
                                    capture(getPieceAtPosition(newPosition));
                                }

                                selectedPiece.movement(newPosition); //mueve la pieza en la parte logica
                                board.movementOnBoard(currentPlayer,positionToSearch,newPosition);//mueve la pieza en la parte grafica
                                addNotation(selectedPiece, newPosition, currentPlayer);

                                if (enemyPlayer.isKingInCheck()) {
                                    if (checkMate(enemyPlayer)) {
                                        return false;
                                    }
                                }
                                return true;

                            } else {
                                System.out.println("MOVIMIENTO INVALIDO, TU REY QUEDA EN JAQUE, REALICE OTRA JUGADA");
                            }
                        } else {
                            System.out.println("MOVIMIENTO INVALIDO");
                        }
                    } else {
                        System.out.println("MOVIMIENTO INVALIDO - LE POSICIÓN ESTÁ OCUPADA POR UNA PIEZA DE TU COLOR");
                    }
                } else{
                    System.out.println("LA POSICIÓN SELECCIONADA NO CONTINE UNA PIEZA DE TU COLOR");
                }
            } while (currentPlayer.isKingInCheck());
        } while (true);

    }
    public boolean isCheckmate(Player player) {
        if (!player.isKingInCheck()) {
            return false; // El rey del jugador no está en jaque, no hay jaque mate
        }
        for (Piece piece : pieceListInBoard) {
            if (piece.getColor() == player.getColor()) {
                for (int row = 0; row < 8; row++) {
                    for (int column = 0; column < 8; column++) {
                        PiecePosition newPosition = new PiecePosition(row, column);
                        if (piece.movementValid(newPosition, pieceListInBoard) && !myKingInCheck(piece, newPosition)) {
                            // Si hay una jugada válida que no pone al rey en jaque, no hay jaque mate
                            return false;
                        }
                    }
                }
            }
        }

        return true; // No hay jugadas válidas que eviten el jaque mate
    }
    private void addNotation(Piece selectedPiece, PiecePosition newPosition, Player player){
        Map<String, String> mapPieces = new HashMap<>();
        mapPieces.put(TypeOfPiece.KING.name(), "K");
        mapPieces.put(TypeOfPiece.QUEEN.name(), "Q");
        mapPieces.put(TypeOfPiece.KNIGHT.name(), "N");
        mapPieces.put(TypeOfPiece.ROOK.name(), "R");
        mapPieces.put(TypeOfPiece.BISHOP.name(), "B");
        mapPieces.put(TypeOfPiece.PAWN.name(), "");
        String piece = mapPieces.get(selectedPiece.getType().name());
        String row = Integer.toString(Math.abs(newPosition.getRow()-7)+1);
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H"};
        String col = letters[newPosition.getColumn()];
        String note = piece+col.toLowerCase()+row;
        if(player.getColor().equals(PieceColor.WHITE))
        {
            notation = new Notation();
            notation.setAttack(note);
            playersMovement.add(notation);
        } else {
            notation.setCounterattack(note);
        }

        System.out.println("El movimiento que acaba de hacer es: " + note);

        System.out.print("Notaciones realizadas(ataque y contraataque): ");
        for (Notation notation : playersMovement) {
            System.out.print(notation.getAttack() + "-" + notation.getCounterattack() + " ");
        }
        System.out.println();
    }
    private boolean myKingInCheck (Piece selectedPiece, PiecePosition newPosition) {
        boolean check = false;
        int row = selectedPiece.getPosition().getRow();
        int column = selectedPiece.getPosition().getColumn();
        PiecePosition oldPosition = new PiecePosition(row,column);
        selectedPiece.setPosition(newPosition);

        for (Piece piece : pieceListInBoard) {
            if (selectedPiece.color != piece.getColor()) {
                if (piece.movementValid(getMyKing(selectedPiece.color).getPosition(), pieceListInBoard)) {
                    check = true;
                    break;
                }
            }
        }
        selectedPiece.setPosition(oldPosition);
        return check;
    }
    private boolean enemyKingInCheck (Piece selectedPiece, PiecePosition newPosition) {
        boolean check = false;
        int row = selectedPiece.getPosition().getRow();
        int column = selectedPiece.getPosition().getColumn();
        PiecePosition oldPosition = new PiecePosition(row,column);
        selectedPiece.setPosition(newPosition);
        Piece  enemyKing = getEnemyKing(selectedPiece.color);
        for (Piece piece : pieceListInBoard) {
            if (selectedPiece.color == piece.getColor()) {
                if (piece.movementValid(enemyKing.getPosition(), pieceListInBoard)) {
                    check = true;
                    break;
                }
            }
        }
        selectedPiece.setPosition(oldPosition);
        return check;
    }
    private Piece getEnemyKing(PieceColor color) {
        for (Piece piece : pieceListInBoard) {
            if (piece instanceof King && piece.getColor() != color) {
                return piece;
            }
        }
        return null;
    }

    private Piece getMyKing(PieceColor color) {
        for (Piece piece : pieceListInBoard) {
            if (piece instanceof King && piece.getColor() == color) {
                return piece;
            }
        }
        return null;
    }
    private boolean boxEmptyOrEnemyPiece(Piece selectedPiece, PiecePosition newPosition) {
        Piece pieceAtNewPosition = getPieceAtPosition(newPosition);
        return  (pieceAtNewPosition == null || pieceAtNewPosition.getColor() != selectedPiece.getColor());
    }
    private void capture(Piece piece) {
        piece.status=PieceStatus.CAPTURED;
        pieceListInBoard.remove(piece);
    }
    private Piece getPieceAtPosition(PiecePosition newPosition) {
        for(Piece piece:pieceListInBoard){
            if(piece.getPosition().getRow().equals(newPosition.getRow())&& piece.getPosition().getColumn().equals(newPosition.getColumn())){
                return piece;
            }
        }
        return null;
    }
    private boolean isKingPiece(PieceColor color) {
        for (Piece piece : pieceListInBoard) {
            if (piece instanceof King && piece.getColor() == color) {
                return true;
            }
        }
        return false;
    }
    public boolean checkMate (Player enemyPlayer) {
        List<PiecePosition> piecePositionList = new ArrayList<>();

        for (Piece piece:pieceListInBoard) {
            if (piece.color.equals(enemyPlayer.getColor()))
                piecePositionList = piece.possibleMovesList(pieceListInBoard);
            for (PiecePosition position: piecePositionList) {
                if  ((boxEmptyOrEnemyPiece(piece,position))) {
                    if (!myKingInCheck(piece,position)) {
                        return false;
                    }
                }
            }
        }
        enemyPlayer.setKingInCheckMate(true);
        return true;
    }


    public boolean isFinish() {
        if (playerA.isKingInCheckMate()) {
            System.out.println("JAQUE MATE, EL REY " + playerA.getColor() + " HA CAIDO!!!");
            return true;
        } else if (playerB.isKingInCheckMate()) {
            System.out.println("JAQUE MATE, EL REY " + playerB.getColor() + " HA CAIDO!!!");
            return true;
        }
        return false;
    }
    private PiecePosition getPosition() {
        PiecePosition position = null;
        do {
            System.out.println("Ingrese una coordenada en un formato de una letra de la \"A\" a la \"H\"" +
                    "y un número del 1 al 8");
            String input = scanner.nextLine().toUpperCase();

            if (isValidPosition(input)) {

                char letter=input.charAt(0);
                char number=input.charAt(1);
                Integer getRow= getMap().get(String.valueOf(number));
                Integer getColumn=getMap().get(String.valueOf(letter));
                position = new PiecePosition(getRow ,getColumn);
                //se le pide a getMap() la clave de letra que ingrese el usuario, y la clave de numero ingresado por el usuario
            } else {
                System.out.println("Ingrese una posición con el formato correcto!!!");
            }
        } while(position == null);
        return position;
    }
    private Map<String, Integer> getMap() {
        Map<String, Integer> mapping = new HashMap<>();

        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H"};
        String[] numbers = {"8", "7", "6", "5", "4", "3", "2", "1"};

        for (int i = 0; i < letters.length; i++) {
            mapping.put(letters[i], i);//en la posicion 0 de letras esta la "A"
            mapping.put(numbers[i], i);//en la posicion 0 de numeros esta el 8 quedaria el mapa como mapa.put("A",8)
            // mapping.put(letters[i],Integer.parseInt(numbers[i]));
        }
        return mapping;
    }

    public void calculateScores() {
    }
    public void loadTypeByPieceInstance(List<Piece> listOfPieces){
        for(Piece piece : listOfPieces){
            if(piece instanceof King)
            {
                piece.setType(TypeOfPiece.KING);
            } else if(piece instanceof Queen)
            {
                piece.setType(TypeOfPiece.QUEEN);
            } else if(piece instanceof Knight)
            {
                piece.setType(TypeOfPiece.KNIGHT);
            } else if(piece instanceof Pawn)
            {
                piece.setType(TypeOfPiece.PAWN);
            } else if(piece instanceof Rook)
            {
                piece.setType(TypeOfPiece.ROOK);
            } else if (piece instanceof Bishop)
            {
                piece.setType(TypeOfPiece.BISHOP);
            } else {
                System.out.println("No existe el tipo");
            }
        }
    }
    public void loadBoardSavedGame(List<Piece> pieceList){
        this.board = new Board();
        this.board.initBoard(pieceList);
        this.playersMovement = new ArrayList<>();
    }
}
