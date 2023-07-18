package ar.edu.utn.frc.tup.lciii.ModelsTest;

import ar.edu.utn.frc.tup.lciii.models.PieceColor;
import ar.edu.utn.frc.tup.lciii.models.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testConstructorAndGetters() {
        Long id = 1L;
        String name = "pepe gomez";
        PieceColor color = PieceColor.WHITE;
        Integer score = 100;
        boolean kingInCheck = true;
        boolean kingInCheckMate = false;

        Player player = new Player(id, name, color, score, kingInCheck, kingInCheckMate);

        assertEquals(id, player.getId());
        assertEquals(name, player.getNamePlayer());
        assertEquals(color, player.getColor());
        assertEquals(score, player.getScore());
        assertEquals(kingInCheck, player.isKingInCheck());
        assertEquals(kingInCheckMate, player.isKingInCheckMate());
    }

    @Test
    public void testSetters() {
        Player player = new Player();

        Long id = 2L;
        player.setId(id);
        assertEquals(id, player.getId());

        String name = "pepe";
        player.setNamePlayer(name);
        assertEquals(name, player.getNamePlayer());

        PieceColor color = PieceColor.BLACK;
        player.setColor(color);
        assertEquals(color, player.getColor());

        Integer score = 200;
        player.setScore(score);
        assertEquals(score, player.getScore());

        boolean kingInCheck = true;
        player.setKingInCheck(kingInCheck);
        assertEquals(kingInCheck, player.isKingInCheck());

        boolean kingInCheckMate = false;
        player.setKingInCheckMate(kingInCheckMate);
        assertEquals(kingInCheckMate, player.isKingInCheckMate());
    }

    @Test
    public void testAdditionalConstructors() {
        String name = "pepem";
        PieceColor color = PieceColor.WHITE;
        Integer score = 100;
        boolean kingInCheck = true;

        Player player1 = new Player(name, color, score, kingInCheck);
        assertNull(player1.getId());
        assertEquals(name, player1.getNamePlayer());
        assertEquals(color, player1.getColor());
        assertEquals(score, player1.getScore());
        assertEquals(kingInCheck, player1.isKingInCheck());
        assertFalse(player1.isKingInCheckMate());

        Long id = 1L;
        Player player2 = new Player(id, name, score, kingInCheck);
        assertEquals(id, player2.getId());
        assertEquals(name, player2.getNamePlayer());
        assertNull(player2.getColor());
        assertEquals(score, player2.getScore());
        assertEquals(kingInCheck, player2.isKingInCheck());
        assertFalse(player2.isKingInCheckMate());
    }
}







