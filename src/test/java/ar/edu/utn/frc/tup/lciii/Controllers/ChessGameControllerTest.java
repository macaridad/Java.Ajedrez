package ar.edu.utn.frc.tup.lciii.Controllers;

import ar.edu.utn.frc.tup.lciii.controllers.ChessGameController;
import ar.edu.utn.frc.tup.lciii.models.ChessGame;
import ar.edu.utn.frc.tup.lciii.services.ChessGameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ChessGameControllerTest {

    @Mock
    private ChessGameService chessGameService;

    @InjectMocks
    private ChessGameController chessGameController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetGameById() throws JsonProcessingException {
        Long gameId = 1L;
        ChessGame game = new ChessGame();
        game.setId(gameId);
        // Agrega más propiedades al juego si es necesario

        when(chessGameService.getGameById(gameId)).thenReturn(game);

        ChessGame result = chessGameController.getGameById(gameId);

        verify(chessGameService, times(1)).getGameById(gameId);
        assertEquals(game, result);
    }

    @Test
    public void testSaveChessGame() {
        ChessGame chessGame = new ChessGame();
        boolean playerWhiteInGame = true;

        chessGameController.saveChessGame(chessGame, playerWhiteInGame);

        verify(chessGameService, times(1)).createGame(chessGame, playerWhiteInGame);
    }

    @Test
    public void testGetAllChessGame() {
        ChessGame game1 = new ChessGame();
        // Agrega propiedades al juego 1 si es necesario

        ChessGame game2 = new ChessGame();
        // Agrega propiedades al juego 2 si es necesario

        List<ChessGame> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);

        when(chessGameService.getAllMatchs()).thenReturn(games);

        List<ChessGame> result = chessGameController.getAllChessGame();

        verify(chessGameService, times(1)).getAllMatchs();
        assertEquals(games, result);
    }

    @Test
    public void testUpdateChessGame() throws JsonProcessingException {
        ChessGame chessGame = new ChessGame();
        // Agrega propiedades al juego si es necesario

        chessGameController.updateChessGame(chessGame);

        verify(chessGameService, times(1)).updateGame(chessGame);
    }
}