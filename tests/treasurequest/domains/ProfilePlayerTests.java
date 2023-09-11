package treasurequest.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ProfilePlayerTests {

    private ProfilePlayer profilePlayer;
    private Map<Coordinate, Case> playerVisitedCases;

    @BeforeEach
    public void setUp() {
        playerVisitedCases = new HashMap<>();
        playerVisitedCases.put(new Coordinate(0, 0), new Case(CaseType.FOREST));
        playerVisitedCases.put(new Coordinate(0, 1), new Case(CaseType.FOREST));
        playerVisitedCases.put(new Coordinate(1, 0), new Case(CaseType.SAND));
        playerVisitedCases.put(new Coordinate(1, 1), new Case(CaseType.SAND));
        playerVisitedCases.put(new Coordinate(1, 2), new Case(CaseType.SAND));

        profilePlayer = new ProfilePlayer(playerVisitedCases);
    }

    @Test
    public void testDetermineDominantCaseType() {
        CaseType dominantCaseType = profilePlayer.determineDominantCaseType();
        assertEquals(CaseType.SAND, dominantCaseType);
    }
    
    
}
