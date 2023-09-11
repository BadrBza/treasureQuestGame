package treasurequest.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class GameStatisticsTests {
    
    private GameStatistics gameStatistics;
    
    @BeforeEach
    public void setUp() {
        gameStatistics = new GameStatistics();
    }

    @Test
    public void testIncrementTimePlayed() {
        long initialTimePlayed = gameStatistics.getTotalTimePlayed();
        gameStatistics.incrementTimePlayed(5000);
        assertEquals(initialTimePlayed + 5000, gameStatistics.getTotalTimePlayed());
    }

    @Test
    public void testIncrementEarnings() {
        int initialEarnings = gameStatistics.getTotalEarnings();
        gameStatistics.incrementEarnings(1000);
        assertEquals(initialEarnings + 1000, gameStatistics.getTotalEarnings());
    }

    @Test
    public void testIncrementExpenses() {
        int initialExpenses = gameStatistics.getTotalExpenses();
        gameStatistics.incrementExpenses(500);
        assertEquals(initialExpenses + 500, gameStatistics.getTotalExpenses());
    }

    @Test
    public void testEndGameTime() {
        gameStatistics.startTime();
        try {
           // simule un temps d'arret
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameStatistics.endGameTime();

        long totalMillis = gameStatistics.getTotalTimePlayed();

        
        assertTrue(totalMillis >= 1000 && totalMillis < 1050);
    }

}

