import commun.CaseYams;
import commun.constants.TypeCombinaison;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CaseYamsTest {

    @Test
    void testConstructorAndGettersAndSetters() {
        CaseYams caseYams = new CaseYams();

        // Teste les setters et getters pour typeCombinaison
        caseYams.setTypeCombinaison(TypeCombinaison.FULL);
        assertEquals(TypeCombinaison.FULL, caseYams.getTypeCombinaison());

        // Teste les setters et getters pour score
        caseYams.setScore(10);
        assertEquals(10, caseYams.getScore());

        // Teste les setters et getters pour estCaseScoreBarre
        caseYams.setEstCaseScoreBarre(true);
        assertTrue(caseYams.estCaseScoreBarre());

        // Teste le constructeur complet
        CaseYams caseYams2 = new CaseYams(TypeCombinaison.BRELAN, 20, true);
        assertEquals(TypeCombinaison.BRELAN, caseYams2.getTypeCombinaison());
        assertEquals(20, caseYams2.getScore());
        assertTrue(caseYams2.estCaseScoreBarre());
    }
}
