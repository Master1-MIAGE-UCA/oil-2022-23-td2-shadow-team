import commun.CaseYams;
import commun.EtatJeuService;
import commun.constants.TypeCombinaison;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EtatJeuServiceTest {

    @Test
    public void testIncrementNombreDeTour() {
        EtatJeuService etatJeu = new EtatJeuService();
        assertEquals(1, etatJeu.getNombreDeTour());
        etatJeu.incrementeNombreDeTour();
        assertEquals(2, etatJeu.getNombreDeTour());
    }

    @Test
    public void testEstNombreDeTourFini() {
        EtatJeuService etatJeu = new EtatJeuService();
        for(int i = 0; i < 13; i++){
            assertFalse(etatJeu.estNombreDeTourFini());
            etatJeu.incrementeNombreDeTour();
        }
        assertTrue(etatJeu.estNombreDeTourFini());
    }

    @Test
    public void testSetAndGetDes() {
        EtatJeuService etatJeu = new EtatJeuService();
        List<Integer> des = Arrays.asList(1, 2, 3, 4, 5);
        etatJeu.setDes(des);
        assertEquals(des, etatJeu.getDes());
    }

    @Test
    public void testSetAndGetNombreRelance() {
        EtatJeuService etatJeu = new EtatJeuService();
        etatJeu.setNombreRelance(3);
        assertEquals(3, etatJeu.getNombreRelance());
    }

    @Test
    public void testSetAndGetFeuilleYams() {
        EtatJeuService etatJeu = new EtatJeuService();
        Map<TypeCombinaison, CaseYams> feuilleYams = new HashMap<>();
        feuilleYams.put(TypeCombinaison.YAMS, new CaseYams());
        etatJeu.setFeuilleYams(feuilleYams);
        assertEquals(feuilleYams, etatJeu.getFeuilleYams());
    }
}
