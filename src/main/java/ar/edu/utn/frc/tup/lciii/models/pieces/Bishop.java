package ar.edu.utn.frc.tup.lciii.models.pieces;
import ar.edu.utn.frc.tup.lciii.models.Piece;
import ar.edu.utn.frc.tup.lciii.models.PieceColor;
import ar.edu.utn.frc.tup.lciii.models.PiecePosition;
import ar.edu.utn.frc.tup.lciii.models.TypeOfPiece;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
public class Bishop extends Piece {

    public Bishop(PieceColor color, PiecePosition position) {
        super(color, position);
        this.type=TypeOfPiece.BISHOP;
    }


    @Override
    public boolean movementValid(PiecePosition newPosition, List<Piece> piecesListOnBoard) {
        int row=Math.abs(newPosition.getRow()-this.position.getRow());
        int column=Math.abs(newPosition.getColumn()-this.position.getColumn());



        return (row == column && checkObstacle(newPosition, piecesListOnBoard));
    }



    public boolean checkObstacle(PiecePosition newPosition, List<Piece> piecesListOnBoard) {

        // Calculo la diferencias de Filas y Columnas
        int rowDiff = newPosition.getRow() - this.position.getRow();
        int colDiff = newPosition.getColumn() - this.position.getColumn();

        // Identifico el sentido del movimiento (-1,0,1)
        int rowStep = Integer.signum(rowDiff);
        int colStep = Integer.signum(colDiff);

        // Fila y Columna inicial a recorrer
        int currentRow = this.position.getRow() + rowStep;
        int currentCol = this.position.getColumn() + colStep;

        while (currentRow != newPosition.getRow() && currentCol != newPosition.getColumn()) {
            PiecePosition currentMovingPosition = new PiecePosition(currentRow, currentCol);

            for (Piece piece : piecesListOnBoard) {
                if (piece.getPosition().getRow().equals(currentMovingPosition.getRow()) && piece.getPosition().getColumn().equals(currentMovingPosition.getColumn()) ) {

                    return false; // Se encontró un obstáculo en el camino
                }
            }
            currentRow += rowStep;
            currentCol += colStep;
        }

        return true; // No se encontraron obstáculos en el camino
    }

}