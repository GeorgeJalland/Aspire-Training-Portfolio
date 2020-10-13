package com.mthree.bullsandcows.service;

import com.mthree.bullsandcows.data.BCDaoImpl;
import com.mthree.bullsandcows.model.Game;
import com.mthree.bullsandcows.model.Round;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BCService {

    private final BCDaoImpl dao;

    @Autowired
    public BCService(BCDaoImpl dao) {
        this.dao = dao;
    }

    private static String generateAnswer() {
        Random randy = new Random();
        String answer = "";
        while (answer.length() < 4) {
            String digit = Integer.toString(randy.nextInt(9) + 1);
            if (!answer.contains(digit)) {
                answer += digit;
            }
        }
        return answer;
    }

    private static String guessResult(String answer, String guess) {
        int exact = 0;
        int partial = 0;
        for (int i = 0; i < 4; i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                exact += 1;
            } else if (answer.indexOf(guess.charAt(i)) != -1) {
                partial += 1;
            }
        }
        return "e:" + exact + ":p:" + partial;
    }

    public List<Round> getRounds(int gameId) throws GameDoesNotExistException {
        getGameById(gameId);
        return dao.getRounds(gameId);
    }

    public List<Game> getAllGames() {
        List<Game> gameList = new ArrayList<>();
        gameList = dao.getAllGames();
        for (Game game : gameList) {
            hideAnswer(game);
        }
        return gameList;
    }

    private void hideAnswer(Game game) {
        if (game.getStatus().equals("In progress")) {
            game.setAnswer("Hidden");
        }
    }

    public Game getGameById(int gameId) throws GameDoesNotExistException {
        Game game = dao.getGameById(gameId);
        if (game == null) {
            throw new GameDoesNotExistException("Game does not exist");
        }
        hideAnswer(game);
        return game;
    }

    public Round createRound(String guess, int gameId) throws GameCompleteException, GameDoesNotExistException {
        Game currentGame = dao.getGameById(gameId);
        if (currentGame == null) {
            throw new GameDoesNotExistException("Game does not exist");
        }
        if (currentGame.getStatus().equals("Complete")) {
            throw new GameCompleteException("Game already complete");
        }
        String result = guessResult(currentGame.getAnswer(), guess);
        Round newRound = new Round(guess, result, gameId);
        if (result.charAt(2) == '4') {
            updateGame(gameId);
        }
        newRound = dao.addRound(newRound);
        if (newRound == null) {

        }
        return newRound;
    }

    public Game addGame() {
        Game newGame = new Game(generateAnswer(), "In progress");
        return dao.addGame(newGame);
    }

    private void updateGame(int gameID) {
        dao.updateGame(gameID);
    }

}
