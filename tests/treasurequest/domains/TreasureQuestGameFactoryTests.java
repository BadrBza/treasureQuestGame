package treasurequest.domains;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreasureQuestGameFactoryTests {
    private TreasureQuestGameFactory gameFactory;

    @BeforeEach
    void setUp() {
        gameFactory = new TreasureQuestGameFactory();
    }

    @Test
    void testCreateNewGame() {
        gameFactory.createNewGame();

        TreasureQuestGame game = gameFactory.getGame();

        assertNotNull(game);
        assertNotNull(game.getCaseMap());
        assertTrue(game.getPlayerCoins() > 0);
    }

    @Test
    void testCreateNewGameBigMap() {
        gameFactory.createNewGameBigMap();

        TreasureQuestGame game = gameFactory.getGame();

        assertNotNull(game);
        assertNotNull(game.getCaseMap());
        assertTrue(game.getPlayerCoins() > 0);
        assertTrue(game.getCaseMap().getWidth() <= 16);
        assertTrue(game.getCaseMap().getHeight() <= 16);
    }

    @Test
    void testGetGame() {
        TreasureQuestGame retrievedGame = gameFactory.getGame();

        assertNull(retrievedGame);

        gameFactory.createNewGame();

        retrievedGame = gameFactory.getGame();

        assertNotNull(retrievedGame);
        assertEquals(gameFactory.getGame(), retrievedGame);
    }
}
