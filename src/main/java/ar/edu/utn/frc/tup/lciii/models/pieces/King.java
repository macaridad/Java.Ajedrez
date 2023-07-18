package ar.edu.utn.frc.tup.lciii.models.pieces;
import ar.edu.utn.frc.tup.lciii.models.Piece;
import ar.edu.utn.frc.tup.lciii.models.PieceColor;
import ar.edu.utn.frc.tup.lciii.models.PiecePosition;
import ar.edu.utn.frc.tup.lciii.models.TypeOfPiece;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class King extends Piece {


    public King(PieceColor color, PiecePosition position) {
        super(color, position);
        this.type=TypeOfPiece.KING;
    }
    @Override
    public boolean movementValid(PiecePosition newPosition, List<Piece> piecesListOnBoard) {

        int rowDif = Math.abs(newPosition.getRow()-this.position.getRow()) ;
        int columnDif = Math.abs(newPosition.getColumn() - this.position.getColumn());

        return rowDif <= 1 && columnDif <= 1;
    }
    public List<PiecePosition> possibleMovesList () {
        List<PiecePosition> possibleMovesList = new ArrayList<>();

        for (int row = -1; row < 2; row++) {
            for (int column = -1; column < 2; column++) {
                int newRow = this.position.getRow() + row;
                int newColumn = this.position.getColumn() + column;

                if (newRow >= 0 && newRow < 8) {
                    if (newColumn >= 0 && newColumn < 8) {
                        PiecePosition newPiecePosition = new PiecePosition(newRow,newColumn);
                        possibleMovesList.add(newPiecePosition);
                    }
                }
            }
        }

        for (PiecePosition position: possibleMovesList) {
            if (this.position.getRow().equals(position.getRow()) && this.position.getColumn().equals(position.getColumn())) {
                possibleMovesList.remove(position);
            }
        }
        return possibleMovesList;
    }

}