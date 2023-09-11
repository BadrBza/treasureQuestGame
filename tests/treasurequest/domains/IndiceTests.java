package treasurequest.domains;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndiceTests {
    @Test
    void testGetDistance() {
        double distance = 10.5;
        int value = 20;
        Indice indice = new Indice(distance, value);

        assertEquals(distance, indice.getDistance());
    }

    @Test
    void testSetDistance() {
        double distance = 10.5;
        int value = 20;
        Indice indice = new Indice();

        indice.setDistance(distance);

        assertEquals(distance, indice.getDistance());
    }

    @Test
    void testGetValue() {
        double distance = 10.5;
        int value = 20;
        Indice indice = new Indice(distance, value);

        assertEquals(value, indice.getValue());
    }

    @Test
    void testSetValue() {
        double distance = 10.5;
        int value = 20;
        Indice indice = new Indice();

        indice.setValue(value);

        assertEquals(value, indice.getValue());
    }
    @Test
    void testVerifValue() {
        Indice indice = new Indice(10.0, 20);

        assertEquals(0, indice.verifValue(0));

        assertEquals(20, indice.verifValue(30));
        assertEquals(20, indice.verifValue(-10));
    }

    @Test
    void testCalculateTreasureRatio() {
        Indice indice1 = new Indice(10.0, 20);
        Indice indice2 = new Indice(0.0, 10);

        assertEquals(2.0, indice1.calculateTreasureRatio());
        assertEquals(Double.POSITIVE_INFINITY, indice2.calculateTreasureRatio());
    }

    @Test
    void testMergeWith() {
        Indice indice1 = new Indice(10.0, 20);
        Indice indice2 = new Indice(5.0, 30);

        Indice mergedIndice = indice1.mergeWith(indice2);

        assertEquals(15.0, mergedIndice.getDistance());
        assertEquals(50, mergedIndice.getValue());
    }
}
