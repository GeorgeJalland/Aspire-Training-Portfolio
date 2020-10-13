package com.mthree.bullsandcows.controller;

import com.mthree.bullsandcows.model.Game;
import com.mthree.bullsandcows.model.Round;
import com.mthree.bullsandcows.service.BCService;
import com.mthree.bullsandcows.service.GameCompleteException;
import com.mthree.bullsandcows.service.GameDoesNotExistException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/bullsandcows")
public class BCController {

    private final BCService service;

    @Autowired
    public BCController(BCService service) {
        this.service = service;
    }

    @PostMapping("/begin")
    public ResponseEntity startGame() {
        Game newGame = service.addGame();
        return ResponseEntity.status(HttpStatus.CREATED).body("Game started, gameId: " + newGame.getId());
    }

    @PostMapping("/guess")
    public ResponseEntity guess(@RequestBody Round round) throws GameDoesNotExistException, GameCompleteException {
        Round result = service.createRound(round.getGuess(), round.getGameID());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/game")
    public List<Game> listGames() {
        return service.getAllGames();
    }

    @GetMapping("/game/{gameID}")
    public ResponseEntity getGameById(@PathVariable int gameID) throws GameDoesNotExistException {
        Game game = service.getGameById(gameID);
        return ResponseEntity.ok(game);
    }

    @GetMapping("/rounds/{gameID}")
    public List<Round> getRounds(@PathVariable int gameID) throws GameDoesNotExistException {
        return service.getRounds(gameID);
    }

}
