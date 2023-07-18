package ar.edu.utn.frc.tup.lciii.ServicesTest;

import ar.edu.utn.frc.tup.lciii.entities.ChessMatchEntity;
import ar.edu.utn.frc.tup.lciii.models.ChessGame;
import ar.edu.utn.frc.tup.lciii.models.Piece;
import ar.edu.utn.frc.tup.lciii.models.PieceColor;
import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.ChessGameJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.impl.ChessGameServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ChessGameServiceImplTest {

    @Mock
    private ChessGameJpaRepository chessGameJpaRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private ModelMapper mergerMapper;

    @InjectMocks
    private ChessGameServiceImpl chessGameService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetGameById() throws JsonProcessingException {
        Long gameId = 1L;

        // Mock ChessMatchEntity
        ChessMatchEntity matchEntity = new ChessMatchEntity();
        matchEntity.setId(gameId);
        matchEntity.setMatchInfo("[]");

        // Mock ChessGame
        ChessGame chessGame = new ChessGame();
        chessGame.setPlayerA(new Player());
        chessGame.setPlayerB(new Player());

        // Mock ObjectMapper
        when(objectMapper.readValue(matchEntity.getMatchInfo(), List.class)).thenReturn(new ArrayList<>());
        when(objectMapper.readValue("[]", List.class)).thenReturn(new ArrayList<>());
        when(objectMapper.writeValueAsString(any(List.class))).thenReturn("[]");

        // Mock ChessGameJpaRepository
        when(chessGameJpaRepository.findById(gameId)).thenReturn(Optional.of(matchEntity));

        ChessGame result = chessGameService.getGameById(gameId);

        verify(chessGameJpaRepository, times(1)).findById(gameId);
        verify(objectMapper, times(1)).readValue(matchEntity.getMatchInfo(), List.class);
        verify(objectMapper, times(1)).writeValueAsString(any(List.class));

        assertEquals(chessGame, result);
    }

    @Test
    public void testCreateGame() throws JsonProcessingException {
        ChessGame chessGame = new ChessGame();
        chessGame.setPlayerA(new Player());
        chessGame.setPlayerB(new Player());

        ChessMatchEntity matchEntity = new ChessMatchEntity();

        when(modelMapper.map(chessGame, ChessMatchEntity.class)).thenReturn(matchEntity);
        when(objectMapper.writeValueAsString(chessGame.getPieceListInBoard())).thenReturn("[]");
        when(chessGameJpaRepository.save(matchEntity)).thenReturn(matchEntity);

        chessGameService.createGame(chessGame, true);

        verify(modelMapper, times(1)).map(chessGame, ChessMatchEntity.class);
        verify(objectMapper, times(1)).writeValueAsString(chessGame.getPieceListInBoard());
        verify(chessGameJpaRepository, times(1)).save(matchEntity);
    }

    @Test
    public void testGetAllMatchs() {
        List<ChessMatchEntity> matchEntities = new ArrayList<>();
        matchEntities.add(new ChessMatchEntity());

        when(chessGameJpaRepository.findAll()).thenReturn(matchEntities);
        when(mergerMapper.map(any(ChessMatchEntity.class), eq(ChessGame.class))).thenReturn(new ChessGame());

        List<ChessGame> result = chessGameService.getAllMatchs();

        verify(chessGameJpaRepository, times(1)).findAll();
        verify(mergerMapper, times(1)).map(any(ChessMatchEntity.class), eq(ChessGame.class));

        assertEquals(matchEntities.size(), result.size());
    }

    @Test
    public void testUpdateGame() throws JsonProcessingException {
        Long gameId = 1L;

        // Mock ChessMatchEntity
        ChessMatchEntity matchEntity = new ChessMatchEntity();
        matchEntity.setId(gameId);
        matchEntity.setMatchInfo("[]");

        // Mock ChessGame
        ChessGame chessGame = new ChessGame();
        chessGame.setPlayerA(new Player());
        chessGame.setPlayerB(new Player());

        // Mock ObjectMapper
        when(objectMapper.readValue(matchEntity.getMatchInfo(), List.class)).thenReturn(new ArrayList<>());
        when(objectMapper.readValue("[]", List.class)).thenReturn(new ArrayList<>());
        when(objectMapper.writeValueAsString(any(List.class))).thenReturn("[]");

        // Mock ChessGameJpaRepository
        when(chessGameJpaRepository.findById(gameId)).thenReturn(Optional.of(matchEntity));
        when(chessGameJpaRepository.save(any(ChessMatchEntity.class))).thenReturn(matchEntity);

        chessGameService.updateGame(chessGame);

        verify(chessGameJpaRepository, times(1)).findById(gameId);
        verify(chessGameJpaRepository, times(1)).save(any(ChessMatchEntity.class));
        verify(objectMapper, times(1)).readValue(matchEntity.getMatchInfo(), List.class);
        verify(objectMapper, times(1)).writeValueAsString(any(List.class));
    }
}
