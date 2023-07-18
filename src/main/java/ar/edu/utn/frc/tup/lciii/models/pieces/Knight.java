package ar.edu.utn.frc.tup.lciii.models.pieces;
import ar.edu.utn.frc.tup.lciii.models.Piece;
import ar.edu.utn.frc.tup.lciii.models.PieceColor;
import ar.edu.utn.frc.tup.lciii.models.PiecePosition;
import ar.edu.utn.frc.tup.lciii.models.TypeOfPiece;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class Knight extends Piece {

    public Knight(PieceColor color, PiecePosition position) {
        super(color, position);
        this.type=TypeOfPiece.KNIGHT;
    }

    @Override
    public boolean movementValid(PiecePosition newPosition, List<Piece> piecesListOnBoard) {
        int r=Math.abs(newPosition.getRow()-getPosition().getRow());
        int c=Math.abs(newPosition.getColumn()-getPosition().getColumn());
        return ((r == 1 && c == 2)||(r == 2 && c == 1));
    }
}