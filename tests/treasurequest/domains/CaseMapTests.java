package treasurequest.domains;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaseMapTests {
    private CaseMap caseMap;

    @BeforeEach
    void setUp() {
        caseMap = new CaseMap();
        char[][] mapArray = {
            {'X', 'X', 'X'},
            {'X', 'S', 'X'},
            {'X', 'X', 'X'}
        };
        caseMap.createFromCharArray(mapArray);
    }

    // Teste la méthode getWidth() pour une carte non vide
    @Test
    void testGetWidthNonEmptyMap() {
        assertEquals(3, caseMap.getWidth());
    }

    // Teste la méthode getHeight() pour une carte non vide
    @Test
    void testGetHeightNonEmptyMap() {
        assertEquals(3, caseMap.getHeight());
    }

    // Teste la méthode getDiggableCaseCount() pour une carte non vide
    @Test
    void testGetDiggableCaseCountNonEmptyMap() {
        assertEquals(1, caseMap.getDiggableCaseCount());
    }

    // Teste la méthode getCoordinateValue() pour une carte non vide
    @Test
    void testGetCoordinateValueNonEmptyMap() {
        Coordinate coordinate = new Coordinate(1, 1);
        Case caseValue = caseMap.getCoordinateValue(coordinate);
        assertNotNull(caseValue);
        assertEquals(CaseType.SAND, caseValue.getType());
    }

    // Teste la méthode placeTreasureRandomly() pour une carte non vide
    @Test
    void testPlaceTreasureRandomlyNonEmptyMap() {
        caseMap.placeTreasureRandomly(10);
        assertEquals(1, caseMap.getDiggableCaseCount());
    }

    // Teste la méthode getMiddleCoordinate() pour une carte non vide
    @Test
    void testGetMiddleCoordinateNonEmptyMap() {
        Coordinate middleCoordinate = caseMap.getMiddleCoordinate();
        assertNotNull(middleCoordinate);
        assertEquals(1, middleCoordinate.getX());
        assertEquals(1, middleCoordinate.getY());
    }

    // Teste la méthode iterator() pour une carte non vide
    @Test
    void testIteratorNonEmptyMap() {
        assertTrue(caseMap.iterator().hasNext());
        int count = 0;
        for (Coordinate coordinate : caseMap) {
            count++;
        }
        assertEquals(9, count);
    }

    // Teste la méthode getCaseTypeAtcoordinate() pour une carte non vide
    @Test
    void testGetCaseTypeAtcoordinateNonEmptyMap() {
        Coordinate coordinate = new Coordinate(1, 1);
        CaseType caseType = caseMap.getCaseTypeAtcoordinate(coordinate);
        assertNotNull(caseType);
        assertEquals(CaseType.SAND, caseType);
    }

    // Teste la méthode generateIndice() pour une carte non vide
    @Test
    void testGenerateIndiceNonEmptyMap() {
        Coordinate coordinate = new Coordinate(1, 1);
        OrientationSprite indice = caseMap.generateIndice(coordinate);
        assertNull(indice);
    }
}
