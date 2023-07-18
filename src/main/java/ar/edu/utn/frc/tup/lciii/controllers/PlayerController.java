package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    public Player getPlayer(Long id){
        return playerService.getPlayer(id);
    }

    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    public Player getPlayerById(Long id){
        return playerService.getPlayerById(id);
    }

    public Player createPlayer(Player player) {
        return playerService.createPlayer(player);
    }
}
