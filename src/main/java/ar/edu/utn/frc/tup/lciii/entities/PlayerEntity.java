package ar.edu.utn.frc.tup.lciii.entities;


import ar.edu.utn.frc.tup.lciii.models.PieceColor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String namePlayer;
    @Column
    private Integer score;
    @Column
    private PieceColor color;
    @Column
    private boolean kingInCheck;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "playerA", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private List<ChessMatchEntity> chessMatchsA=new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "playerB", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private List<ChessMatchEntity> chessMatchsB=new ArrayList<>();
    @Column(name = "player_info")
    private String playerInfo;
}
