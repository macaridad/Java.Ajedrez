package ar.edu.utn.frc.tup.lciii.models;

import ar.edu.utn.frc.tup.lciii.models.pieces.*;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Rook.class, name = "ROOK"),
        @JsonSubTypes.Type(value = King.class, name = "KING"),
        @JsonSubTypes.Type(value = Queen.class, name = "QUEEN"),
        @JsonSubTypes.Type(value = Knight.class, name = "KNIGHT"),
        @JsonSubTypes.Type(value = Bishop.class, name = "BISHOP"),
        @JsonSubTypes.Type(value = Pawn.class, name = "PAWN"),
        // Agrega aquí las demás subclases concretas de Piece
})

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Piece {
    protected PieceColor color;
    protected PiecePosition position;
    protected PieceStatus status;
    protected TypeOfPiece type;

    /*Para iniciarlizar las piezas todas ONBOARD*/
    public Piece(PieceColor color, PiecePosition position) {
        this.color = color;
        this.position = position;
        this.status = PieceStatus.ONBOARD;
    }
    /**
     Este método es común a todas las piezas
     @PiecePosition newPosition
     */
    public boolean movement(PiecePosition newPosition) {

        if (this.position == newPosition) {
            return false;
        }
        else {
            this.setPosition(newPosition);
            return true;
        }
    }
    protected abstract boolean movementValid(PiecePosition newPosition, List<Piece> piecesListOnBoard);

    public List<PiecePosition> possibleMovesList (List<Piece> piecesListOnBoard) {
        List<PiecePosition> possibleMovesList = new ArrayList<>();

        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                PiecePosition newPosition = new PiecePosition(row,column);
                if (this.movementValid(newPosition, piecesListOnBoard)) {
                    possibleMovesList.add(newPosition);
                }
            }
        }
        return possibleMovesList;
    };

}
