package treasurequest.domains;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTests {
    // Teste la méthode getX()
    @Test
    void testGetX() {
        Coordinate coordinate = new Coordinate(1, 2);
        assertEquals(1, coordinate.getX());
    }

    // Teste la méthode setX()
    @Test
    void testSetX() {
        Coordinate coordinate = new Coordinate(1, 2);
        coordinate.setX(3);
        assertEquals(3, coordinate.getX());
    }

    // Teste la méthode getY()
    @Test
    void testGetY() {
        Coordinate coordinate = new Coordinate(1, 2);
        assertEquals(2, coordinate.getY());
    }

    // Teste la méthode setY()
    @Test
    void testSetY() {
        Coordinate coordinate = new Coordinate(1, 2);
        coordinate.setY(4);
        assertEquals(4, coordinate.getY());
    }

    // Teste la méthode hashCode()
    @Test
    void testHashCode() {
        Coordinate coordinate1 = new Coordinate(1, 2);
        Coordinate coordinate2 = new Coordinate(1, 2);
        assertEquals(coordinate1.hashCode(), coordinate2.hashCode());
    }

    // Teste la méthode equals()
    @Test
    void testEquals() {
        Coordinate coordinate1 = new Coordinate(1, 2);
        Coordinate coordinate2 = new Coordinate(1, 2);
        Coordinate coordinate3 = new Coordinate(3, 4);
        assertTrue(coordinate1.equals(coordinate2));
        assertFalse(coordinate1.equals(coordinate3));
    }

    // Teste la méthode distanceCalculateXandY()
    @Test
    void testDistanceCalculateXandY() {
        Coordinate coordinate1 = new Coordinate(1, 2);
        Coordinate coordinate2 = new Coordinate(4, 6);
        double distance = Coordinate.distanceCalculateXandY(coordinate1, coordinate2);
        assertEquals(5.0, distance);
    }

    // Teste la méthode distanceTo()
    @Test
    void testDistanceTo() {
        Coordinate coordinate1 = new Coordinate(1, 2);
        Coordinate coordinate2 = new Coordinate(4, 6);
        double distance = coordinate1.distanceTo(coordinate2);
        assertEquals(5.0, distance);
    }

    // Teste la méthode calculateDirection()
    @Test
    void testCalculateDirection() {
        Coordinate coordinate1 = new Coordinate(1, 2);
        Coordinate coordinate2 = new Coordinate(3, 1);
        OrientationSprite direction = coordinate1.calculateDirection(coordinate2);
        assertEquals(OrientationSprite.NORTH_EAST, direction);
    }
    
    @Test
    void testCalculateDirectionNorth() {
        Coordinate coord1 = new Coordinate(5, 5);
        Coordinate coord2 = new Coordinate(5, 4);
        OrientationSprite direction = coord1.calculateDirection(coord2);

        assertEquals(OrientationSprite.NORTH, direction);
    }

    @Test
    void testCalculateDirectionSouth() {
        Coordinate coord1 = new Coordinate(5, 5);
        Coordinate coord2 = new Coordinate(5, 6);
        OrientationSprite direction = coord1.calculateDirection(coord2);

        assertEquals(OrientationSprite.SOUTH, direction);
    }

    @Test
    void testCalculateDirectionEast() {
        Coordinate coord1 = new Coordinate(5, 5);
        Coordinate coord2 = new Coordinate(6, 5);
        OrientationSprite direction = coord1.calculateDirection(coord2);

        assertEquals(OrientationSprite.EAST, direction);
    }

    @Test
    void testCalculateDirectionWest() {
        Coordinate coord1 = new Coordinate(5, 5);
        Coordinate coord2 = new Coordinate(4, 5);
        OrientationSprite direction = coord1.calculateDirection(coord2);

        assertEquals(OrientationSprite.WEST, direction);
    }

}

