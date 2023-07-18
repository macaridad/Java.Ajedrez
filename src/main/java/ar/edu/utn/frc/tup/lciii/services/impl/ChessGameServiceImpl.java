package ar.edu.utn.frc.tup.lciii.services.impl;
import ar.edu.utn.frc.tup.lciii.entities.ChessMatchEntity;
import ar.edu.utn.frc.tup.lciii.entities.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.models.ChessGame;
import ar.edu.utn.frc.tup.lciii.models.Piece;

import ar.edu.utn.frc.tup.lciii.models.PieceColor;

import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.ChessGameJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.ChessGameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChessGameServiceImpl implements ChessGameService {
    @Autowired
    private ChessGameJpaRepository chessGameJpaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ModelMapper mergerMapper;
    private Long savedId;




    @Override
    public ChessGame getGameById(Long id) throws JsonProcessingException {

        Optional<ChessMatchEntity> optionalMatchEntity = chessGameJpaRepository.findById(id);
        ChessGame chessGame = new ChessGame();
        if (optionalMatchEntity.isPresent()) {
            ChessMatchEntity matchEntity = optionalMatchEntity.get();

            String matchInfoJson = matchEntity.getMatchInfo();

            try {
                List<Piece> pieceList = objectMapper.readValue(matchInfoJson, new TypeReference<List<Piece>>() {});
                chessGame.loadTypeByPieceInstance(pieceList);
                // Asignar los valores de la partida cargada a la instancia actual de ChessGame
                chessGame.setPlayerA(modelMapper.map(matchEntity.getPlayerA(), Player.class));
                chessGame.setPlayerB(modelMapper.map(matchEntity.getPlayerB(), Player.class));
                chessGame.setPieceListInBoard(pieceList);
                chessGame.loadBoardSavedGame(pieceList);

                System.out.println("Partida cargada exitosamente.");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La partida no existe.");
        }
        return chessGame;
    }


    @Override
    public void createGame(ChessGame chessGame,boolean playerWhiteInGame) {
        ChessMatchEntity chessMatchEntity = modelMapper.map(chessGame, ChessMatchEntity.class);
        try{
            String info = objectMapper.writeValueAsString(chessGame.getPieceListInBoard());
            chessMatchEntity.setMatchInfo(info);

        } catch (JsonProcessingException exception){
            exception.printStackTrace();
        }
        PlayerEntity playerEntityA = modelMapper.map(chessGame.getPlayerA(), PlayerEntity.class);
        PlayerEntity playerEntityB = modelMapper.map(chessGame.getPlayerB(), PlayerEntity.class);
        //playerEntityA.getChessMatchsA().forEach(i -> i.setPlayerA(playerEntityA));
        //playerEntityB.getChessMatchsB().forEach(i -> i.setPlayerB(playerEntityB));
        if(playerWhiteInGame)
        {
            chessMatchEntity.setNextColor(PieceColor.WHITE);
        }
        else {
            chessMatchEntity.setNextColor(PieceColor.BLACK);
        }

        //chessMatchEntity.setPlayerA(playerEntityA);
        //chessMatchEntity.setPlayerB(playerEntityB);
        ChessMatchEntity savedEntity = chessGameJpaRepository.save(chessMatchEntity);
        savedId = savedEntity.getId();
    }
    @Override
    public List<ChessGame> getAllMatchs () {
        List<ChessMatchEntity> chessMatchEntities = chessGameJpaRepository.findAll();
        return chessMatchEntities.stream()
                .map(chessMatchEntity -> mergerMapper.map(chessMatchEntity, ChessGame.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateGame(ChessGame chessGame) throws JsonProcessingException {
        Optional<ChessMatchEntity> optionalMatchEntity = chessGameJpaRepository.findById(savedId);
        if (optionalMatchEntity.isPresent()) {
            ChessMatchEntity matchEntity = optionalMatchEntity.get();

            // Actualizar los datos de la partida en la entidad
            matchEntity.setMatchInfo(objectMapper.writeValueAsString(chessGame.getPieceListInBoard()));

            PlayerEntity playerEntityA = modelMapper.map(chessGame.getPlayerA(), PlayerEntity.class);
            PlayerEntity playerEntityB = modelMapper.map(chessGame.getPlayerB(), PlayerEntity.class);
            //playerEntityA.getChessMatchsA().forEach(i -> i.setPlayerA(playerEntityA));
            //playerEntityB.getChessMatchsB().forEach(i -> i.setPlayerB(playerEntityB));

            if (playerEntityA.getColor()== PieceColor.WHITE) {
                matchEntity.setNextColor(PieceColor.BLACK);
            } else{
                matchEntity.setNextColor(PieceColor.WHITE);
            }

            //chessMatchEntity.setPlayerA(playerEntityA);
            //chessMatchEntity.setPlayerB(playerEntityB);

            chessGameJpaRepository.save(matchEntity);

            System.out.println("Partida actualizada exitosamente.");
        } else {
            System.out.println("La partida no existe.");
        }
    }


}
