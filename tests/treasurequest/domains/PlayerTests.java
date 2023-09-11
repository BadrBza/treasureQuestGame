package treasurequest.domains;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTests {
    // Teste la méthode getCoins()
    @Test
    void testGetCoins() {
        Player player = new Player(100);
        assertEquals(100, player.getCoins());
    }

    // Teste la méthode setCoins()
    @Test
    void testSetCoins() {
        Player player = new Player(100);
        player.setCoins(200);
        assertEquals(200, player.getCoins());
    }

    // Teste la méthode canDig() lorsque le joueur a suffisamment de pièces
    @Test
    void testCanDigEnoughCoins() {
        Player player = new Player(10);
        assertTrue(player.canDig());
    }

    // Teste la méthode canDig() lorsque le joueur n'a pas suffisamment de pièces
    @Test
    void testCanDigNotEnoughCoins() {
        Player player = new Player(0);
        assertFalse(player.canDig());
    }

    // Teste la méthode addCoins()
    @Test
    void testAddCoins() {
        Player player = new Player(100);
        player.addCoins(50);
        assertEquals(150, player.getCoins());
    }

    // Teste la méthode subtractCoins()
    @Test
    void testSubtractCoins() {
        Player player = new Player(100);
        player.subtractCoins(50);
        assertEquals(50, player.getCoins());
    }
}
