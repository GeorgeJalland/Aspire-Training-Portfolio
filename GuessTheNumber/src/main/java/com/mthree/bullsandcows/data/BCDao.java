package com.mthree.bullsandcows.data;

import com.mthree.bullsandcows.model.Game;
import com.mthree.bullsandcows.model.Round;
import java.util.List;

public interface BCDao {

    List<Round> getRounds(int gameID);

    List<Game> getAllGames();

    Game getGameById(int gameID);

    Round addRound(Round round);

    Game addGame(Game game);

    void updateGame(int gameID);

    void resetDatabase();

}
