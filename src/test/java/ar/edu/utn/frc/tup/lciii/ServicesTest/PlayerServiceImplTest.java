package ar.edu.utn.frc.tup.lciii.ServicesTest;

import static org.mockito.Mockito.*;

import ar.edu.utn.frc.tup.lciii.entities.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.models.PieceColor;
import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.PlayerJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.impl.PlayerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class PlayerServiceImplTest {
    @Mock
    private PlayerJpaRepository playerJpaRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetPlayer() {
        Long playerId = 1L;
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(playerId);

        when(playerJpaRepository.getReferenceById(playerId)).thenReturn(playerEntity);
        when(modelMapper.map(playerEntity, Player.class)).thenReturn(new Player());

        playerService.getPlayer(playerId);

        verify(playerJpaRepository, times(1)).getReferenceById(playerId);
        verify(modelMapper, times(1)).map(playerEntity, Player.class);
    }

    @Test
    void testCreatePlayer() {
        Player player = new Player();
        player.setId(1L); // Asignar un ID válido al jugador
        player.setNamePlayer("string");
        player.setColor(PieceColor.WHITE);
        player.setScore(0);
        player.setKingInCheck(false);
        player.setKingInCheckMate(false);
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(player.getId());

        // Configurar el mock del modelMapper
        when(modelMapper.map(eq(player), eq(PlayerEntity.class))).thenReturn(playerEntity);

        when(playerJpaRepository.save(playerEntity)).thenReturn(playerEntity);
        when(modelMapper.map(eq(playerEntity), eq(Player.class))).thenReturn(player);

        playerService.createPlayer(player);

        verify(modelMapper, times(1)).map(eq(player), eq(PlayerEntity.class));
        verify(playerJpaRepository, times(1)).save(playerEntity);
        verify(modelMapper, times(1)).map(eq(playerEntity), eq(Player.class));
    }

    @Test
    void testGetAllPlayers() {
        List<PlayerEntity> playerEntities = new ArrayList<>();
        playerEntities.add(new PlayerEntity());

        when(playerJpaRepository.findAll()).thenReturn(playerEntities);
        when(modelMapper.map(any(PlayerEntity.class), eq(Player.class))).thenReturn(new Player());

        playerService.getAllPlayers();

        verify(playerJpaRepository, times(1)).findAll();
        verify(modelMapper, times(playerEntities.size())).map(any(PlayerEntity.class), eq(Player.class));
    }

    @Test
    void testGetPlayerById_PlayerExists() {
        Long playerId = 1L;
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setNamePlayer("John Doe");
        playerEntity.setColor(PieceColor.WHITE);
        playerEntity.setScore(100);
        playerEntity.setKingInCheck(true);

        when(playerJpaRepository.findById(playerId)).thenReturn(Optional.of(playerEntity));

        playerService.getPlayerById(playerId);

        verify(playerJpaRepository, times(1)).findById(playerId);
    }

    @Test
    void testGetPlayerById_PlayerDoesNotExist() {
        Long playerId = 1L;

        when(playerJpaRepository.findById(playerId)).thenReturn(Optional.empty());

        playerService.getPlayerById(playerId);

        verify(playerJpaRepository, times(1)).findById(playerId);
    }
}