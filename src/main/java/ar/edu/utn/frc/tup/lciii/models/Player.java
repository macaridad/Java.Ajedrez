package ar.edu.utn.frc.tup.lciii.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Player {
    private Long id;
    private String namePlayer;
    private PieceColor color;
    private Integer score;
    private boolean kingInCheck;
    private boolean kingInCheckMate;

    public Player(String name, PieceColor color, int score, boolean kingInCheck) {
        namePlayer=name;
        this.color=color;
        this.score=score;
        this.kingInCheck=kingInCheck;
    }
    public Player(Long id, String name, int score, boolean kingInCheck) {
        this.setId(id);
        this.setNamePlayer(name);
        this.setScore(score);
        this.setKingInCheck(kingInCheck);
    }
}
