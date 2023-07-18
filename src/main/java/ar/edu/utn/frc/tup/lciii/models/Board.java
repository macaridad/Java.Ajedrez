package ar.edu.utn.frc.tup.lciii.models;


import java.util.List;

public class Board {

    private static final Integer ROWS = 8;
    private static final Integer COLUMNS = 8;
    private static final String freeBox = "      ";
    private String [][] board;

    public String[][] getBoard() { return board; }
    public void setBoard(String[][] board) { this.board = board; }
    public Board() {
        this.board = new String[ROWS][COLUMNS];
        }


    public void initBoard(List<Piece> pieceListInBoard) {

        for (int f = 0; f < board.length; f++) {
            for (int c = 0; c < board.length; c++) {
                board[f][c] = "      ";
            }
        }
        completeBoardWithPieces(pieceListInBoard);
        drawBoard();
    }


    public void completeBoardWithPieces(List<Piece> pieceListInBoard) {
        for(Piece piece : pieceListInBoard)
        {
            if(piece.getColor()==PieceColor.BLACK)
            {
                if(piece.getType().equals(TypeOfPiece.ROOK) || piece.getType().equals(TypeOfPiece.PAWN) || piece.getType().equals(TypeOfPiece.KING))
                {
                    board[piece.getPosition().getRow()][piece.getPosition().getColumn()] = pieceColorBlack(" " + piece.getType().toString() + " ");
                } else if (piece.getType().equals(TypeOfPiece.QUEEN)) {
                    board[piece.getPosition().getRow()][piece.getPosition().getColumn()] = pieceColorBlack(piece.getType().toString() + " ");
                } else {
                    board[piece.getPosition().getRow()][piece.getPosition().getColumn()] = pieceColorBlack(piece.getType().toString());
                }
            }
            else {
                if(piece.getType().equals(TypeOfPiece.ROOK) || piece.getType().equals(TypeOfPiece.PAWN) || piece.getType().equals(TypeOfPiece.KING))
                {
                    board[piece.getPosition().getRow()][piece.getPosition().getColumn()] = pieceColorWhite(" " + piece.getType().toString() + " ");
                } else if (piece.getType().equals(TypeOfPiece.QUEEN)) {
                    board[piece.getPosition().getRow()][piece.getPosition().getColumn()] = pieceColorWhite(piece.getType().toString() + " ");
                } else {
                    board[piece.getPosition().getRow()][piece.getPosition().getColumn()] = pieceColorWhite(piece.getType().toString());
                }
            }
        }
    }


    public void drawBoard() {
        int fila = 8;

        String box = "        ";
        StringBuilder sb = new StringBuilder();
        sb.append("##########################################################################\n");
        sb.append("#       A       B       C       D       E       F       G       H        #\n");
        sb.append("#   " + redBorderBackground("                                                                  ") +"   #\n");

        for(int f = 0; f < ROWS; f++) {

            if (f%2==0) {
                sb.append("#   " + redBorderBackground(" ") + whiteBackground(box) + blackBackground(box) + whiteBackground(box) + blackBackground(box)+
                        whiteBackground(box) + blackBackground(box) + whiteBackground(box) + blackBackground(box) + redBorderBackground(" ") + "   #\r\n");
                sb.append("# " + fila + " " + redBorderBackground(" "));
                for (int c = 0; c < COLUMNS; c++) {
                    if (c%2==0) {
                        sb.append(whiteBackground(" ") +  whiteBackground(this.board[f][c]) + whiteBackground(" "));
                    }
                    else {
                        sb.append(blackBackground(" ") + blackBackground(this.board[f][c]) + blackBackground(" "));
                    }
                }
                sb.append(redBorderBackground(" ") + " " + fila + " #\n");
                sb.append("#   " + redBorderBackground(" ") + whiteBackground(box) + blackBackground(box)+ whiteBackground(box) + blackBackground(box) +
                        whiteBackground(box) + blackBackground(box) + whiteBackground(box) + blackBackground(box) + redBorderBackground(" ") + "   #\r\n");
            }
            else {
                sb.append("#   " + redBorderBackground(" ") + blackBackground(box) + whiteBackground(box) + blackBackground(box) + whiteBackground(box) +
                        blackBackground(box) + whiteBackground(box) + blackBackground(box) + whiteBackground(box) + redBorderBackground(" ") + "   #\r\n");
                sb.append("# " + fila + " " + redBorderBackground(" "));
                for (int c = 0; c < COLUMNS; c++) {
                    if (c%2==0) {
                        sb.append(blackBackground(" ") +  blackBackground(this.board[f][c])  + blackBackground(" "));
                    }
                    else {
                        sb.append(whiteBackground(" " + this.board[f][c])+ whiteBackground(" "));
                    }
                }
                sb.append(redBorderBackground(" ") +" " + fila + " #\n");
                sb.append("#   " + redBorderBackground(" ") + blackBackground(box) + whiteBackground(box) + blackBackground(box) + whiteBackground(box) +
                        blackBackground(box) + whiteBackground(box) + blackBackground(box) + whiteBackground(box) + redBorderBackground(" ") + "   #\r\n");
            }
            fila--;
        }
        sb.append("#   " + redBorderBackground("                                                                  ") +"   #\n");
        sb.append("#       A       B       C       D       E       F       G       H        #\n");
        sb.append("##########################################################################\n");

        System.out.println(sb);
    }


    private String blackBackground(String s) {
        return "\u001B[40m" + s + "\u001B[0m";
    }
    private String whiteBackground(String s) {
        return "\u001B[47m" + s + "\u001B[47m";
    }
    private String redBorderBackground(String s) {
        return "\u001B[48;2;90;0;0m" + s + "\u001B[0m";
    }
    private String pieceColorBlack(String s) {
        return "\u001B[48;2;90;0;0m" + s + "\u001B[0m";
    }
    private String pieceColorWhite(String s) { return "\u001B[43m" + s + "\u001B[0m"; }


    public void movementOnBoard (Player player, PiecePosition actualPosition, PiecePosition newPosition) {

        //Se mueve la pieza a la nueva posición
        if (player.getColor()==PieceColor.BLACK) {
            this.board[newPosition.getRow()][newPosition.getColumn()] = pieceColorBlack(this.board[actualPosition.getRow()][actualPosition.getColumn()]);
        }
        else {
            this.board[newPosition.getRow()][newPosition.getColumn()] = pieceColorWhite(this.board[actualPosition.getRow()][actualPosition.getColumn()]);
        }

        // Se elimina la pieza de la posición original
        this.board[actualPosition.getRow()][actualPosition.getColumn()] = freeBox;
    }



}
