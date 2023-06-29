import commun.Decision;
import commun.constants.TypeCombinaison;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DecisionTest {

    @Test
    public void testSetAndGetCombinaison() {
        Decision decision = new Decision();
        decision.setCombinaison(TypeCombinaison.YAMS);
        assertEquals(TypeCombinaison.YAMS, decision.getCombinaison());
    }

    @Test
    public void testSetAndGetEstOptionRelance() {
        Decision decision = new Decision();
        decision.setEstOptionRelance(true);
        assertTrue(decision.estOptionRelance());
    }

    @Test
    public void testSetAndGetDesARelancer() {
        Decision decision = new Decision();
        List<Integer> desARelancer = Arrays.asList(1, 2, 3);
        decision.setDesARelancer(desARelancer);
        assertEquals(desARelancer, decision.getDesARelancer());
    }

    @Test
    public void testSetAndGetDesAGarder() {
        Decision decision = new Decision();
        List<Integer> desAGarder = Arrays.asList(4, 5);
        decision.setDesAGarder(desAGarder);
        assertEquals(desAGarder, decision.getDesAGarder());
    }

    @Test
    public void testSetAndGetEstCombinaisonAbarrer() {
        Decision decision = new Decision();
        decision.setEstCombinaisonAbarrer(true);
        assertTrue(decision.estCombinaisonAbarrer());
    }
}
