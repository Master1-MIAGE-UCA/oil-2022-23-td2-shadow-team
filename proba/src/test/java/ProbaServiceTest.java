import commun.constants.TypeCombinaison;
import org.junit.jupiter.api.Test;
import yams.ProbaService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProbaServiceTest {

    private final ProbaService probaService = new ProbaService();

    @Test
    public void testCalculeProbabiliteCarre() {
        List<Integer> des = Arrays.asList(1, 2, 3, 4, 5);
        TypeCombinaison typeCombinaison = TypeCombinaison.CARRE;
        double expectedProbabilite = 0.0;

        double probabilite = probaService.calculeProbabilite(des, typeCombinaison);

        assertEquals(expectedProbabilite, probabilite, 0.001);
    }

    @Test
    public void testCalculeProbabiliteFull() {
        List<Integer> des = Arrays.asList(1, 1, 2, 2, 3);
        TypeCombinaison typeCombinaison = TypeCombinaison.FULL;
        double expectedProbabilite = 0.0;

        double probabilite = probaService.calculeProbabilite(des, typeCombinaison);

        assertEquals(expectedProbabilite, probabilite, 0.001);
    }

    @Test
    public void testCalculeProbabiliteBrelan() {
        List<Integer> des = Arrays.asList(1, 2, 3, 4, 5);
        TypeCombinaison typeCombinaison = TypeCombinaison.BRELAN;
        double expectedProbabilite = 0.0;

        double probabilite = probaService.calculeProbabilite(des, typeCombinaison);

        assertEquals(expectedProbabilite, probabilite, 0.001);
    }
}
