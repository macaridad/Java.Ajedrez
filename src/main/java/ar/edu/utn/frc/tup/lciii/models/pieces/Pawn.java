package ar.edu.utn.frc.tup.lciii.models.pieces;

import ar.edu.utn.frc.tup.lciii.models.Piece;
import ar.edu.utn.frc.tup.lciii.models.PieceColor;
import ar.edu.utn.frc.tup.lciii.models.PiecePosition;
import ar.edu.utn.frc.tup.lciii.models.TypeOfPiece;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
public class Pawn extends Piece {

    public Pawn(PieceColor color, PiecePosition position) {
        super(color, position);
        this.type= TypeOfPiece.PAWN;
    }
    @Override
    public boolean movementValid(PiecePosition newPosition, List<Piece> piecesListOnBoard) {
        int rowDiff = newPosition.getRow() - getPosition().getRow();
        int columnDiff = Math.abs(newPosition.getColumn() - getPosition().getColumn());

        if (this.color == PieceColor.WHITE) {

            if (rowDiff == -1 && columnDiff == 0) {

                if (movementValidForCapture(newPosition, piecesListOnBoard)) {
                    return false;
                }
                return true;

            } else if (this.position.getRow() == 6 && rowDiff == -2 && columnDiff == 0) {

                if (movementValidForCapture(newPosition, piecesListOnBoard)) {
                    return false;
                }
                return true;

            } else if (rowDiff == -1 && columnDiff == 1 && movementValidForCapture(newPosition, piecesListOnBoard)) {
                return true;
            }
        }

        else if (this.color == PieceColor.BLACK) {
            if (rowDiff == 1 && columnDiff == 0) {
                if (movementValidForCapture(newPosition, piecesListOnBoard)) {
                    return false;
                }
                return true;
            } else if (this.position.getRow() == 1 && rowDiff == 2 && columnDiff == 0) {
                if (movementValidForCapture(newPosition, piecesListOnBoard)) {
                    return false;
                }
                return true;
            } else if ((rowDiff == 1 && columnDiff == 1) && (movementValidForCapture(newPosition, piecesListOnBoard))) {
                return true;
            }
        }
        return false;
    }

    public boolean movementValidForCapture(PiecePosition newPosition, List<Piece> piecesListOnBoard){
        for (Piece piece : piecesListOnBoard) {
            if (piece.getPosition().getRow().equals(newPosition.getRow()) && piece.getPosition().getColumn().equals(newPosition.getColumn()) ) {
                return true;
            }
        }
        return false;
    }
}