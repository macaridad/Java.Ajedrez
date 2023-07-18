package ar.edu.utn.frc.tup.lciii.Controllers;

import ar.edu.utn.frc.tup.lciii.controllers.PlayerController;
import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlayerControllerTest {

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController playerController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPlayer() {
        Long playerId = 1L;
        Player player = new Player();
        player.setId(playerId);
        player.setNamePlayer("John Doe");

        when(playerService.getPlayer(playerId)).thenReturn(player);

        Player result = playerController.getPlayer(playerId);

        verify(playerService, times(1)).getPlayer(playerId);
        assertEquals(player, result);
    }

    @Test
    public void testGetAllPlayers() {
        Player player1 = new Player();
        player1.setId(1L);
        player1.setNamePlayer("John Doe");

        Player player2 = new Player();
        player2.setId(2L);
        player2.setNamePlayer("Jane Smith");

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        when(playerService.getAllPlayers()).thenReturn(players);

        List<Player> result = playerController.getAllPlayers();

        verify(playerService, times(1)).getAllPlayers();
        assertEquals(players, result);
    }

    @Test
    public void testGetPlayerById() {
        Long playerId = 1L;
        Player player = new Player();
        player.setId(playerId);
        player.setNamePlayer("John Doe");

        when(playerService.getPlayerById(playerId)).thenReturn(player);

        Player result = playerController.getPlayerById(playerId);

        verify(playerService, times(1)).getPlayerById(playerId);
        assertEquals(player, result);
    }

    @Test
    public void testCreatePlayer() {
        Player player = new Player();
        player.setNamePlayer("John Doe");

        when(playerService.createPlayer(player)).thenReturn(player);

        Player result = playerController.createPlayer(player);

        verify(playerService, times(1)).createPlayer(player);
        assertEquals(player, result);
    }
}