package ar.edu.utn.frc.tup.lciii.entities;

import ar.edu.utn.frc.tup.lciii.models.PieceColor;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "matchs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChessMatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="game_name")
    private String gameName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_a") // Nombre de columna único para playerA
    private PlayerEntity playerA;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_b") // Nombre de columna único para playerB
    private PlayerEntity playerB;

    @Column(name = "color_a")
    @Enumerated(EnumType.STRING)
    private PieceColor colorA;
    @Column(name = "color_b")
    @Enumerated(EnumType.STRING)
    private PieceColor colorB;

    @Enumerated(EnumType.STRING)
    private PieceColor nextColor;

    @Column
    private String result;

    @Column
    private String matchInfo;

    @Column(name = "game_date")
    private LocalDateTime gameDate;

}
