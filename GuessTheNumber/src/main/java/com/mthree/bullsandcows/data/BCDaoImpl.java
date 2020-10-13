package com.mthree.bullsandcows.data;

import com.mthree.bullsandcows.model.Game;
import com.mthree.bullsandcows.model.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class BCDaoImpl implements BCDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BCDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Round> getRounds(int gameID) {
        final String sql = "SELECT * FROM round WHERE gameId = ?;";
        return jdbcTemplate.query(sql, new RoundMapper(), gameID);
    }

    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT * FROM game;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    @Override
    public Game getGameById(int gameID) {
        final String sql = "SELECT * FROM game WHERE id = ?;";
        try {
            return jdbcTemplate.queryForObject(sql, new GameMapper(), gameID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public Round addRound(Round round) {
        final String sql = "INSERT INTO round(guess, results, time, gameId) VALUES(?,?,now(),?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, round.getGuess());
            statement.setString(2, round.getResult());
            statement.setString(3, Integer.toString(round.getGameID()));
            return statement;

        }, keyHolder);

        int newId = keyHolder.getKey().intValue();
        final String sql2 = "SELECT time FROM round WHERE id = ?;";
        String time = (String) jdbcTemplate.queryForObject(sql2, new Object[]{newId}, String.class);
        round.setTime(time);
        return round;
    }

    @Override
    public Game addGame(Game game) {
        final String sql = "INSERT INTO game(answer,status) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getAnswer());
            statement.setString(2, game.getStatus());
            return statement;

        }, keyHolder);

        game.setId(keyHolder.getKey().intValue());

        return game;
    }

    @Override
    public void updateGame(int gameID) {
        final String sql = "UPDATE game SET status = 'Complete'"
                + "WHERE id = ?;";
        jdbcTemplate.update(sql, gameID);
    }

    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round(rs.getString("guess"),
                    rs.getString("results"), rs.getInt("gameId"));
            round.setTime(rs.getString("time"));
            return round;
        }
    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game(rs.getString("answer"), rs.getString("status"));
            game.setId(rs.getInt("id"));
            return game;
        }
    }

    @Override
    public void resetDatabase() {
        List<Game> games = getAllGames();
        for (Game game : games) {
            jdbcTemplate.update("DELETE FROM round WHERE gameId = ?;", game.getId());
        }
        jdbcTemplate.update("DELETE FROM game;");
    }

}
