package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.models.ChessGame;
import ar.edu.utn.frc.tup.lciii.services.ChessGameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChessGameController {
    @Autowired
    private ChessGameService chessGameService;
    public ChessGame getGameById(Long id) throws JsonProcessingException {
        return chessGameService.getGameById(id);
    }
    public void saveChessGame(ChessGame chessGame, boolean playerWhiteInGame){
        chessGameService.createGame(chessGame, playerWhiteInGame);
    }

    public List<ChessGame> getAllChessGame() {
        return chessGameService.getAllMatchs();
    }

    public void updateChessGame(ChessGame chessGame) throws JsonProcessingException {
        chessGameService.updateGame(chessGame);
    }




}

