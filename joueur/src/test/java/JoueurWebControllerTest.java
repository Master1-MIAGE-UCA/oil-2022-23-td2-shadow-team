import commun.CaseYams;
import commun.constants.TypeCombinaison;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import commun.Decision;
import commun.EtatJeuService;
import yams.AIStrategeService;
import yams.JoueurWebController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JoueurWebControllerTest {

    @InjectMocks
    JoueurWebController joueurWebController;

    @Mock
    AIStrategeService player;

    @Test
    public void testGetName() {
        MockitoAnnotations.openMocks(this);

        String playerName = "AI Player";

        Mockito.when(player.getName()).thenReturn(playerName);

        String result = joueurWebController.getName();
        assertEquals(playerName, result);
    }

    @Test
    public void testPlay() {
        MockitoAnnotations.openMocks(this);

        EtatJeuService etatJeuService = new EtatJeuService();
        etatJeuService.setFeuilleYams(new HashMap<TypeCombinaison, CaseYams>());
        etatJeuService.setDes(new ArrayList<>());
        etatJeuService.setNombreRelance(0);
        //etatJeuService.setNombreDeTour(1);

        Decision decision = new Decision();
        decision.setEstCombinaisonAbarrer(true);
        decision.setCombinaison(TypeCombinaison.BRELAN);

        Mockito.when(player.play(etatJeuService)).thenReturn(decision);

        Decision result = joueurWebController.play(etatJeuService);
        assertEquals(decision, result);
    }
}
