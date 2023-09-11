package treasurequest.domains;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import treasurequest.supervisors.views.TileType;

public class CaseTypeTests {

    @Test
    public void testGetPriceCoins() {
        assertEquals(-1, CaseType.WATER.getPriceCoins());
        assertEquals(1, CaseType.SAND.getPriceCoins());
        assertEquals(2, CaseType.GRASSLAND.getPriceCoins());
        assertEquals(3, CaseType.FOREST.getPriceCoins());
        assertEquals(5, CaseType.ROCK.getPriceCoins());
        assertEquals(0, CaseType.UNKNOWN.getPriceCoins());
    }

    @Test
    public void testFromChar() {
        assertEquals(CaseType.WATER, CaseType.fromChar('X'));
        assertEquals(CaseType.SAND, CaseType.fromChar('S'));
        assertEquals(CaseType.GRASSLAND, CaseType.fromChar('P'));
        assertEquals(CaseType.FOREST, CaseType.fromChar('F'));
        assertEquals(CaseType.ROCK, CaseType.fromChar('R'));
        assertEquals(CaseType.UNKNOWN, CaseType.fromChar('A'));
    }

    //@Test
    //public void testToTileType() {
        //assertEquals(TileType.WATER, CaseType.WATER.toTileType());
        //assertEquals(TileType.SAND, CaseType.SAND.toTileType());
        //assertEquals(TileType.GRASSLAND, CaseType.GRASSLAND.toTileType());
        //assertEquals(TileType.FOREST, CaseType.FOREST.toTileType());
        //assertEquals(TileType.ROCK, CaseType.ROCK.toTileType());
       // assertEquals(TileType.UNKNOWN, CaseType.UNKNOWN.toTileType());
    //}
}
