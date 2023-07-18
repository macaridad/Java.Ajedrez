package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.models.ChessGame;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChessGameService {
    ChessGame getGameById(Long id) throws JsonProcessingException;
    void createGame(ChessGame chessGame, boolean playerWhiteInGame);
    List<ChessGame> getAllMatchs();
    void updateGame(ChessGame chessGame) throws JsonProcessingException;
}
