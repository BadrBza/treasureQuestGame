package treasurequest.domains;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TreasureQuestGameTests {
	private TreasureQuestGame game;

	@BeforeEach
	void setUp() {
		game = new TreasureQuestGame();
		game.newGame();
	}

	@Test
	void testNewGame() {
		assertNotNull(game.getCaseMap());
		assertTrue(game.getTreasureCount() > 0);
		assertNotNull(game.getPlayer());
		assertNotNull(game.getCoordinateActive());
	}

	@Test
	void testNewGameBigMap() {
		game.newGameBigMap();

		assertNotNull(game.getCaseMap());
		assertTrue(game.getTreasureCount() > 0);
		assertNotNull(game.getPlayer());
		assertNotNull(game.getCoordinateActive());
		assertTrue(game.getCaseMap().getWidth() >= 16);
		assertTrue(game.getCaseMap().getHeight() >= 16);
	}

	@Test
	void testGetPlayerCoins() {
		int coins = game.getPlayerCoins();

		assertTrue(coins >= 0);
	}

	@Test
	void testSetPlayerCoins() {
		game.setPlayerCoins(10);

		assertEquals(10, game.getPlayerCoins());

		game.setPlayerCoins(-5);

		assertEquals(0, game.getPlayerCoins());
	}

	@Test
	void testGetMiddleCoordinate() {
		Coordinate middleCoordinate = game.getMiddleCoordinate();

		assertNotNull(middleCoordinate);
	}

	@Test
	void testGetCoordinateValue() {
		Coordinate coordinate = new Coordinate(0, 0);
		Case caseValue = game.getCoordinateValue(coordinate);

		assertNotNull(caseValue);
	}

	@Test
	void testGetCaseTypeAtcoordinate() {
		Coordinate coordinate = new Coordinate(0, 0);
		CaseType caseType = game.getCaseTypeAtcoordinate(coordinate);

		assertNotNull(caseType);
	}

	@Test
	void testGetDiggableCaseCount() {
		int diggableCaseCount = game.getDiggableCaseCount();

		assertTrue(diggableCaseCount >= 0);
	}

	@Test
	void testIsValidCoordinate() {
		assertTrue(game.isValidCoordinate(0, 0));
		assertFalse(game.isValidCoordinate(-1, 0));
		assertFalse(game.isValidCoordinate(0, -1));
		assertFalse(game.isValidCoordinate(game.getCaseMap().getWidth(), 0));
		assertFalse(game.isValidCoordinate(0, game.getCaseMap().getHeight()));
	}

	@Test
	void testGetCaseTypeAtCoordinate() {
		CaseType caseType = game.getCaseTypeAtCoordinate(0, 0);

		assertNotNull(caseType);
	}

	@Test
	void testSetActiveCoordinate() {
		game.setActiveCoordinate(0, 0);

		assertEquals(new Coordinate(0, 0), game.getCoordinateActive());
	}

	@Test
	void testUpdateActiveCase() {
		Coordinate coordinate = game.getCoordinateActive();
		Case activeCase = game.getCoordinateValue(coordinate);

		assertFalse(activeCase.isVisited());

		game.updateActiveCase();

		assertTrue(activeCase.isVisited());
	}

	@Test
	void testGetPlayer() {
		Player player = game.getPlayer();

		assertNotNull(player);
	}

	@Test
	void testGetCaseMap() {
		CaseMap caseMap = game.getCaseMap();

		assertNotNull(caseMap);
	}

	@Test
	void testGetTreasureCount() {
		int treasureCount = game.getTreasureCount();

		assertTrue(treasureCount >= 0);
	}

	@Test
	void testAddCoins() {
		int initialCoins = game.getPlayerCoins();

		game.addCoins(10);

		assertEquals(initialCoins + 10, game.getPlayerCoins());
	}

	@Test
	void testDeductCoins() {
		int initialPlayerCoins = game.getPlayerCoins();
		int coinsToDeduct = 10;
		game.deductCoins(coinsToDeduct);
		int newPlayerCoins = game.getPlayerCoins();

		assertEquals(initialPlayerCoins - coinsToDeduct, newPlayerCoins);
	}

	@Test
	void testCanDig() {
		Coordinate coordinate = game.getCoordinateActive();
		Case activeCase = game.getCoordinateValue(coordinate);

		boolean canDig = game.canDig(activeCase);
		assertTrue(canDig);
	}

	@Test
	void testDecrementDiggableCaseCount() {
		int initialDiggableCaseCount = game.getDiggableCaseCount();
		game.decrementDiggableCaseCount();
		int newDiggableCaseCount = game.getDiggableCaseCount();

		assertEquals(initialDiggableCaseCount, newDiggableCaseCount);
	}

	@Test
	void testGetNearestTreasure() {
		Coordinate nearestTreasure = game.getNearestTreasure(game.getCoordinateActive());

		assertNotNull(nearestTreasure);
	}

	@Test
	void testGetTreasureArea() {
		List<Coordinate> treasureArea = game.getTreasureArea(game.getCoordinateActive());

		assertNotNull(treasureArea);
	}

	@Test
	void testGenerateIndice() {
		Coordinate coordinate = game.getCoordinateActive();
		OrientationSprite indice = game.generateIndice(coordinate);

		assertNotNull(indice);
	}

	@Test
	void testIsGameOver() {
		assertFalse(game.isGameOver());
	}

	@Test
	void testHasDiggableCases() {
		assertTrue(game.hasDiggableCases());
	}

	@Test
	void testIsAllTreasuresFound() {
		assertFalse(game.isAllTreasuresFound());
	}

	@Test
	void testGetDugCoordinates() {
		Map<Coordinate, Case> dugCoordinates = game.getDugCoordinates();

		assertNotNull(dugCoordinates);
	}
}
