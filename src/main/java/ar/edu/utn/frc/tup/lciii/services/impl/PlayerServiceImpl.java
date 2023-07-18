package ar.edu.utn.frc.tup.lciii.services.impl;
import ar.edu.utn.frc.tup.lciii.entities.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.PlayerJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerJpaRepository playerJpaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public Player getPlayer(Long id) {
        PlayerEntity playerEntity = playerJpaRepository.getReferenceById(id);
        return modelMapper.map(playerEntity, Player.class);
    }

    @Override
    public Player createPlayer(Player player) {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(player.getId());
        playerEntity.setNamePlayer(player.getNamePlayer());
        playerEntity.setScore(player.getScore());
        playerEntity.setColor(player.getColor());
        playerEntity.setKingInCheck(player.isKingInCheck());

        PlayerEntity savedPlayerEntity = playerJpaRepository.save(playerEntity);
        Player savedPlayer = new Player();
        savedPlayer.setId(savedPlayerEntity.getId());
        savedPlayer.setNamePlayer(savedPlayerEntity.getNamePlayer());
        savedPlayer.setScore(savedPlayerEntity.getScore());
        savedPlayer.setColor(savedPlayerEntity.getColor());
        savedPlayer.setKingInCheck(savedPlayerEntity.isKingInCheck());
        return savedPlayer;//saved
    }


    @Override
    public List<Player> getAllPlayers() {
        List<PlayerEntity> playerEntities = playerJpaRepository.findAll();
        return playerEntities.stream()
                .map(playerEntity -> modelMapper.map(playerEntity, Player.class))
                .collect(Collectors.toList());
    }

    @Override
    public Player getPlayerById(Long id) {
        Optional<PlayerEntity> optionalPlayerEntity = playerJpaRepository.findById(id);
        Player player = null;
        if (optionalPlayerEntity.isPresent()) {
            PlayerEntity playerEntity = optionalPlayerEntity.get();
            // Crear una instancia de Player y asignar los valores directamente
            player = new Player();
            player.setNamePlayer(playerEntity.getNamePlayer());
            player.setColor(playerEntity.getColor());
            player.setScore(playerEntity.getScore());
            player.setKingInCheck(playerEntity.isKingInCheck());
        } else {
            System.out.println("El jugador no existe.");
        }
        return player;
    }


}
