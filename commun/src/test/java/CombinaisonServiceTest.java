import commun.CombinaisonService;
import commun.constants.TypeCombinaison;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CombinaisonServiceTest {

    @Test
    void testCalculeScoreBrelan() {
        CombinaisonService combinaisonService = new CombinaisonService(TypeCombinaison.BRELAN);
        List<Integer> des = Arrays.asList(3, 3, 3, 2, 1); // suppose un brelan de 3
        combinaisonService.calculeScore(des);
        assertEquals(9, combinaisonService.getScore()); // 3 * 3 = 9
    }

    @Test
    void testCalculeScoreCarre() {
        CombinaisonService combinaisonService = new CombinaisonService(TypeCombinaison.CARRE);
        List<Integer> des = Arrays.asList(4, 4, 4, 4, 2); // suppose un carré de 4
        combinaisonService.calculeScore(des);
        assertEquals(16, combinaisonService.getScore()); // 4 * 4 = 16
    }
    @Test
    void testCalculeScoreFull() {
        CombinaisonService combinaisonService = new CombinaisonService(TypeCombinaison.FULL);
        List<Integer> des = Arrays.asList(2, 2, 3, 3, 3); // suppose un full (2,2 et 3,3,3)
        combinaisonService.calculeScore(des);
        assertEquals(25, combinaisonService.getScore()); // score fixe pour un full
    }


    @Test
    void testCalculeScoreGrandeSuite() {
        CombinaisonService combinaisonService = new CombinaisonService(TypeCombinaison.GRANDE_SUITE);
        List<Integer> des = Arrays.asList(2, 3, 4, 5, 6); // suppose une grande suite (2,3,4,5,6)
        combinaisonService.calculeScore(des);
        assertEquals(40, combinaisonService.getScore()); // score fixe pour une grande suite
    }

    @Test
    void testCalculeScoreYams() {
        CombinaisonService combinaisonService = new CombinaisonService(TypeCombinaison.YAMS);
        List<Integer> des = Arrays.asList(5, 5, 5, 5, 5); // suppose un yams de 5
        combinaisonService.calculeScore(des);
        assertEquals(50, combinaisonService.getScore()); // score fixe pour un yams
    }

    @Test
    void testCalculeScoreChance() {
        CombinaisonService combinaisonService = new CombinaisonService(TypeCombinaison.CHANCE);
        List<Integer> des = Arrays.asList(2, 3, 4, 5, 1); // aucun besoin particulier
        combinaisonService.calculeScore(des);
        assertEquals(15, combinaisonService.getScore()); // la somme des dés (2+3+4+5+1)
    }

    @Test
    void testCalculeScoreTotal1() {
        CombinaisonService combinaisonService = new CombinaisonService(TypeCombinaison.TOTAL_1);
        List<Integer> des = Arrays.asList(1, 1, 3, 1, 5); // trois 1
        combinaisonService.calculeScore(des);
        assertEquals(3, combinaisonService.getScore()); // la somme des 1
    }

    @Test
    void testCalculeScoreTotal2() {
        CombinaisonService combinaisonService = new CombinaisonService(TypeCombinaison.TOTAL_2);
        List<Integer> des = Arrays.asList(2, 2, 2, 5, 6); // trois 2
        combinaisonService.calculeScore(des);
        assertEquals(6, combinaisonService.getScore()); // la somme des 2 (3*2)
    }
}