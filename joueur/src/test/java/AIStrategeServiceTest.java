import commun.CaseYams;
import commun.CombinaisonService;
import commun.constants.TypeCombinaison;
import org.junit.jupiter.api.Test;
import yams.AIStrategeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AIStrategeServiceTest {


    @Test
    public void testEstFeuilleRemplie() {
        AIStrategeService aiStrategeService = new AIStrategeService();
        Map<TypeCombinaison, CaseYams> caseYamsAR = new HashMap<>();
        for (TypeCombinaison typeCombinaison : TypeCombinaison.values()) {
            CaseYams caseYams = new CaseYams();
            caseYams.setEstCaseScoreBarre(true);
            caseYams.setScore(10);
            caseYamsAR.put(typeCombinaison, caseYams);
        }
        aiStrategeService.setCaseYamsAR(caseYamsAR);
        assertTrue(aiStrategeService.estFeuilleRemplie());
    }
    @Test
    public void testEstCetteCombinaisonRealisee() {
        AIStrategeService aiStrategeService = new AIStrategeService();
        Map<TypeCombinaison, CaseYams> caseYamsAR = new HashMap<>();
        CaseYams caseYams = new CaseYams();
        caseYams.setScore(10);
        caseYamsAR.put(TypeCombinaison.BRELAN, caseYams);
        aiStrategeService.setCaseYamsAR(caseYamsAR);

        CombinaisonService combinaisonService = new CombinaisonService(TypeCombinaison.BRELAN);
        aiStrategeService.setCombinaisonChoisie(combinaisonService);

        assertTrue(aiStrategeService.estCetteCombinaisonRealisee());
    }
    /*
    @Test
    public void testChoisirCombinaison() {
        AIStrategeService aiStrategeService = new AIStrategeService();
        List<Integer> des = List.of(1, 2, 3, 4, 5);
        aiStrategeService.setDes(des);

        Map<TypeCombinaison, CaseYams> caseYamsAR = new HashMap<>();
        CaseYams caseYams = new CaseYams();
        caseYams.setScore(0);
        caseYamsAR.put(TypeCombinaison.GRANDE_SUITE, caseYams);
        aiStrategeService.setCaseYamsAR(caseYamsAR);
        aiStrategeService.choisirCombinaison();
        assertEquals(TypeCombinaison.GRANDE_SUITE, aiStrategeService.getCombinaisonChoisie().getTypeCombinaison());
    }

     */
}
