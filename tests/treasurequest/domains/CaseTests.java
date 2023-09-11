package treasurequest.domains;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

class CaseTests {
	private Case caseWater;
	private Case caseSand;
	private OrientationSprite indice;

	@BeforeEach
	void setUp() {
		caseWater = new Case(CaseType.WATER);
		caseSand = new Case(CaseType.SAND);
		indice = OrientationSprite.NORTH;
	}

	// Teste si la méthode getType() renvoie le bon type de case
	@Test
	void testGetType() {
		assertEquals(CaseType.WATER, caseWater.getType());
		assertEquals(CaseType.SAND, caseSand.getType());
	}

	// Teste si la méthode isDug() renvoie l'état correct (non creusé) pour les
	// cases nouvellement créées
	@Test
	void testIsDug() {
		assertFalse(caseWater.isDug());
		assertFalse(caseSand.isDug());
	}

	// Teste si la méthode dig() modifie correctement l'état de la case en fonction
	// de son type
	@Test
	void testDig() {
		caseWater.dig();
		assertFalse(caseWater.isDug()); // La case d'eau ne doit pas être creusée

		caseSand.dig();
		assertTrue(caseSand.isDug()); // La case de sable doit être creusée
	}

	// Teste si la méthode getTreasureValue() renvoie la valeur correcte du trésor
	// pour les cases nouvellement créées
	@Test
	void testGetTreasureValue() {
		assertEquals(0, caseWater.getTreasureValue());
		assertEquals(0, caseSand.getTreasureValue());
	}

	// Teste si la méthode setTreasureValue() affecte correctement la valeur du
	// trésor en fonction du type de case
	@Test
	void testSetTreasureValue() {
		caseWater.setTreasureValue(10);
		assertEquals(0, caseWater.getTreasureValue()); // La case d'eau ne doit pas stocker de trésor

		caseSand.setTreasureValue(10);
		assertEquals(10, caseSand.getTreasureValue()); // La case de sable doit stocker le trésor
	}

	// Teste si la méthode hasTreasure() renvoie le bon état de présence de trésor
	@Test
	void testHasTreasure() {
		assertFalse(caseWater.hasTreasure());
		assertFalse(caseSand.hasTreasure());

		caseSand.setTreasureValue(10);
		assertTrue(caseSand.hasTreasure()); // La case de sable doit avoir un trésor après avoir défini la valeur du
											// trésor
	}

	// Teste si la méthode isVisited() renvoie la bonne valeur par défaut (false)
	// pour les cases nouvellement créées
	@Test
	void testIsVisited() {
		assertFalse(caseWater.isVisited());
		assertFalse(caseSand.isVisited());
	}

	// Teste si la méthode setVisited() modifie correctement l'état visité de la
	// case
	@Test
	void testSetVisited() {
		caseWater.setVisited(true);
		assertTrue(caseWater.isVisited());

		caseSand.setVisited(true);
		assertTrue(caseSand.isVisited());
	}

	// Teste si la méthode getCoordinate() renvoie la coordonnée correcte de la case
	@Test
	void testGetCoordinate() {
		Coordinate coordinate = new Coordinate(1, 2);
		caseWater.setCoordinate(coordinate);

		assertEquals(coordinate, caseWater.getCoordinate()); // La coordonnée de la case d'eau doit être celle spécifiée
	}
}
