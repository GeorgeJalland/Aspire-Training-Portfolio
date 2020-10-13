package com.mthree.bullsandcows.data;

import com.mthree.bullsandcows.TestApplicationConfiguration;
import com.mthree.bullsandcows.model.Game;
import com.mthree.bullsandcows.model.Round;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class BCDaoImplTest {

    private final BCDao testDao;

    @Autowired
    public BCDaoImplTest(BCDao testDao) {
        this.testDao = testDao;
    }

    @Before
    public void setUp() {
        testDao.resetDatabase();
    }

    @Test
    public void testAddGetGame() {
        Game expectedGame = new Game("1234", "In progress");

        expectedGame = testDao.addGame(expectedGame);
        Game actualGame = testDao.getGameById(expectedGame.getId());

        assertNull(testDao.getGameById(0)); //No such game exists
        assertEquals(expectedGame, actualGame);
    }

    @Test
    public void testUpdateGame() {
        Game expectedGame = new Game("3333", "In progress");

        expectedGame = testDao.addGame(expectedGame);
        testDao.updateGame(expectedGame.getId());
        expectedGame.setStatus("Complete");
        Game actualGame = testDao.getGameById(expectedGame.getId());

        assertEquals(expectedGame, actualGame);
    }

    @Test
    public void testAddGetRounds() {
        int addedGameId = testDao.addGame(new Game("8134", "In progress")).getId();
        Round round_1 = new Round("1234", "e:2:p:1", addedGameId);
        Round round_2 = new Round("8765", "e:1:p:0", addedGameId);
        Round round_3 = new Round("8134", "e:4:p:0", addedGameId);

        List<Round> expectedRounds = new ArrayList<>();
        expectedRounds.add(testDao.addRound(round_1));
        expectedRounds.add(testDao.addRound(round_2));
        expectedRounds.add(testDao.addRound(round_3));

        List<Round> actualRounds = new ArrayList<>();
        actualRounds = testDao.getRounds(addedGameId);

        assertTrue(actualRounds.size() == 3);
        assertEquals(expectedRounds, actualRounds);
    }

    @Test
    public void testGetAllGames() {
        Game game_1 = new Game("1234", "In progress");
        Game game_2 = new Game("3456", "In progress");
        Game game_3 = new Game("5678", "In progress");

        List<Game> expectedGames = new ArrayList<>();
        expectedGames.add(testDao.addGame(game_1));
        expectedGames.add(testDao.addGame(game_2));
        expectedGames.add(testDao.addGame(game_3));

        List<Game> actualGames = testDao.getAllGames();
        assertEquals(expectedGames, actualGames);
    }

}
